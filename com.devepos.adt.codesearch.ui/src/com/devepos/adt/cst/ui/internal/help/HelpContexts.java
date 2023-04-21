package com.devepos.adt.cst.ui.internal.help;

/**
 * Holds Help Contexts
 *
 * @author stockbal
 */
public enum HelpContexts {
  CODE_SEARCH("code_search"),
  CODE_SEARCH_DIALOG("code_search_dialog"),
  CODE_SEARCH_RESULT("code_search_result"),
  CODE_SEARCH_PREFERENCES("code_search_preferences"),
  CODE_SEARCH_PROPERTIES("code_search_properties"),
  CODE_SEARCH_EXPORT_DIALOG("result_csv_export");

  private String helpContextId;

  HelpContexts(final String contextId) {
    helpContextId = contextId;
  }

  public String getHelpContextId() {
    return helpContextId;
  }
}
