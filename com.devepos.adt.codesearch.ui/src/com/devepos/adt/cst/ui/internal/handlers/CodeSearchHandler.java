package com.devepos.adt.cst.ui.internal.handlers;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.devepos.adt.base.IAdtObjectTypeConstants;
import com.devepos.adt.base.destinations.DestinationUtil;
import com.devepos.adt.base.ui.adtobject.IAdtObject;
import com.devepos.adt.base.ui.project.AbapProjectProviderAccessor;
import com.devepos.adt.base.ui.search.IChangeableSearchPage;
import com.devepos.adt.base.ui.search.ISearchPageListener;
import com.devepos.adt.base.ui.search.SearchPageUtil;
import com.devepos.adt.base.ui.util.AdtUIUtil;
import com.devepos.adt.base.ui.virtualfolders.IVirtualFolderNode;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchDialog;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchQuery;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchQuerySpecification;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchRelevantWbTypesUtil;
import com.devepos.adt.cst.ui.internal.codesearch.FilterName;
import com.sap.adt.tools.core.model.adtcore.IAdtObjectReference;

/**
 * Handler implementation for code search command
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchHandler extends AbstractHandler implements ISearchPageListener {

  private static final String EXACT_NAME_MODIFIER = "<";
  private static final String FILTER_VALUE_DELIMITER = ",";
  private static final String FILTER_VALUES_PATTERN = "%s:%s";
  private static List<String> POSSIBLE_TYPES_FILTER_VALUES;
  static {
    POSSIBLE_TYPES_FILTER_VALUES = Arrays.asList("CLAS", "INTF", "PROG", "TYPE", "DDLS", "DDLX",
        "DCLS", "BDEF", "XSLT", "FUGR");
  }

  private CodeSearchQuery searchQuery;

  private class AdtObjectSelectionToQueryConverter {
    private CodeSearchQuerySpecification querySpecs;
    private List<IAdtObject> adtObjects;
    private List<String> packages;
    private List<String> objectNames;
    private Set<String> objectTypes;

    public AdtObjectSelectionToQueryConverter(List<IAdtObject> adtObjects) {
      this.adtObjects = adtObjects;
      querySpecs = new CodeSearchQuerySpecification();
    }

    public CodeSearchQuery convert() {
      objectNames = new ArrayList<>();
      packages = new ArrayList<>();
      objectTypes = new HashSet<>();
      IProject project = adtObjects.get(0).getProject();

      collectObjectInformation(project);

      StringBuffer filterString = new StringBuffer();

      querySpecs.setIgnoreCaseCheck(true);
      if (!objectNames.isEmpty()) {
        querySpecs.setObjectNames(String.join(" ", objectNames));
      }
      if (!packages.isEmpty() && objectNames.isEmpty()) {
        filterString.append(String.format(FILTER_VALUES_PATTERN, FilterName.PACKAGE
            .getContentAssistName(), String.join(FILTER_VALUE_DELIMITER, packages)));
      }
      if (!objectTypes.isEmpty()) {
        if (filterString.length() > 0) {
          filterString.append(" ");
        }
        filterString.append(String.format(FILTER_VALUES_PATTERN, FilterName.OBJECT_TYPE
            .getContentAssistName(), String.join(FILTER_VALUE_DELIMITER, objectTypes)));
      }
      querySpecs.setObjectScopeFilters(null, filterString.toString());

      if (project != null) {
        querySpecs.setProjectProvider(AbapProjectProviderAccessor.getProviderForDestination(
            DestinationUtil.getDestinationId(project)));
      }
      return new CodeSearchQuery(querySpecs);
    }

    private void collectObjectInformation(IProject project) {
      List<String> relevantWbTypes = CodeSearchRelevantWbTypesUtil.getRelevantTypesForHandler();

      for (IAdtObject adtObj : adtObjects) {
        IAdtObjectReference adtObjRef = adtObj.getReference();
        if (IAdtObjectTypeConstants.PACKAGE.equals(adtObjRef.getType())) {
          if (objectNames.isEmpty()) {
            packages.add(adtObjRef.getName().toLowerCase());
          }
        } else if (relevantWbTypes.stream().anyMatch(t -> t.equals(adtObjRef.getType()))) {
          collectObjectName(adtObjRef, project);
        }
      }
    }

    private void collectObjectName(final IAdtObjectReference adtObjRef, final IProject project) {
      String mainAdtType = adtObjRef.getType().substring(0, 4);
      objectTypes.add(mainAdtType.toLowerCase());

      if (IAdtObjectTypeConstants.FUNCTION_INCLUDE.equals(adtObjRef.getType())
          || IAdtObjectTypeConstants.FUNCTION_MODULE.equals(adtObjRef.getType())) {
        String parentUri = adtObjRef.getParentUri();
        if (parentUri != null) {
          String parentName = URLDecoder.decode(parentUri.substring(parentUri.lastIndexOf("/") + 1),
              Charset.defaultCharset());
          objectNames.add(parentName + EXACT_NAME_MODIFIER);
        }
      } else {
        objectNames.add(adtObjRef.getName().toLowerCase() + EXACT_NAME_MODIFIER);
      }
    }
  }

  private class VirtualFolderToQueryConverter {
    private IVirtualFolderNode node;
    private StringBuffer filterBuffer;
    private CodeSearchQuerySpecification querySpecs;
    private static final String YEAR_DATE_PATTERN = "1.%s...12.%s";

    public VirtualFolderToQueryConverter(IVirtualFolderNode node) {
      this.node = node;
      filterBuffer = new StringBuffer();
      querySpecs = new CodeSearchQuerySpecification();
    }

    public CodeSearchQuery convert() {
      addFiltersToFilterString(node.getUserFilters(), FilterName.OWNER.getContentAssistName());
      addFiltersToFilterString(node.getPackageFilters(), FilterName.PACKAGE.getContentAssistName());
      addFiltersToFilterString(node.getApplicationComponentFilters(),
          FilterName.APPLICATION_COMPONENT.getContentAssistName());
      addTypeFilters();
      addCreatedFilters();

      querySpecs.setIgnoreCaseCheck(true);
      querySpecs.setObjectScopeFilters(null, filterBuffer.toString());
      querySpecs.setObjectNames(node.getSearchString());

      return new CodeSearchQuery(querySpecs);
    }

    private void addCreatedFilters() {
      List<String> createdFilters = node.getCreatedFilters();
      if (createdFilters.isEmpty()) {
        return;
      }

      List<String> createdDatePatterns = new ArrayList<>();
      for (String createdDate : createdFilters) {
        createdDatePatterns.add(String.format(YEAR_DATE_PATTERN, createdDate, createdDate));
      }

      addFiltersToFilterString(createdDatePatterns, FilterName.CREATED_DATE.getContentAssistName());
    }

    private void addFiltersToFilterString(List<String> filters, String filterQualifier) {
      if (filters.isEmpty()) {
        return;
      }
      if (filterBuffer.length() > 0) {
        filterBuffer.append(" ");
      }
      filterBuffer.append(String.format(FILTER_VALUES_PATTERN, filterQualifier, String.join(
          FILTER_VALUE_DELIMITER, filters).toLowerCase()));
    }

    private void addTypeFilters() {
      List<String> typeFilters = node.getTypeFilters();
      if (typeFilters.isEmpty()) {
        return;
      }

      List<String> validTypes = new ArrayList<>();

      for (String filter : typeFilters) {
        if (POSSIBLE_TYPES_FILTER_VALUES.stream().anyMatch(f -> f.equalsIgnoreCase(filter))) {
          validTypes.add(filter);
        }
      }

      addFiltersToFilterString(validTypes, FilterName.OBJECT_TYPE.getContentAssistName());
    };
  }

  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection == null) {
      return null;
    }

    IVirtualFolderNode virtualFolderNode = getVirtualFolderFromSelection(selection);
    if (virtualFolderNode != null) {
      createQueryFromVirtualFolder(virtualFolderNode);
    } else {

      // collect objects from selection
      final List<IAdtObject> selectedObjects = AdtUIUtil.getAdtObjectsFromSelection(false,
          selection);
      if (selectedObjects == null || selectedObjects.isEmpty()) {
        return null;
      }
      createQueryFromAdtObjects(selectedObjects, selection);
    }

    if (searchQuery == null) {
      return null;
    }

    SearchPageUtil.addSearchPageOpenListener(this);
    NewSearchUI.openSearchDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow(),
        CodeSearchDialog.PAGE_ID);
    return null;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public void pageOpened(final ISearchPage searchPage) {
    if (searchPage instanceof IChangeableSearchPage) {
      ((IChangeableSearchPage) searchPage).setInputFromSearchQuery(searchQuery);
    }
  }

  private void createQueryFromAdtObjects(final List<IAdtObject> selectedObjects,
      final ISelection selection) {

    searchQuery = new AdtObjectSelectionToQueryConverter(selectedObjects).convert();
    if (selection instanceof ITextSelection) {
      searchQuery.getQuerySpecs().setPatterns(((ITextSelection) selection).getText());
    }
  }

  private void createQueryFromVirtualFolder(IVirtualFolderNode virtualFolderNode) {
    searchQuery = new VirtualFolderToQueryConverter(virtualFolderNode).convert();
  }

  private IVirtualFolderNode getVirtualFolderFromSelection(ISelection selection) {
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection structuredSelection = (IStructuredSelection) selection;
      if (structuredSelection.size() == 1) {
        return Adapters.adapt(structuredSelection.getFirstElement(), IVirtualFolderNode.class);
      }
    }
    return null;
  }
}
