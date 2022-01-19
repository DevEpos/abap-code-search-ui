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
   * Indicates if the global include in a class should be searched
   */
  String CLASS_SCOPE_GLOBAL_ENABLED = "codeSearch.classScope.includes.globalEnabled";
  /**
   * Indicates if the local definitions include in a class should be searched
   */
  String CLASS_SCOPE_LOCAL_DEF_ENABLED = "codeSearch.classScope.includes.localDefEnabled";
  /**
   * Indicates if the local types include in a class should be searched
   */
  String CLASS_SCOPE_LOCAL_IMPL_ENABLED = "codeSearch.classScope.includes.localImplEnabled";
  /**
   * Indicates if the macros include in a class should be searched
   */
  String CLASS_SCOPE_MACROS_ENABLED = "codeSearch.classScope.includes.macrosEnabled";
  /**
   * Option to control if all class includes or only specific ones should be
   * searched
   */
  String CLASS_SCOPE_OPTION = "codeSearch.classScope.option";
  /**
   * Indicates if the tests include in a class should be searched
   */
  String CLASS_SCOPE_TESTS_ENABLED = "codeSearch.classScope.includes.testsEnabled";
  /**
   * Maximum objects that will be selected during the Code Search
   */
  String MAX_OBJECTS = "codeSearch.maxObjects";
  /**
   * Maximum results that will be collected during the Code Search
   */
  String MAX_RESULTS = "codeSearch.maxResults";
}
