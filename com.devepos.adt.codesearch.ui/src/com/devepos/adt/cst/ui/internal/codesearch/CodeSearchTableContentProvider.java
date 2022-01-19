package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;

import com.devepos.adt.base.ui.tree.ITreeNode;

public class CodeSearchTableContentProvider implements IStructuredContentProvider {
  private static final Object[] EMPTY_ARRAY = new Object[0];

  private final CodeSearchResultPage resultPage;

  /**
   * @param codeSearchResultPage
   */
  public CodeSearchTableContentProvider(final CodeSearchResultPage resultPage) {
    this.resultPage = resultPage;
  }

  @Override
  public Object[] getElements(final Object inputElement) {
    CodeSearchResult result = (CodeSearchResult) resultPage.getInput();
    if (result != null) {
      List<Object> matches = new ArrayList<>();
      // TODO: consider filters (not yet implemented)
      for (ITreeNode obj : result.getFlatResult()) {
        if (result.getMatchCount(obj) > 0) {
          matches.add(obj);
        }
      }
      return matches.toArray();
    }
    return EMPTY_ARRAY;
  }
}
