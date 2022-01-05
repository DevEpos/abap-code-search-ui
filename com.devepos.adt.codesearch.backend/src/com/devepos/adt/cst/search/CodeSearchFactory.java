package com.devepos.adt.cst.search;

import com.devepos.adt.cst.internal.search.CodeSearchSearchService;

/**
 * Factory for creating instances of {@link ICodeSearchService}
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchFactory {

  private static ICodeSearchService INSTANCE;

  /**
   * Creates new search service instance for searching in ABAP Code
   *
   * @return new search service instance for searching in ABAP Code
   */
  public static ICodeSearchService getCodeSearchService() {
    if (INSTANCE == null) {
      INSTANCE = new CodeSearchSearchService();
    }
    return INSTANCE;
  }
}
