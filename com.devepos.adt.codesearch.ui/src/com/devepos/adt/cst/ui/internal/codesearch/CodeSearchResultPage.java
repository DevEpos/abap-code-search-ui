package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.ui.IContextMenuConstants;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.eclipse.search.ui.text.Match;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.PreferencesUtil;

import com.devepos.adt.base.IAdtObjectTypeConstants;
import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseImages;
import com.devepos.adt.base.ui.IGeneralCommandConstants;
import com.devepos.adt.base.ui.StylerFactory;
import com.devepos.adt.base.ui.action.ActionFactory;
import com.devepos.adt.base.ui.action.CommandFactory;
import com.devepos.adt.base.ui.search.ISearchResultPageExtension;
import com.devepos.adt.base.ui.tree.IAdtObjectReferenceNode;
import com.devepos.adt.base.ui.tree.ICollectionTreeNode;
import com.devepos.adt.base.ui.tree.IStyledTreeNode;
import com.devepos.adt.base.ui.tree.ITreeNode;
import com.devepos.adt.base.ui.tree.LoadingTreeItemsNode;
import com.devepos.adt.base.ui.util.AdtTypeUtil;
import com.devepos.adt.cst.ui.internal.preferences.CodeSearchPreferencesPage;
import com.sap.adt.tools.core.model.adtcore.IAdtCoreFactory;
import com.sap.adt.tools.core.model.adtcore.IAdtObjectReference;
import com.sap.adt.tools.core.ui.navigation.AdtNavigationServiceFactory;

public class CodeSearchResultPage extends AbstractTextSearchViewPage implements
    ISearchResultPageExtension<CodeSearchQuery> {

  private IStructuredContentProvider contentProvider;
  private IAction openPreferencesAction;

  public CodeSearchResultPage() {
    super();
  }

  /**
   * Custom view label provider for the Result Tree
   *
   * @author stockbal
   */
  private static class ViewLabelProvider extends LabelProvider implements ILabelProvider,
      IStyledLabelProvider {

    @Override
    public Image getImage(final Object element) {
      Image image;
      final ITreeNode searchResult = (ITreeNode) element;
      image = searchResult.getImage();
      if (image == null && element instanceof IAdtObjectReferenceNode) {
        final IAdtObjectReferenceNode adtObjRefNode = (IAdtObjectReferenceNode) element;
        String adtType = adtObjRefNode.getObjectReference().getType();
        if (adtType.equals(IAdtObjectTypeConstants.CLASS_INCLUDE)) {
          return AdtBaseUIResources.getImage(IAdtBaseImages.NEUTRAL_METHOD);
        }
        image = AdtTypeUtil.getInstance().getTypeImage(adtType);
      }
      return image;
    }

    @Override
    public StyledString getStyledText(final Object element) {
      StyledString text = new StyledString();
      final ITreeNode searchResult = (ITreeNode) element;

      if (element instanceof IStyledTreeNode) {
        text = ((IStyledTreeNode) element).getStyledText();
        if (text == null) {
          text = new StyledString();
        }
      } else {
        if (element instanceof LoadingTreeItemsNode) {
          text.append(searchResult.getDisplayName(), StylerFactory.ITALIC_STYLER);
          return text;
        }
        if (element instanceof IAdtObjectReferenceNode) {
          text.append(searchResult.getDisplayName());
          String type = ((IAdtObjectReferenceNode) element).getAdtObjectType();
          if (!type.equals(IAdtObjectTypeConstants.CLASS_INCLUDE)) {
            String typeDescription = AdtTypeUtil.getInstance().getTypeDescription(type);
            if (typeDescription != null) {
              text.append(" (" + typeDescription + ")", StyledString.QUALIFIER_STYLER);
            }
          }
        }
      }

      return text;
    }

    @Override
    public String getText(final Object element) {
      final ITreeNode searchResult = (ITreeNode) element;

      return searchResult.getName();
    }
  }

  @Override
  public void createControl(final Composite parent) {
    super.createControl(parent);

    initializeActions();
  }

  @Override
  public String getSearchPageId() {
    return CodeSearchDialog.PAGE_ID;
  }

  @Override
  public CodeSearchQuery getSearchQuery() {
    return (CodeSearchQuery) getInput().getQuery();
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
    viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new ViewLabelProvider()));
  }

  @Override
  protected void configureTreeViewer(final TreeViewer viewer) {
    contentProvider = new CodeSearchTreeContentProvider(this);
    viewer.setContentProvider(contentProvider);
    viewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new ViewLabelProvider()));
  }

  @Override
  protected void elementsChanged(final Object[] updatedElements) {
    Set<ITreeNode> parentNodesOfNoMatchNodes = new HashSet<>();
    Set<ITreeNode> nodesToRefresh = new HashSet<>();
    CodeSearchResult result = (CodeSearchResult) getInput();

    for (Object obj : updatedElements) {
      // check if element still has matches
      if (getDisplayedMatchCount(obj) <= 0) {
        ITreeNode matchNode = (ITreeNode) obj;
        ITreeNode parentNode = matchNode.getParent();
        if (parentNode != null) {
          result.removeSearchResult(matchNode);
          parentNodesOfNoMatchNodes.add(parentNode);
        }
      }
    }

    boolean isRootNodeAdjusted = false;
    // check parents if they still have children
    for (ITreeNode parent : parentNodesOfNoMatchNodes) {

      while (parent != null) {
        if (parent instanceof ICollectionTreeNode) {
          ICollectionTreeNode collectionParent = (ICollectionTreeNode) parent;

          if (collectionParent.hasChildren()) {
            nodesToRefresh.add(parent);
            break;
          }
          // maybe the node was added in an earlier iteration where it still had children
          nodesToRefresh.remove(parent);
          parent = collectionParent.getParent();
          if (parent instanceof ICollectionTreeNode) {
            ((ICollectionTreeNode) parent).removeChild(collectionParent);

            // check if we removed a node from the root node, then we have to refresh the
            // whole viewer as the root node is not visible
            if (parent == result.getResultTree()) {
              isRootNodeAdjusted = true;
            }
          }
        }
      }
    }

    StructuredViewer viewer = getViewer();
    if (viewer instanceof TableViewer) {
      viewer.refresh();
    } else {
      if (!isRootNodeAdjusted && !nodesToRefresh.isEmpty()) {
        for (Object node : nodesToRefresh) {
          viewer.refresh(node);
        }
      } else {
        // refresh all nodes
        viewer.refresh();
      }
    }
  }

  @Override
  protected void fillToolbar(final IToolBarManager tbm) {
    super.fillToolbar(tbm);
    tbm.appendToGroup(IContextMenuConstants.GROUP_NEW, CommandFactory.createContribItemById(
        IGeneralCommandConstants.OPEN_QUERY_IN_SEARCH_DIALOG, false, null));
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
    openPreferencesAction = ActionFactory.createAction("Open Code Search preferences", null, () -> {
      PreferencesUtil.createPreferenceDialogOn(null, CodeSearchPreferencesPage.PAGE_ID,
          new String[] { CodeSearchPreferencesPage.PAGE_ID }, (Object) null).open();
    });
  }

  private boolean navigateToElement(final Object element, final boolean activate) {
    IAdtObjectReference adtObjRef = null;
    if (element instanceof IAdtObjectReferenceNode) {
      adtObjRef = ((IAdtObjectReferenceNode) element).getObjectReference();
    } else if (element instanceof SearchMatchNode) {
      adtObjRef = IAdtCoreFactory.eINSTANCE.createAdtObjectReference();
      adtObjRef.setUri(((SearchMatchNode) element).getUri());
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
