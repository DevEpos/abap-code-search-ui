package com.devepos.adt.cst.search;

import com.devepos.adt.cst.internal.search.CodeSearchService;

/**
 * Factory for creating instances of {@link ICodeSearchService}
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchFactory {

  private static ICodeSearchService INSTANCE;

  /**
   * Retrieves instance of {@link ICodeSearchService}
   *
   * @return search service instance for searching in ABAP Code
   */
  public static ICodeSearchService getCodeSearchService() {
    if (INSTANCE == null) {
      INSTANCE = new CodeSearchService();
    }
    return INSTANCE;
  }
}
