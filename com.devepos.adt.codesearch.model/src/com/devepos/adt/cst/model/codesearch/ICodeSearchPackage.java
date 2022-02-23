/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchFactory
 * @model kind="package"
 * @generated
 */
public interface ICodeSearchPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  String eNAME = "codesearch";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  String eNS_URI = "http://www.devepos.com/adt/cst";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  String eNS_PREFIX = "cst";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  ICodeSearchPackage eINSTANCE = com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage.init();

  /**
   * The meta object id for the '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject
   * <em>Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchObject()
   * @generated
   */
  int CODE_SEARCH_OBJECT = 0;

  /**
   * The feature id for the '<em><b>Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT__URI = 0;

  /**
   * The feature id for the '<em><b>Parent Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT__PARENT_URI = 1;

  /**
   * The feature id for the '<em><b>Adt Main Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT = 2;

  /**
   * The feature id for the '<em><b>Matches</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT__MATCHES = 3;

  /**
   * The number of structural features of the '<em>Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch
   * <em>Match</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchMatch()
   * @generated
   */
  int CODE_SEARCH_MATCH = 1;

  /**
   * The feature id for the '<em><b>Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_MATCH__URI = 0;

  /**
   * The feature id for the '<em><b>Snippet</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_MATCH__SNIPPET = 1;

  /**
   * The number of structural features of the '<em>Match</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_MATCH_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Match</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_MATCH_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult
   * <em>Result</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchResult()
   * @generated
   */
  int CODE_SEARCH_RESULT = 2;

  /**
   * The feature id for the '<em><b>Search Objects</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__SEARCH_OBJECTS = 0;

  /**
   * The feature id for the '<em><b>Response Message List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST = 1;

  /**
   * The feature id for the '<em><b>Number Of Results</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__NUMBER_OF_RESULTS = 2;

  /**
   * The feature id for the '<em><b>Number Of Searched Objects</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_OBJECTS = 3;

  /**
   * The feature id for the '<em><b>Number Of Searched Sources</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_SOURCES = 4;

  /**
   * The feature id for the '<em><b>Query Time In Ms</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__QUERY_TIME_IN_MS = 5;

  /**
   * The number of structural features of the '<em>Result</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT_FEATURE_COUNT = 6;

  /**
   * The number of operations of the '<em>Result</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings
   * <em>Settings</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchSettings()
   * @generated
   */
  int CODE_SEARCH_SETTINGS = 3;

  /**
   * The feature id for the '<em><b>Parallel Enabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS__PARALLEL_ENABLED = 0;

  /**
   * The feature id for the '<em><b>Parallel Server Group</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS__PARALLEL_SERVER_GROUP = 1;

  /**
   * The feature id for the '<em><b>Parallel Package Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS__PARALLEL_PACKAGE_SIZE = 2;

  /**
   * The feature id for the '<em><b>Pcre Extended Disabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS__PCRE_EXTENDED_DISABLED = 3;

  /**
   * The feature id for the '<em><b>Pcre Single Line Enabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS__PCRE_SINGLE_LINE_ENABLED = 4;

  /**
   * The number of structural features of the '<em>Settings</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS_FEATURE_COUNT = 5;

  /**
   * The number of operations of the '<em>Settings</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS_OPERATION_COUNT = 0;

  /**
   * The meta object id for the
   * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchScopeParameter <em>Scope
   * Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchScopeParameter
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchScopeParameter()
   * @generated
   */
  int CODE_SEARCH_SCOPE_PARAMETER = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE_PARAMETER__NAME = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE_PARAMETER__VALUE = 1;

  /**
   * The number of structural features of the '<em>Scope Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE_PARAMETER_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Scope Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE_PARAMETER_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchScope
   * <em>Scope</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchScope
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchScope()
   * @generated
   */
  int CODE_SEARCH_SCOPE = 5;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE__ID = 0;

  /**
   * The feature id for the '<em><b>Object Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE__OBJECT_COUNT = 1;

  /**
   * The number of structural features of the '<em>Scope</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Scope</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE_OPERATION_COUNT = 0;

  /**
   * The meta object id for the
   * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchScopeParameters <em>Scope
   * Parameters</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchScopeParameters
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchScopeParameters()
   * @generated
   */
  int CODE_SEARCH_SCOPE_PARAMETERS = 6;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE_PARAMETERS__PARAMETERS = 0;

  /**
   * The number of structural features of the '<em>Scope Parameters</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE_PARAMETERS_FEATURE_COUNT = 1;

  /**
   * The number of operations of the '<em>Scope Parameters</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SCOPE_PARAMETERS_OPERATION_COUNT = 0;

  /**
   * Returns the meta object for class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject <em>Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Object</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject
   * @generated
   */
  EClass getCodeSearchObject();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getUri <em>Uri</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Uri</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getUri()
   * @see #getCodeSearchObject()
   * @generated
   */
  EAttribute getCodeSearchObject_Uri();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getParentUri <em>Parent
   * Uri</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Parent Uri</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getParentUri()
   * @see #getCodeSearchObject()
   * @generated
   */
  EAttribute getCodeSearchObject_ParentUri();

  /**
   * Returns the meta object for the containment reference
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getAdtMainObject <em>Adt Main
   * Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference '<em>Adt Main Object</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getAdtMainObject()
   * @see #getCodeSearchObject()
   * @generated
   */
  EReference getCodeSearchObject_AdtMainObject();

  /**
   * Returns the meta object for the containment reference list
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getMatches <em>Matches</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Matches</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getMatches()
   * @see #getCodeSearchObject()
   * @generated
   */
  EReference getCodeSearchObject_Matches();

  /**
   * Returns the meta object for class '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch
   * <em>Match</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Match</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchMatch
   * @generated
   */
  EClass getCodeSearchMatch();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getUri <em>Uri</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Uri</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getUri()
   * @see #getCodeSearchMatch()
   * @generated
   */
  EAttribute getCodeSearchMatch_Uri();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getSnippet <em>Snippet</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Snippet</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getSnippet()
   * @see #getCodeSearchMatch()
   * @generated
   */
  EAttribute getCodeSearchMatch_Snippet();

  /**
   * Returns the meta object for class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult <em>Result</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Result</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult
   * @generated
   */
  EClass getCodeSearchResult();

  /**
   * Returns the meta object for the containment reference list
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getSearchObjects <em>Search
   * Objects</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Search Objects</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getSearchObjects()
   * @see #getCodeSearchResult()
   * @generated
   */
  EReference getCodeSearchResult_SearchObjects();

  /**
   * Returns the meta object for the containment reference
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getResponseMessageList
   * <em>Response Message List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference '<em>Response Message List</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getResponseMessageList()
   * @see #getCodeSearchResult()
   * @generated
   */
  EReference getCodeSearchResult_ResponseMessageList();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfResults <em>Number Of
   * Results</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Number Of Results</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfResults()
   * @see #getCodeSearchResult()
   * @generated
   */
  EAttribute getCodeSearchResult_NumberOfResults();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfSearchedObjects
   * <em>Number Of Searched Objects</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Number Of Searched Objects</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfSearchedObjects()
   * @see #getCodeSearchResult()
   * @generated
   */
  EAttribute getCodeSearchResult_NumberOfSearchedObjects();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfSearchedSources
   * <em>Number Of Searched Sources</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Number Of Searched Sources</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfSearchedSources()
   * @see #getCodeSearchResult()
   * @generated
   */
  EAttribute getCodeSearchResult_NumberOfSearchedSources();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getQueryTimeInMs <em>Query Time
   * In Ms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Query Time In Ms</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getQueryTimeInMs()
   * @see #getCodeSearchResult()
   * @generated
   */
  EAttribute getCodeSearchResult_QueryTimeInMs();

  /**
   * Returns the meta object for class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings <em>Settings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Settings</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings
   * @generated
   */
  EClass getCodeSearchSettings();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isParallelEnabled <em>Parallel
   * Enabled</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Parallel Enabled</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isParallelEnabled()
   * @see #getCodeSearchSettings()
   * @generated
   */
  EAttribute getCodeSearchSettings_ParallelEnabled();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#getParallelServerGroup
   * <em>Parallel Server Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Parallel Server Group</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#getParallelServerGroup()
   * @see #getCodeSearchSettings()
   * @generated
   */
  EAttribute getCodeSearchSettings_ParallelServerGroup();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#getParallelPackageSize
   * <em>Parallel Package Size</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Parallel Package Size</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#getParallelPackageSize()
   * @see #getCodeSearchSettings()
   * @generated
   */
  EAttribute getCodeSearchSettings_ParallelPackageSize();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreExtendedDisabled
   * <em>Pcre Extended Disabled</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Pcre Extended Disabled</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreExtendedDisabled()
   * @see #getCodeSearchSettings()
   * @generated
   */
  EAttribute getCodeSearchSettings_PcreExtendedDisabled();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreSingleLineEnabled
   * <em>Pcre Single Line Enabled</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Pcre Single Line Enabled</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreSingleLineEnabled()
   * @see #getCodeSearchSettings()
   * @generated
   */
  EAttribute getCodeSearchSettings_PcreSingleLineEnabled();

  /**
   * Returns the meta object for class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter <em>Scope
   * Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Scope Parameter</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter
   * @generated
   */
  EClass getCodeSearchScopeParameter();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter#getName()
   * @see #getCodeSearchScopeParameter()
   * @generated
   */
  EAttribute getCodeSearchScopeParameter_Name();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter#getValue
   * <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter#getValue()
   * @see #getCodeSearchScopeParameter()
   * @generated
   */
  EAttribute getCodeSearchScopeParameter_Value();

  /**
   * Returns the meta object for class '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScope
   * <em>Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Scope</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScope
   * @generated
   */
  EClass getCodeSearchScope();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScope#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScope#getId()
   * @see #getCodeSearchScope()
   * @generated
   */
  EAttribute getCodeSearchScope_Id();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScope#getObjectCount <em>Object
   * Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Object Count</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScope#getObjectCount()
   * @see #getCodeSearchScope()
   * @generated
   */
  EAttribute getCodeSearchScope_ObjectCount();

  /**
   * Returns the meta object for class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters <em>Scope
   * Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Scope Parameters</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters
   * @generated
   */
  EClass getCodeSearchScopeParameters();

  /**
   * Returns the meta object for the containment reference list
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters#getParameters
   * <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters#getParameters()
   * @see #getCodeSearchScopeParameters()
   * @generated
   */
  EReference getCodeSearchScopeParameters_Parameters();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ICodeSearchFactory getCodeSearchFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   * <li>each class,</li>
   * <li>each feature of each class,</li>
   * <li>each operation of each class,</li>
   * <li>each enum,</li>
   * <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   *
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject <em>Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchObject()
     * @generated
     */
    EClass CODE_SEARCH_OBJECT = eINSTANCE.getCodeSearchObject();

    /**
     * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_OBJECT__URI = eINSTANCE.getCodeSearchObject_Uri();

    /**
     * The meta object literal for the '<em><b>Parent Uri</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_OBJECT__PARENT_URI = eINSTANCE.getCodeSearchObject_ParentUri();

    /**
     * The meta object literal for the '<em><b>Adt Main Object</b></em>' containment reference
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT = eINSTANCE.getCodeSearchObject_AdtMainObject();

    /**
     * The meta object literal for the '<em><b>Matches</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference CODE_SEARCH_OBJECT__MATCHES = eINSTANCE.getCodeSearchObject_Matches();

    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch <em>Match</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchMatch()
     * @generated
     */
    EClass CODE_SEARCH_MATCH = eINSTANCE.getCodeSearchMatch();

    /**
     * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_MATCH__URI = eINSTANCE.getCodeSearchMatch_Uri();

    /**
     * The meta object literal for the '<em><b>Snippet</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_MATCH__SNIPPET = eINSTANCE.getCodeSearchMatch_Snippet();

    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult <em>Result</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchResult()
     * @generated
     */
    EClass CODE_SEARCH_RESULT = eINSTANCE.getCodeSearchResult();

    /**
     * The meta object literal for the '<em><b>Search Objects</b></em>' containment reference list
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference CODE_SEARCH_RESULT__SEARCH_OBJECTS = eINSTANCE.getCodeSearchResult_SearchObjects();

    /**
     * The meta object literal for the '<em><b>Response Message List</b></em>' containment reference
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST = eINSTANCE
        .getCodeSearchResult_ResponseMessageList();

    /**
     * The meta object literal for the '<em><b>Number Of Results</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_RESULT__NUMBER_OF_RESULTS = eINSTANCE
        .getCodeSearchResult_NumberOfResults();

    /**
     * The meta object literal for the '<em><b>Number Of Searched Objects</b></em>' attribute
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_OBJECTS = eINSTANCE
        .getCodeSearchResult_NumberOfSearchedObjects();

    /**
     * The meta object literal for the '<em><b>Number Of Searched Sources</b></em>' attribute
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_SOURCES = eINSTANCE
        .getCodeSearchResult_NumberOfSearchedSources();

    /**
     * The meta object literal for the '<em><b>Query Time In Ms</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_RESULT__QUERY_TIME_IN_MS = eINSTANCE.getCodeSearchResult_QueryTimeInMs();

    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings <em>Settings</em>}'
     * class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchSettings()
     * @generated
     */
    EClass CODE_SEARCH_SETTINGS = eINSTANCE.getCodeSearchSettings();

    /**
     * The meta object literal for the '<em><b>Parallel Enabled</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SETTINGS__PARALLEL_ENABLED = eINSTANCE
        .getCodeSearchSettings_ParallelEnabled();

    /**
     * The meta object literal for the '<em><b>Parallel Server Group</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SETTINGS__PARALLEL_SERVER_GROUP = eINSTANCE
        .getCodeSearchSettings_ParallelServerGroup();

    /**
     * The meta object literal for the '<em><b>Parallel Package Size</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SETTINGS__PARALLEL_PACKAGE_SIZE = eINSTANCE
        .getCodeSearchSettings_ParallelPackageSize();

    /**
     * The meta object literal for the '<em><b>Pcre Extended Disabled</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SETTINGS__PCRE_EXTENDED_DISABLED = eINSTANCE
        .getCodeSearchSettings_PcreExtendedDisabled();

    /**
     * The meta object literal for the '<em><b>Pcre Single Line Enabled</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SETTINGS__PCRE_SINGLE_LINE_ENABLED = eINSTANCE
        .getCodeSearchSettings_PcreSingleLineEnabled();

    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchScopeParameter <em>Scope
     * Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchScopeParameter
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchScopeParameter()
     * @generated
     */
    EClass CODE_SEARCH_SCOPE_PARAMETER = eINSTANCE.getCodeSearchScopeParameter();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SCOPE_PARAMETER__NAME = eINSTANCE.getCodeSearchScopeParameter_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SCOPE_PARAMETER__VALUE = eINSTANCE.getCodeSearchScopeParameter_Value();

    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchScope <em>Scope</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchScope
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchScope()
     * @generated
     */
    EClass CODE_SEARCH_SCOPE = eINSTANCE.getCodeSearchScope();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SCOPE__ID = eINSTANCE.getCodeSearchScope_Id();

    /**
     * The meta object literal for the '<em><b>Object Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SCOPE__OBJECT_COUNT = eINSTANCE.getCodeSearchScope_ObjectCount();

    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchScopeParameters <em>Scope
     * Parameters</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchScopeParameters
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchScopeParameters()
     * @generated
     */
    EClass CODE_SEARCH_SCOPE_PARAMETERS = eINSTANCE.getCodeSearchScopeParameters();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list
     * feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    EReference CODE_SEARCH_SCOPE_PARAMETERS__PARAMETERS = eINSTANCE
        .getCodeSearchScopeParameters_Parameters();

  }

} // ICodeSearchPackage
