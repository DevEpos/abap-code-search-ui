/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
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
   * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  String eNAME = "codesearch";

  /**
   * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  String eNS_URI = "http://www.devepos.com/adt/codesearchtools";

  /**
   * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  String eNS_PREFIX = "cst";

  /**
   * The singleton instance of the package. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @generated
   */
  ICodeSearchPackage eINSTANCE = com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage.init();

  /**
   * The meta object id for the
   * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject
   * <em>Object</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchObject()
   * @generated
   */
  int CODE_SEARCH_OBJECT = 0;

  /**
   * The feature id for the '<em><b>Uri</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT__URI = 0;

  /**
   * The feature id for the '<em><b>Parent Uri</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT__PARENT_URI = 1;

  /**
   * The feature id for the '<em><b>Adt Main Object</b></em>' containment
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT = 2;

  /**
   * The feature id for the '<em><b>Matches</b></em>' containment reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT__MATCHES = 3;

  /**
   * The number of structural features of the '<em>Object</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>Object</em>' class. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_OBJECT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the
   * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch
   * <em>Match</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchMatch()
   * @generated
   */
  int CODE_SEARCH_MATCH = 1;

  /**
   * The feature id for the '<em><b>Uri</b></em>' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_MATCH__URI = 0;

  /**
   * The feature id for the '<em><b>Snippet</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_MATCH__SNIPPET = 1;

  /**
   * The number of structural features of the '<em>Match</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_MATCH_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Match</em>' class. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_MATCH_OPERATION_COUNT = 0;

  /**
   * The meta object id for the
   * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult
   * <em>Result</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchResult()
   * @generated
   */
  int CODE_SEARCH_RESULT = 2;

  /**
   * The feature id for the '<em><b>Search Objects</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__SEARCH_OBJECTS = 0;

  /**
   * The feature id for the '<em><b>Number Of Results</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__NUMBER_OF_RESULTS = 1;

  /**
   * The feature id for the '<em><b>Number Of Searched Objects</b></em>'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_OBJECTS = 2;

  /**
   * The feature id for the '<em><b>Query Time In Ms</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT__QUERY_TIME_IN_MS = 3;

  /**
   * The number of structural features of the '<em>Result</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT_FEATURE_COUNT = 4;

  /**
   * The number of operations of the '<em>Result</em>' class. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_RESULT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the
   * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings
   * <em>Settings</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings
   * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchSettings()
   * @generated
   */
  int CODE_SEARCH_SETTINGS = 3;

  /**
   * The feature id for the '<em><b>Pcre Enabled</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS__PCRE_ENABLED = 0;

  /**
   * The feature id for the '<em><b>Parallel Enabled</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS__PARALLEL_ENABLED = 1;

  /**
   * The feature id for the '<em><b>Parallel Server Group</b></em>' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS__PARALLEL_SERVER_GROUP = 2;

  /**
   * The number of structural features of the '<em>Settings</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS_FEATURE_COUNT = 3;

  /**
   * The number of operations of the '<em>Settings</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @ordered
   */
  int CODE_SEARCH_SETTINGS_OPERATION_COUNT = 0;

  /**
   * Returns the meta object for class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject
   * <em>Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Object</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject
   * @generated
   */
  EClass getCodeSearchObject();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getUri
   * <em>Uri</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Uri</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getUri()
   * @see #getCodeSearchObject()
   * @generated
   */
  EAttribute getCodeSearchObject_Uri();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getParentUri
   * <em>Parent Uri</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Parent Uri</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getParentUri()
   * @see #getCodeSearchObject()
   * @generated
   */
  EAttribute getCodeSearchObject_ParentUri();

  /**
   * Returns the meta object for the containment reference
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getAdtMainObject
   * <em>Adt Main Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference '<em>Adt Main
   *         Object</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getAdtMainObject()
   * @see #getCodeSearchObject()
   * @generated
   */
  EReference getCodeSearchObject_AdtMainObject();

  /**
   * Returns the meta object for the containment reference list
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getMatches
   * <em>Matches</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list
   *         '<em>Matches</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getMatches()
   * @see #getCodeSearchObject()
   * @generated
   */
  EReference getCodeSearchObject_Matches();

  /**
   * Returns the meta object for class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch
   * <em>Match</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Match</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchMatch
   * @generated
   */
  EClass getCodeSearchMatch();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getUri
   * <em>Uri</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Uri</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getUri()
   * @see #getCodeSearchMatch()
   * @generated
   */
  EAttribute getCodeSearchMatch_Uri();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getSnippet
   * <em>Snippet</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Snippet</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getSnippet()
   * @see #getCodeSearchMatch()
   * @generated
   */
  EAttribute getCodeSearchMatch_Snippet();

  /**
   * Returns the meta object for class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult
   * <em>Result</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Result</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult
   * @generated
   */
  EClass getCodeSearchResult();

  /**
   * Returns the meta object for the containment reference list
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getSearchObjects
   * <em>Search Objects</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the containment reference list '<em>Search
   *         Objects</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getSearchObjects()
   * @see #getCodeSearchResult()
   * @generated
   */
  EReference getCodeSearchResult_SearchObjects();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfResults
   * <em>Number Of Results</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
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
   * <em>Number Of Searched Objects</em>}'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Number Of Searched
   *         Objects</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfSearchedObjects()
   * @see #getCodeSearchResult()
   * @generated
   */
  EAttribute getCodeSearchResult_NumberOfSearchedObjects();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getQueryTimeInMs
   * <em>Query Time In Ms</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Query Time In Ms</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getQueryTimeInMs()
   * @see #getCodeSearchResult()
   * @generated
   */
  EAttribute getCodeSearchResult_QueryTimeInMs();

  /**
   * Returns the meta object for class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings
   * <em>Settings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for class '<em>Settings</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings
   * @generated
   */
  EClass getCodeSearchSettings();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreEnabled
   * <em>Pcre Enabled</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the meta object for the attribute '<em>Pcre Enabled</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreEnabled()
   * @see #getCodeSearchSettings()
   * @generated
   */
  EAttribute getCodeSearchSettings_PcreEnabled();

  /**
   * Returns the meta object for the attribute
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isParallelEnabled
   * <em>Parallel Enabled</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
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
   * <em>Parallel Server Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @return the meta object for the attribute '<em>Parallel Server Group</em>'.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#getParallelServerGroup()
   * @see #getCodeSearchSettings()
   * @generated
   */
  EAttribute getCodeSearchSettings_ParallelServerGroup();

  /**
   * Returns the factory that creates the instances of the model. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ICodeSearchFactory getCodeSearchFactory();

  /**
   * <!-- begin-user-doc --> Defines literals for the meta objects that represent
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
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject
     * <em>Object</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchObject()
     * @generated
     */
    EClass CODE_SEARCH_OBJECT = eINSTANCE.getCodeSearchObject();

    /**
     * The meta object literal for the '<em><b>Uri</b></em>' attribute feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_OBJECT__URI = eINSTANCE.getCodeSearchObject_Uri();

    /**
     * The meta object literal for the '<em><b>Parent Uri</b></em>' attribute
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_OBJECT__PARENT_URI = eINSTANCE.getCodeSearchObject_ParentUri();

    /**
     * The meta object literal for the '<em><b>Adt Main Object</b></em>' containment
     * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EReference CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT = eINSTANCE.getCodeSearchObject_AdtMainObject();

    /**
     * The meta object literal for the '<em><b>Matches</b></em>' containment
     * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EReference CODE_SEARCH_OBJECT__MATCHES = eINSTANCE.getCodeSearchObject_Matches();

    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch
     * <em>Match</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchMatch()
     * @generated
     */
    EClass CODE_SEARCH_MATCH = eINSTANCE.getCodeSearchMatch();

    /**
     * The meta object literal for the '<em><b>Uri</b></em>' attribute feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_MATCH__URI = eINSTANCE.getCodeSearchMatch_Uri();

    /**
     * The meta object literal for the '<em><b>Snippet</b></em>' attribute feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_MATCH__SNIPPET = eINSTANCE.getCodeSearchMatch_Snippet();

    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult
     * <em>Result</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchResult()
     * @generated
     */
    EClass CODE_SEARCH_RESULT = eINSTANCE.getCodeSearchResult();

    /**
     * The meta object literal for the '<em><b>Search Objects</b></em>' containment
     * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EReference CODE_SEARCH_RESULT__SEARCH_OBJECTS = eINSTANCE.getCodeSearchResult_SearchObjects();

    /**
     * The meta object literal for the '<em><b>Number Of Results</b></em>' attribute
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_RESULT__NUMBER_OF_RESULTS = eINSTANCE
        .getCodeSearchResult_NumberOfResults();

    /**
     * The meta object literal for the '<em><b>Number Of Searched Objects</b></em>'
     * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_OBJECTS = eINSTANCE
        .getCodeSearchResult_NumberOfSearchedObjects();

    /**
     * The meta object literal for the '<em><b>Query Time In Ms</b></em>' attribute
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_RESULT__QUERY_TIME_IN_MS = eINSTANCE.getCodeSearchResult_QueryTimeInMs();

    /**
     * The meta object literal for the
     * '{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings
     * <em>Settings</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings
     * @see com.devepos.adt.cst.model.codesearch.impl.CodeSearchPackage#getCodeSearchSettings()
     * @generated
     */
    EClass CODE_SEARCH_SETTINGS = eINSTANCE.getCodeSearchSettings();

    /**
     * The meta object literal for the '<em><b>Pcre Enabled</b></em>' attribute
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SETTINGS__PCRE_ENABLED = eINSTANCE.getCodeSearchSettings_PcreEnabled();

    /**
     * The meta object literal for the '<em><b>Parallel Enabled</b></em>' attribute
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SETTINGS__PARALLEL_ENABLED = eINSTANCE
        .getCodeSearchSettings_ParallelEnabled();

    /**
     * The meta object literal for the '<em><b>Parallel Server Group</b></em>'
     * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    EAttribute CODE_SEARCH_SETTINGS__PARALLEL_SERVER_GROUP = eINSTANCE
        .getCodeSearchSettings_ParallelServerGroup();

  }

} // ICodeSearchPackage
