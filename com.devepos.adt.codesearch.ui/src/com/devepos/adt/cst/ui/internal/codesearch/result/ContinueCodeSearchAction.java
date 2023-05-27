package com.devepos.adt.cst.ui.internal.codesearch.result;

import org.eclipse.jface.action.Action;
import org.eclipse.search.ui.NewSearchUI;

import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseImages;
import com.devepos.adt.base.ui.search.ISearchResultPageExtension;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchQuery;
import com.devepos.adt.cst.ui.internal.messages.Messages;

/**
 * Action to continue a cancelled or interrupted {@link CodeSearchQuery}
 * 
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class ContinueCodeSearchAction extends Action {

  private final ISearchResultPageExtension<CodeSearchQuery> searchResultPage;

  public ContinueCodeSearchAction(
      final ISearchResultPageExtension<CodeSearchQuery> searchResultPage) {
    super(Messages.ContinueCodeSearchAction_actionName_xlbl, AdtBaseUIResources.getImageDescriptor(
        IAdtBaseImages.CONTINUE));
    this.searchResultPage = searchResultPage;
  }

  @Override
  public void run() {
    var query = searchResultPage.getSearchQuery();
    if (query == null || NewSearchUI.isQueryRunning(query)) {
      return;
    }
    query.setContinue(true);
    NewSearchUI.runQueryInBackground(query);
  }

}
