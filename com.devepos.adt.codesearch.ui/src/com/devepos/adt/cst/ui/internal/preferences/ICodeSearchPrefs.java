package com.devepos.adt.cst.ui.internal.preferences;

/**
 * Preference Id's for ABAP Code Search
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public interface ICodeSearchPrefs {

  /**
   * Removes the limit on the maximum number of objects that are searched
   */
  String ALL_OBJECTS_ENABLED = "codeSearch.searchAllObjectsEnabled";
  /**
   * Maximum objects that will be selected during the Code Search
   */
  String MAX_OBJECTS = "codeSearch.maxObjects";
  /**
   * Concatenates lines of the search pattern with \n if single pattern mode and regular expressions
   * are active
   */
  String SINGLE_PATTERN_REGEX_CONCAT_WITH_LF = "codeSearch.singlePattern.regex.concatPatternLinesWithLf";
  /**
   * Setting to remember the include settings for class/function group/program for next dialog call
   */
  String REMEMBER_INCLUDE_SETTINGS = "codeSearch.rememberIncludeSettings";
  /**
   * Marker sequence value to mark Code search matches in a file export
   */
  String EXPORT_DIALOG_MATCH_MARKER_SEQUENCE = "codeSearch.exportDialog.matchMarkerSequence";
}
