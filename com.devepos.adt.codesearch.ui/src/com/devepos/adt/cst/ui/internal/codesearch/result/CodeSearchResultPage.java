package com.devepos.adt.cst.ui.internal.codesearch.result;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.search.ui.IContextMenuConstants;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.eclipse.search.ui.text.Match;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.PreferencesUtil;

import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.ContextHelper;
import com.devepos.adt.base.ui.IAdtBaseImages;
import com.devepos.adt.base.ui.IGeneralCommandConstants;
import com.devepos.adt.base.ui.IGeneralContextConstants;
import com.devepos.adt.base.ui.action.ActionFactory;
import com.devepos.adt.base.ui.action.CommandFactory;
import com.devepos.adt.base.ui.action.PreferenceToggleAction;
import com.devepos.adt.base.ui.search.ISearchResultPageExtension;
import com.devepos.adt.base.ui.tree.IAdtObjectReferenceNode;
import com.devepos.adt.base.ui.tree.ITreeNode;
import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchDialog;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchQuery;
import com.devepos.adt.cst.ui.internal.messages.Messages;
import com.devepos.adt.cst.ui.internal.preferences.CodeSearchPreferencesPage;
import com.sap.adt.tools.core.model.adtcore.IAdtCoreFactory;
import com.sap.adt.tools.core.model.adtcore.IAdtObjectReference;
import com.sap.adt.tools.core.ui.navigation.AdtNavigationServiceFactory;

