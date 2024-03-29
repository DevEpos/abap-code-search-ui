package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;

import com.devepos.adt.base.project.IAbapProjectProvider;
import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseStrings;
import com.devepos.adt.base.ui.project.AbapProjectProviderAccessor;
import com.devepos.adt.base.ui.project.ProjectUtil;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScope;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters;
import com.devepos.adt.cst.search.CodeSearchFactory;
import com.devepos.adt.cst.search.ICodeSearchService;
import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;
import com.devepos.adt.cst.ui.internal.codesearch.result.CodeSearchResult;
import com.devepos.adt.cst.ui.internal.messages.Messages;

/**
 * Query for running Code Search
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchQuery implements ISearchQuery {

  private static final int FALLBACK_PACKAGE_SIZE = 100;
  private static final float WORK_UNITS_PACKAGE = 10.0f;

  private final CodeSearchResult searchResult;
  private CodeSearchQuerySpecification querySpecs;
  private boolean continueQuery;
  private boolean isContinueForCurrentExecution;
  private boolean finished;
  private int currentOffset;

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
    return Messages.CodeSearchQuery_queryName_xlbl;
  }

  public IAbapProjectProvider getProjectProvider() {
    return querySpecs.getProjectProvider();
  }

  public CodeSearchQuerySpecification getQuerySpecs() {
    return querySpecs;
  }

  @Override
  public ISearchResult getSearchResult() {
    return searchResult;
  }

  public boolean isFinished() {
    return finished;
  }

  @Override
  public IStatus run(final IProgressMonitor monitor) throws OperationCanceledException {
    finished = false;
    isContinueForCurrentExecution = continueQuery;
    continueQuery = false;

    if (!isContinueForCurrentExecution) {
      searchResult.reset();
    }

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

    ICodeSearchService service = CodeSearchFactory.getCodeSearchService();

    // create the scope to retrieve the number of objects that need / should be searched
    ICodeSearchScopeParameters scopeParameters = querySpecs.createScopeParameters();
    ICodeSearchScope scope = service.createScope(destinationId, scopeParameters, monitor);

    if (scope == null || scope.getObjectCount() <= 0) {
      searchResult.setNoObjectsInScope();
    } else {
      searchResult.setObjectScopeCount(scope.getObjectCount());
      startSearchingWithScope(monitor, scope, service, destinationId);
    }
    finished = true;
    return Status.OK_STATUS;
  }

  public void setContinue(final boolean continueQuery) {
    this.continueQuery = continueQuery;
  }

  public void setQuerySpecs(final CodeSearchQuerySpecification querySpecs) {
    this.querySpecs = querySpecs;
  }

  private void setInitialWorkedValue(final IProgressMonitor monitor, final ICodeSearchScope scope,
      final int workUnits) {
    // update current percentage of progress
    if (currentOffset > 0) {
      int workedUnits = (int) Math.ceil((scope.getObjectCount() - currentOffset)
          / WORK_UNITS_PACKAGE);
      monitor.worked(workUnits - workedUnits);
    }
  }

  private void startSearchingWithScope(final IProgressMonitor monitor, final ICodeSearchScope scope,
      final ICodeSearchService service, final String destinationId) {

    Map<String, Object> uriParams = querySpecs.buildSearchUriParameters();
    uriParams.put(SearchParameter.SCOPE_ID.getUriName(), scope.getId());
    uriParams.put(SearchParameter.MAX_OBJECTS.getUriName(), FALLBACK_PACKAGE_SIZE);

    if (!isContinueForCurrentExecution) {
      currentOffset = 0;
    }
    int workUnits = (int) Math.ceil(scope.getObjectCount() / WORK_UNITS_PACKAGE);
    if (workUnits <= 0) {
      workUnits = 1;
    }
    monitor.beginTask("", workUnits);
    setInitialWorkedValue(monitor, scope, workUnits);

    while (currentOffset < scope.getObjectCount()) {
      uriParams.put(SearchParameter.SCOPE_OFFSET.getUriName(), currentOffset);

      // determine overall client runtime
      var startTime = System.currentTimeMillis();
      ICodeSearchResult serviceSearchResult = service.search(destinationId, uriParams, monitor);
      searchResult.addResult(serviceSearchResult, System.currentTimeMillis() - startTime);

      monitor.worked((int) Math.ceil(serviceSearchResult.getNumberOfSearchedObjects()
          / WORK_UNITS_PACKAGE));
      currentOffset += serviceSearchResult.getNumberOfSearchedObjects();
    }
    monitor.done();
  }
}
