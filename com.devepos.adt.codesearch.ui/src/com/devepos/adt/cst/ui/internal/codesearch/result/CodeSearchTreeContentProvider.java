package com.devepos.adt.cst.ui.internal.codesearch.result;

import java.util.ArrayList;
import java.util.List;

import com.devepos.adt.base.ui.tree.ICollectionTreeNode;
import com.devepos.adt.base.ui.tree.ITreeNode;
import com.devepos.adt.base.ui.tree.PackageNode;
import com.devepos.adt.base.ui.tree.TreeContentProvider;

public class CodeSearchTreeContentProvider extends TreeContentProvider {

  private final CodeSearchResultPage resultPage;

  /**
   * @param codeSearchResultPage
   */
  public CodeSearchTreeContentProvider(final CodeSearchResultPage resultPage) {
    this.resultPage = resultPage;
  }

  @Override
  public Object[] getElements(final Object inputElement) {
    CodeSearchResult result = (CodeSearchResult) resultPage.getInput();
    if (result != null) {
      ICollectionTreeNode rootNode = result.getResultTree();
      if (rootNode == null) {
        return EMPTY_ARRAY;
      }
      if (resultPage.isPackageGroupingEnabled()) {
        return rootNode.getChildren().toArray();
      }
      return getNonPackageResults(rootNode.getChildren());
    }
    return EMPTY_ARRAY;
  }

  private Object[] getNonPackageResults(final List<ITreeNode> children) {
    List<ITreeNode> nonPackageNodes = new ArrayList<>();
    collectNonPackageResults(children, nonPackageNodes);
    return nonPackageNodes.toArray();
  }

  private void collectNonPackageResults(final List<ITreeNode> nodes,
      final List<ITreeNode> nonPackageNodes) {
    for (ITreeNode node : nodes) {
      if (node instanceof PackageNode) {
        collectNonPackageResults(((ICollectionTreeNode) node).getChildren(), nonPackageNodes);
      } else {
        nonPackageNodes.add(node);
      }
    }
  }

}