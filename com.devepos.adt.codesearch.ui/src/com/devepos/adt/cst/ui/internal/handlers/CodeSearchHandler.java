package com.devepos.adt.cst.ui.internal.handlers;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.LocalDate;
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
import com.devepos.adt.base.ITadirTypeConstants;
import com.devepos.adt.base.destinations.DestinationUtil;
import com.devepos.adt.base.ui.adtobject.IAdtObject;
import com.devepos.adt.base.ui.project.AbapProjectProviderAccessor;
import com.devepos.adt.base.ui.projectexplorer.node.IAbapRepositoryFolderNode;
import com.devepos.adt.base.ui.projectexplorer.virtualfolders.IVirtualFolderNode;
import com.devepos.adt.base.ui.search.IChangeableSearchPage;
import com.devepos.adt.base.ui.search.ISearchPageListener;
import com.devepos.adt.base.ui.search.SearchPageUtil;
import com.devepos.adt.base.ui.util.AdtUIUtil;
import com.devepos.adt.base.util.StringUtil;
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

  private CodeSearchQuery searchQuery;

  private static class AbapRepositoryFolderToQueryConverter {
    private final IAbapRepositoryFolderNode node;

    private final StringBuffer filterBuffer;
    private final CodeSearchQuerySpecification querySpecs;

    public AbapRepositoryFolderToQueryConverter(final IAbapRepositoryFolderNode node) {
      this.node = node;
      filterBuffer = new StringBuffer();
      querySpecs = new CodeSearchQuerySpecification();
    }

    public CodeSearchQuery convert() {
      addFiltersToFilterString(node.getUser(), FilterName.OWNER.getContentAssistName());
      addFiltersToFilterString(node.getPackages(), FilterName.PACKAGE.getContentAssistName());
      addTypeFilters(FilterName.OBJECT_TYPE.getContentAssistName());

      querySpecs.setIgnoreCaseCheck(true);
      querySpecs.setObjectScopeFilters(null, filterBuffer.toString());

      return new CodeSearchQuery(querySpecs);
    }

    private void addTypeFilters(String filterQualifier) {
      String category = node.getCategory();
      if (!StringUtil.isEmpty(category)) {
        if (IAbapRepositoryFolderNode.CATEGORY_DICTIONARY.equals(category)) {
          addFiltersToFilterString(ITadirTypeConstants.DATA_DEFINITION, filterQualifier);
        } else if (IAbapRepositoryFolderNode.CATEGORY_SOURCE_LIB.equals(category)) {
          addFiltersToFilterString(CodeSearchRelevantWbTypesUtil.getSourceCodeLibraryTypeFilters(),
              FilterName.OBJECT_TYPE.getContentAssistName());
        }
        return;
      }
      String type = node.getType();
      if (!StringUtil.isEmpty(type)) {
        switch (type) {
        case IAdtObjectTypeConstants.DATA_DEFINITION:
          addFiltersToFilterString(ITadirTypeConstants.DATA_DEFINITION, filterQualifier);
        case IAdtObjectTypeConstants.CLASS:
          addFiltersToFilterString(ITadirTypeConstants.CLASS, filterQualifier);
        case IAdtObjectTypeConstants.INTERFACE:
          addFiltersToFilterString(ITadirTypeConstants.INTERFACE, filterQualifier);
        case IAdtObjectTypeConstants.FUNCTION_GROUP:
          addFiltersToFilterString(ITadirTypeConstants.FUNCTION_GROUP, filterQualifier);
        case IAdtObjectTypeConstants.PROGRAM:
          addFiltersToFilterString(ITadirTypeConstants.PROGRAM, filterQualifier);
        case IAdtObjectTypeConstants.PROGRAM_INCLUDE:
          addFiltersToFilterString(ITadirTypeConstants.PROGRAM, filterQualifier);
        case IAdtObjectTypeConstants.SIMPLE_TRANSFORMATION:
          addFiltersToFilterString(ITadirTypeConstants.SIMPLE_TRANSFORMATION, filterQualifier);
        }
      }
    }

    private void addFiltersToFilterString(final List<String> filters,
        final String filterQualifier) {
      if (filters == null || filters.isEmpty()) {
        return;
      }
      addFiltersToFilterString(String.join(FILTER_VALUE_DELIMITER, filters), filterQualifier);
    }

    private void addFiltersToFilterString(final String filter, final String filterQualifier) {
      if (StringUtil.isEmpty(filter)) {
        return;
      }
      if (filterBuffer.length() > 0) {
        filterBuffer.append(" ");
      }
      filterBuffer.append(String.format(FILTER_VALUES_PATTERN, filterQualifier, filter
          .toLowerCase()));
    }
  }

  private static class AdtObjectSelectionToQueryConverter {
    private final CodeSearchQuerySpecification querySpecs;
    private final List<IAdtObject> adtObjects;
    private List<String> packages;
    private List<String> objectNames;
    private Set<String> objectTypes;

    public AdtObjectSelectionToQueryConverter(final List<IAdtObject> adtObjects) {
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

    private void collectObjectInformation(final IProject project) {
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

  private static class VirtualFolderToQueryConverter {
    private static final String YEAR_DATE_PATTERN = "1.%d...12.%d";
    private static final String MONTH_YEAR_DATE_PATTERN = "%d.%d";
    private static final String FULL_DATE_PATTERN = "%s.%d.%d";

    private final IVirtualFolderNode node;

    private final StringBuffer filterBuffer;
    private final CodeSearchQuerySpecification querySpecs;

    public VirtualFolderToQueryConverter(final IVirtualFolderNode node) {
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
      List<String> createdDatePatterns = new ArrayList<>();

      List<String> dateFilters = node.getCreatedDateFilters();
      if (!dateFilters.isEmpty()) {
        for (String date : dateFilters) {
          createdDatePatterns.add(String.format(FULL_DATE_PATTERN, Integer.parseInt(date.substring(
              6, 8)), Integer.parseInt(date.substring(4, 6)), Integer.parseInt(date.substring(0,
                  4))));
        }
      } else {
        List<Integer> createdYearFilters = node.getCreatedYearFilters();
        List<Integer> createdMonthFilters = node.getCreatedMonthFilters();
        if (createdYearFilters.isEmpty() && createdMonthFilters.isEmpty()) {
          return;
        }

        if (createdMonthFilters.isEmpty()) {
          for (int createdDate : createdYearFilters) {
            createdDatePatterns.add(String.format(YEAR_DATE_PATTERN, createdDate, createdDate));
          }
        } else {
          createdYearFilters = createdYearFilters.isEmpty() ? Arrays.asList(LocalDate.now()
              .getYear()) : createdYearFilters;

          for (int year : createdYearFilters) {
            for (int month : createdMonthFilters) {
              createdDatePatterns.add(String.format(MONTH_YEAR_DATE_PATTERN, month, year));
            }
          }
        }
      }

      addFiltersToFilterString(createdDatePatterns, FilterName.CREATED_DATE.getContentAssistName());
    }

    private void addFiltersToFilterString(final List<String> filters,
        final String filterQualifier) {
      if (filters == null || filters.isEmpty()) {
        return;
      }
      if (filterBuffer.length() > 0) {
        filterBuffer.append(" ");
      }
      filterBuffer.append(String.format(FILTER_VALUES_PATTERN, filterQualifier, String.join(
          FILTER_VALUE_DELIMITER, filters).toLowerCase()));
    }

    private void addTypeFilters() {
      addFiltersToFilterString(CodeSearchRelevantWbTypesUtil.extractValidTypeFilters(node
          .getTypeFilters()), FilterName.OBJECT_TYPE.getContentAssistName());
    }
  }

  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    searchQuery = null;
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection == null) {
      return null;
    }

    if (selection instanceof IStructuredSelection) {
      IStructuredSelection structSelection = (IStructuredSelection) selection;
      if (structSelection.size() > 1) {
        // collect objects from selection
        final List<IAdtObject> selectedObjects = AdtUIUtil.getAdtObjectsFromSelection(false,
            selection);
        if (selectedObjects == null || selectedObjects.isEmpty()) {
          return null;
        }
        createQueryFromAdtObjects(selectedObjects, selection);
      } else {
        Object selectedObject = structSelection.getFirstElement();
        IVirtualFolderNode virtualFolderNode = Adapters.adapt(selectedObject,
            IVirtualFolderNode.class);
        if (virtualFolderNode != null) {
          searchQuery = new VirtualFolderToQueryConverter(virtualFolderNode).convert();
        } else {
          IAbapRepositoryFolderNode repositoryFolder = Adapters.adapt(selectedObject,
              IAbapRepositoryFolderNode.class);
          if (repositoryFolder != null) {
            searchQuery = new AbapRepositoryFolderToQueryConverter(repositoryFolder).convert();
          } else {
            IAdtObject adtObject = Adapters.adapt(selectedObject, IAdtObject.class);
            createQueryFromAdtObjects(adtObject == null ? null : Arrays.asList(adtObject),
                selection);
          }
        }
      }
    } else {
      createQueryFromAdtObjects(AdtUIUtil.getAdtObjectsFromSelection(false, selection), selection);
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

    if (selectedObjects == null || selectedObjects.isEmpty()) {
      return;
    }
    searchQuery = new AdtObjectSelectionToQueryConverter(selectedObjects).convert();
    if (selection instanceof ITextSelection) {
      searchQuery.getQuerySpecs().setPatterns(((ITextSelection) selection).getText());
    }
  }

}
