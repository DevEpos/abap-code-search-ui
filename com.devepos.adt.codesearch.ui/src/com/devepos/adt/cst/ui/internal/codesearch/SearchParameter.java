package com.devepos.adt.cst.ui.internal.codesearch;

public enum SearchParameter {
  CLASS_SCOPE("classScope"),
  MAX_OBJECTS("maxObjects"),
  MAX_RESULTS("maxResults"),
  ALL_RESULTS("allResults"),
  IGNORE_CASE("ignoreCase"),
  IGNORE_COMMENT_LINES("ignoreCommentLines"),
  USE_REGEX("useRegex"),
  MATCH_ALL("matchAll"),
  MULTI_LINE("multiLine"),
  SINGLE_PATTERN("singlePattern"),
  SCOPE_ID("scopeId"),
  SCOPE_OFFSET("scopeOffset");

  private String uriParamName;

  SearchParameter(final String uriParamName) {
    this.uriParamName = uriParamName;
  }

  /**
   * Retrieves the URI name of the paremeter
   */
  public String getUriName() {
    return uriParamName;
  }
}