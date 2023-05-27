package com.devepos.adt.cst.ui.internal.codesearch.result;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.search.ui.IQueryListener;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.NewSearchUI;

import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchQuery;

/**
 * Holds runtime information of a code search query
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchRuntimeInformation implements IQueryListener {
  private final Set<IRuntimeInfoListener> runtimeListeners = new HashSet<>();
  private int averageDuration;
  private int requestCount;
  private int resultCount;
  private int objectScopeCount;
  private int searchedSourcesCount;
  private float searchedLinesOfCode;

  private int searchedObjectsCount;

  private String systemId;
  private int overallServerTimeInMs;
  private long overallClientTimeInMs;
  private final CodeSearchQuery searchQuery;

  public CodeSearchRuntimeInformation(final CodeSearchQuery searchQuery) {
    this.searchQuery = searchQuery;
  }

  interface IRuntimeInfoListener {
    void queryFinished();

    void updated();
  }

  /**
   * Returns the average net query duration
   */
  public int getAverageDuration() {
    return averageDuration;
  }

  /**
   * Returns the count of the objects in scope
   */
  public int getObjectScopeCount() {
    return objectScopeCount;
  }

  public long getOverallClientTimeInMs() {
    return overallClientTimeInMs;
  }

  /**
   * Returns the overall net server time of the query, i.e. the pure search duration
   */
  public int getOverallServerTimeInMs() {
    return overallServerTimeInMs;
  }

  public int getResultCount() {
    return resultCount;
  }

  /**
   * Returns the number of lines of code that were searched
   */
  public float getSearchedLinesOfCode() {
    return searchedLinesOfCode;
  }

  /**
   * Returns the count of searched objects
   */
  public int getSearchedObjectsCount() {
    return searchedObjectsCount;
  }

  /**
   * Returns the count of searched sources
   */
  public int getSearchedSourcesCount() {
    return searchedSourcesCount;
  }

  public String getSystemId() {
    if (systemId == null) {
      systemId = searchQuery.getProjectProvider()
          .getDestinationData()
          .getSystemConfiguration()
          .getSystemId();
    }
    return systemId;
  }

  public void increaseOverallClientTimeInMs(final long overallClientTimeInMs) {
    this.overallClientTimeInMs += overallClientTimeInMs;
  }

  public boolean isQueryFinished() {
    return searchQuery != null && searchQuery.isFinished();
  }

  public boolean isSearchRunning() {
    return searchQuery == null ? false : NewSearchUI.isQueryRunning(searchQuery);
  }

  @Override
  public void queryAdded(final ISearchQuery query) {
  }

  @Override
  public void queryFinished(final ISearchQuery query) {
    if (query == searchQuery) {
      fireQueryFinished();
    }
  }

  @Override
  public void queryRemoved(final ISearchQuery query) {
  }

  @Override
  public void queryStarting(final ISearchQuery query) {
  }

  public void reset() {
    averageDuration = 0;
    overallServerTimeInMs = 0;
    overallClientTimeInMs = 0;
    resultCount = 0;
    requestCount = 0;
    searchedLinesOfCode = 0;
    objectScopeCount = 0;
    searchedSourcesCount = 0;
    searchedObjectsCount = 0;
  }

  public void setObjectCount(final int objectCount) {
    objectScopeCount = objectCount;
  }

  public void updateWithNewResult(final ICodeSearchResult result) {
    overallServerTimeInMs += result.getQueryTimeInMs();
    resultCount += result.getNumberOfResults();
    searchedObjectsCount += result.getNumberOfSearchedObjects();
    searchedSourcesCount += result.getNumberOfSearchedSources();
    searchedLinesOfCode += result.getLinesOfSearchedCode();
    averageDuration = overallServerTimeInMs / ++requestCount;

    fireUpdated();
  }

  void addRuntimeInfoListener(final IRuntimeInfoListener l) {
    runtimeListeners.add(l);
  }

  void removeRuntimeInfoListener(final IRuntimeInfoListener l) {
    runtimeListeners.remove(l);
  }

  void startQueryListening() {
    NewSearchUI.addQueryListener(this);
  }

  void stopQueryListening() {
    NewSearchUI.removeQueryListener(this);
  }

  private void fireQueryFinished() {
    for (IRuntimeInfoListener l : runtimeListeners) {
      l.queryFinished();
    }
  }

  private void fireUpdated() {
    for (IRuntimeInfoListener l : runtimeListeners) {
      l.updated();
    }
  }
}
