package com.devepos.adt.cst.ui.internal.codesearch;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;

import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseStrings;
import com.devepos.adt.base.ui.project.AbapProjectProviderAccessor;
import com.devepos.adt.base.ui.project.IAbapProjectProvider;
import com.devepos.adt.base.ui.project.ProjectUtil;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.devepos.adt.cst.search.CodeSearchFactory;
import com.devepos.adt.cst.search.ICodeSearchService;
import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;

/**
 * Query for running Code Search
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchQuery implements ISearchQuery {

  private IAbapProjectProvider projectProvider;
  private CodeSearchResult searchResult;
  private CodeSearchQuerySpecification querySpecs;

  public CodeSearchQuery(final CodeSearchQuerySpecification querySpecs) {
    searchResult = new CodeSearchResult(this);
    this.querySpecs = querySpecs;
  }

  @Override
  public boolean canRerun() {
    return true;
  }

  @Override
  public boolean canRunInBackground() {
    return true;
  }

  @Override
  public String getLabel() {
    return "ABAP Code Search+ Query";
  }

  public IAbapProjectProvider getProjectProvider() {
    return projectProvider;
  }

  public CodeSearchQuerySpecification getQuerySpecification() {
    return querySpecs;
  }

  @Override
  public ISearchResult getSearchResult() {
    return searchResult;
  }

  @Override
  public IStatus run(final IProgressMonitor monitor) throws OperationCanceledException {
    searchResult.removeAll();

    // check project availability
    IAbapProjectProvider projectProvider = querySpecs.getProjectProvider();
    if (projectProvider == null) {
      projectProvider = AbapProjectProviderAccessor.getProviderForDestination(querySpecs
          .getDestinationId());
      querySpecs.setProjectProvider(projectProvider);
    }
    final String destinationId = querySpecs.getDestinationId();
    if (destinationId == null) {
      return new Status(IStatus.ERROR, CodeSearchUIPlugin.PLUGIN_ID, AdtBaseUIResources.format(
          IAdtBaseStrings.Destinations_DestinationNotValid_xmsg, destinationId));
    }
    IStatus logonStatus = ProjectUtil.ensureLoggedOnToProject(projectProvider.getProject());
    if (!logonStatus.isOK()) {
      return logonStatus;
    }

    // build parameters map for request
    ICodeSearchService service = CodeSearchFactory.getCodeSearchService();
    ICodeSearchResult serviceSearchResult = service.search(destinationId, querySpecs
        .buildSearchUriParameters(), monitor);

    searchResult.setResult(serviceSearchResult);
    return Status.OK_STATUS;
  }

  public void setProjectProvider(final IAbapProjectProvider projectProvider) {
    this.projectProvider = projectProvider;

  }

}
