package com.devepos.adt.cst.ui.internal.handlers;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
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

  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    ISelection selection = HandlerUtil.getCurrentSelection(event);
    // collect objects from selection
    final List<IAdtObject> selectedObjects = AdtUIUtil.getAdtObjectsFromSelection(false, selection);
    if (selectedObjects == null || selectedObjects.isEmpty()) {
      return null;
    }

    createQuery(selectedObjects, selection);
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

  private void createQuery(final List<IAdtObject> selectedObjects, final ISelection selection) {
    List<String> packages = new ArrayList<>();
    List<String> objectNames = new ArrayList<>();
    Set<String> objectTypes = new HashSet<>();
    IProject project = selectedObjects.get(0).getProject();
    List<String> relevantWbTypes = CodeSearchRelevantWbTypesUtil.getRelevantTypesForHandler();

    for (IAdtObject adtObj : selectedObjects) {
      IAdtObjectReference adtObjRef = adtObj.getReference();
      if (IAdtObjectTypeConstants.PACKAGE.equals(adtObjRef.getType())) {
        if (objectNames.isEmpty()) {
          packages.add(adtObjRef.getName().toLowerCase());
        }
      } else if (relevantWbTypes.stream().anyMatch(t -> t.equals(adtObjRef.getType()))) {
        getTadirObject(objectNames, objectTypes, adtObjRef, project);
      }
    }
    StringBuffer filterString = new StringBuffer();

    CodeSearchQuerySpecification querySpecs = new CodeSearchQuerySpecification();
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

    if (selection instanceof ITextSelection) {
      querySpecs.setPatterns(((ITextSelection) selection).getText());
    }

    searchQuery = new CodeSearchQuery(querySpecs);
  }

  private void getTadirObject(final List<String> objectNames, final Set<String> objectTypes,
      final IAdtObjectReference adtObjRef, final IProject project) {
    String mainAdtType = adtObjRef.getType().substring(0, 4);
    objectTypes.add(mainAdtType.toLowerCase());

    String parentUri = adtObjRef.getParentUri();
    if (parentUri != null) {
      if (IAdtObjectTypeConstants.FUNCTION_INCLUDE.equals(adtObjRef.getType())
          || IAdtObjectTypeConstants.FUNCTION_MODULE.equals(adtObjRef.getType())) {
        String parentName = URLDecoder.decode(parentUri.substring(parentUri.lastIndexOf("/") + 1),
            Charset.defaultCharset());
        objectNames.add(parentName + EXACT_NAME_MODIFIER);
      }
    } else {
      objectNames.add(adtObjRef.getName().toLowerCase() + EXACT_NAME_MODIFIER);
    }
  }
}
