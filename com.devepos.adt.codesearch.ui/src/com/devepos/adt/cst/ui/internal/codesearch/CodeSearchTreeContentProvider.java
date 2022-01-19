package com.devepos.adt.cst.ui.internal.codesearch;

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
    var result = (CodeSearchResult) resultPage.getInput();
    if (result != null) {
      var rootNode = result.getResultTree();
      if (rootNode != null) {
        return rootNode.getChildren().toArray();
      }
    }
    return EMPTY_ARRAY;
  }

}