/**
 * Result page of a ABAP Code Search query
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchResultPage extends AbstractTextSearchViewPage implements
    ISearchResultPageExtension<CodeSearchQuery> {

  private static final String GROUP_BY_PACKAGE_PREF = "codeSearch.result.groupByPackageEnabled";
  private static final String GROUP_GROUPING = "com.devepos.adt.cst.searchResult.grouping";
  private IStructuredContentProvider contentProvider;
  private IAction openPreferencesAction;
  private ContextHelper contextHelper;
  private PreferenceToggleAction groupByPackageAction;
  private IPropertyChangeListener prefChangeListener;

  public CodeSearchResultPage() {
    super();

    prefChangeListener = l -> {
      if (l.getProperty().equals(GROUP_BY_PACKAGE_PREF)) {
        StructuredViewer viewer = getViewer();
        if (viewer instanceof TreeViewer) {
          viewer.refresh();
        }
      }
    };
    CodeSearchUIPlugin.getDefault()
        .getPreferenceStore()
        .addPropertyChangeListener(prefChangeListener);
    initializeActions();
  }

  @Override
  public void createControl(final Composite parent) {
    super.createControl(parent);

    contextHelper = ContextHelper.createForServiceLocator(getSite());
    contextHelper.activateAbapContext();
    contextHelper.activateContext(IGeneralContextConstants.SEARCH_PAGE_VIEWS);
  }

  @Override
  public void dispose() {
    if (contextHelper != null) {
      contextHelper.deactivateAllContexts();
    }
    if (prefChangeListener != null) {
      CodeSearchUIPlugin.getDefault()
          .getPreferenceStore()
          .removePropertyChangeListener(prefChangeListener);
    }
    super.dispose();
  }

  @Override
  public String getSearchPageId() {
    return CodeSearchDialog.PAGE_ID;
  }

  @Override
  public CodeSearchQuery getSearchQuery() {
    return (CodeSearchQuery) getInput().getQuery();
  }

  public boolean isPackageGroupingEnabled() {
    return groupByPackageAction != null ? groupByPackageAction.isChecked() : false;
  }

  @Override
  public void setActionBars(final IActionBars actionBars) {
    actionBars.getMenuManager().add(openPreferencesAction);
  }

  @Override
  protected void clear() {
    getViewer().refresh();
  }

  @Override
  protected void configureTableViewer(final TableViewer viewer) {
    contentProvider = new CodeSearchTableContentProvider(this);
    viewer.setContentProvider(contentProvider);
    viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(
        new CodeSearchResultLabelProvider()));
  }

  @Override
  protected void configureTreeViewer(final TreeViewer viewer) {
    contentProvider = new CodeSearchTreeContentProvider(this);
    viewer.setContentProvider(contentProvider);
    viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(
        new CodeSearchResultLabelProvider()));
    viewer.setComparator(new ViewerComparator() {

      @Override
      public int compare(final Viewer viewer, final Object e1, final Object e2) {
        if (e1 instanceof SearchMatchNode) {
          return 0;
        }
        return ((ITreeNode) e1).getDisplayName().compareTo(((ITreeNode) e2).getDisplayName());
      }

    });
  }

  @Override
  protected void elementsChanged(final Object[] updatedElements) {
    new MatchViewerUpdater(updatedElements, (CodeSearchResult) getInput(), getViewer(), this)
        .update();
  }

  @Override
  protected void fillToolbar(final IToolBarManager tbm) {
    super.fillToolbar(tbm);
    tbm.appendToGroup(IContextMenuConstants.GROUP_NEW, CommandFactory.createContribItemById(
        IGeneralCommandConstants.OPEN_QUERY_IN_SEARCH_DIALOG, false, null));
    if (getLayout() != FLAG_LAYOUT_FLAT) {
      tbm.appendToGroup(IContextMenuConstants.GROUP_VIEWER_SETUP, new Separator(GROUP_GROUPING));
      tbm.appendToGroup(GROUP_GROUPING, groupByPackageAction);
    }
  }

  @Override
  protected StructuredViewer getViewer() {
    // override to make available in package
    return super.getViewer();
  }

  @Override
  protected void handleOpen(final OpenEvent event) {
    if (event.getSelection() instanceof IStructuredSelection) {
      IStructuredSelection selection = (IStructuredSelection) event.getSelection();
      Object element = selection.getFirstElement();
      if (element != null && getDisplayedMatchCount(element) == 0) {
        boolean navigated = navigateToElement(element, OpenStrategy.activateOnOpen());
        if (navigated) {
          return;
        }
      }
    }
    super.handleOpen(event);
  }

  @Override
  protected void showMatch(final Match match, final int currentOffset, final int currentLength,
      final boolean activate) throws PartInitException {
    Object element = match.getElement();
    navigateToElement(element, activate);
  }

  private void initializeActions() {
    openPreferencesAction = ActionFactory.createAction(
        Messages.CodeSearchResultPage_openSearchPreferencesAction_xlbl, null, () -> {
          PreferencesUtil.createPreferenceDialogOn(null, CodeSearchPreferencesPage.PAGE_ID,
              new String[] { CodeSearchPreferencesPage.PAGE_ID }, (Object) null).open();
        });
    groupByPackageAction = new PreferenceToggleAction(
        Messages.CodeSearchResultPage_groupByPackageAction_xtol, AdtBaseUIResources
            .getImageDescriptor(IAdtBaseImages.PACKAGE), GROUP_BY_PACKAGE_PREF, true,
        CodeSearchUIPlugin.getDefault().getPreferenceStore());
  }

  private boolean navigateToElement(final Object element, final boolean activate) {
    IAdtObjectReference adtObjRef = null;
    if (element instanceof IAdtObjectReferenceNode) {
      adtObjRef = ((IAdtObjectReferenceNode) element).getObjectReference();
    } else if (element instanceof SearchMatchNode) {
      SearchMatchNode matchNode = (SearchMatchNode) element;
      adtObjRef = IAdtCoreFactory.eINSTANCE.createAdtObjectReference();
      adtObjRef.setUri(matchNode.getUri());
    }

    if (adtObjRef != null) {
      IProject project = ((CodeSearchQuery) getInput().getQuery()).getProjectProvider()
          .getProject();
      AdtNavigationServiceFactory.createNavigationService().navigate(project, adtObjRef, activate);
      return true;
    }
    return false;
  }
}
