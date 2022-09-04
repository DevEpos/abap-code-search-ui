package com.devepos.adt.cst.ui.internal.codesearch.result;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;

import com.devepos.adt.base.ui.tree.ICollectionTreeNode;
import com.devepos.adt.base.ui.tree.ITreeNode;
import com.devepos.adt.base.ui.tree.PackageNode;

class MatchViewerUpdater {
  private final Object[] updatedMatchNodes;
  private final CodeSearchResult result;
  private final Set<ICollectionTreeNode> parentNodesWithoutMatches;
  private final Set<ICollectionTreeNode> nodesToRefresh;
  private boolean isRootNodeAdjusted;
  private final CodeSearchResultPage resultPage;
  private final StructuredViewer viewer;

  public MatchViewerUpdater(final Object[] updatedMatchNodes, final CodeSearchResult result,
      final StructuredViewer viewer, final CodeSearchResultPage resultPage) {
    this.updatedMatchNodes = updatedMatchNodes;
    this.result = result;
    this.viewer = viewer;
    this.resultPage = resultPage;
    parentNodesWithoutMatches = new HashSet<>();
    nodesToRefresh = new HashSet<>();
  }

  public void update() {
    handleChangedElements();
    removeNodesWithoutChildren();
    refreshViewer();
  }

  private void handleChangedElements() {
    for (Object obj : updatedMatchNodes) {
      // check if element still has matches
      if (resultPage.getDisplayedMatchCount(obj) <= 0) {
        ITreeNode matchNode = (ITreeNode) obj;
        ICollectionTreeNode parentNode = matchNode.getParent();
        if (parentNode != null) {
          result.removeSearchResult(matchNode);
          parentNode.removeChild(matchNode);
          parentNode.setNodeValue(null);
          parentNodesWithoutMatches.add(parentNode);
        }
      }
    }
  }

  private void refreshViewer() {
    if (viewer instanceof TableViewer) {
      viewer.refresh();
      return;
    }
    updateMatchCounts();

    if (!isRootNodeAdjusted && !nodesToRefresh.isEmpty()) {
      for (ICollectionTreeNode node : nodesToRefresh) {
        viewer.refresh(node);
      }
    } else {
      // refresh all nodes
      viewer.refresh();
    }
  }

  private void removeNodesWithoutChildren() {
    // check parents if they still have children
    for (ICollectionTreeNode parent : parentNodesWithoutMatches) {

      while (parent != null) {
        /*
         * The whole viewer needs to be refreshed under the following conditions
         * - node was removed from the root node
         * - node was removed from a package node but the package grouping is turned off
         */
        if (!isRootNodeAdjusted && parent == result.getResultTree() || parent instanceof PackageNode
            && !resultPage.isPackageGroupingEnabled()) {
          isRootNodeAdjusted = true;
        }

        if (parent.hasChildren()) {
          nodesToRefresh.add(parent);
          break;
        }

        // maybe the node was added in an earlier iteration where it still had children
        nodesToRefresh.remove(parent);

        ICollectionTreeNode child = parent;
        parent = parent.getParent();

        if (parent != null) {
          // remove the child
          result.removeChildeNode(parent, child);
        }
      }
    }
  }

  private void updateAndPropagateMatchCount(final ICollectionTreeNode collectionNode) {
    if (collectionNode == null) {
      return;
    }
    if (collectionNode.getNodeValue() == null) {
      collectionNode.setNodeValue(collectionNode.getChildren().size());
      updateAndPropagateMatchCount(collectionNode.getParent());
    } else {
      int matchCount = 0;
      for (ITreeNode childNode : collectionNode.getChildren()) {
        matchCount += (Integer) childNode.getNodeValue();
      }
      collectionNode.setNodeValue(matchCount);
      updateAndPropagateMatchCount(collectionNode.getParent());
    }
  }

  private void updateMatchCounts() {
    Set<ICollectionTreeNode> tmpNodes = new HashSet<>();

    for (ICollectionTreeNode node : nodesToRefresh) {
      updateAndPropagateMatchCount(node);

      if (!isRootNodeAdjusted) {
        ICollectionTreeNode parent = node.getParent();
        while (parent != null) {
          if (parent.getParent() != null) {
            tmpNodes.add(parent);
          }
          parent = parent.getParent();
        }
      }
    }
    nodesToRefresh.addAll(tmpNodes);
  }
}