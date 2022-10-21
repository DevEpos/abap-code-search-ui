/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.devepos.adt.base.model.adtbase.IResponseMessageList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getSearchObjects <em>Search
 * Objects</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getResponseMessageList
 * <em>Response Message List</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfResults <em>Number
 * Of Results</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfSearchedObjects
 * <em>Number Of Searched Objects</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getNumberOfSearchedSources
 * <em>Number Of Searched Sources</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getLinesOfSearchedCode
 * <em>Lines Of Searched Code</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getQueryTimeInMs <em>Query Time
 * In Ms</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult()
 * @model extendedMetaData="kind='elementOnly' name='result'"
 * @generated
 */
public interface ICodeSearchResult extends EObject {
  /**
   * Returns the value of the '<em><b>Search Objects</b></em>' containment reference list.
   * The list contents are of type {@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Search Objects</em>' containment reference list.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult_SearchObjects()
   * @model containment="true" suppressedSetVisibility="true"
   *        extendedMetaData="kind='element' name='searchObject' namespace='##targetNamespace'"
   * @generated
   */
  EList<ICodeSearchObject> getSearchObjects();

  /**
   * Returns the value of the '<em><b>Response Message List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Response Message List</em>' containment reference.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult_ResponseMessageList()
   * @model containment="true" suppressedSetVisibility="true"
   *        extendedMetaData="kind='element' name='responseMessages'
   *        namespace='http://www.devepos.com/adt/base'"
   * @generated
   */
  IResponseMessageList getResponseMessageList();

  /**
   * Returns the value of the '<em><b>Number Of Results</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Number Of Results</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult_NumberOfResults()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Int" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='numberOfResults' namespace='##targetNamespace'"
   * @generated
   */
  int getNumberOfResults();

  /**
   * Returns the value of the '<em><b>Number Of Searched Objects</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Number Of Searched Objects</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult_NumberOfSearchedObjects()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Int" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='numberOfSearchedObjects'
   *        namespace='##targetNamespace'"
   * @generated
   */
  int getNumberOfSearchedObjects();

  /**
   * Returns the value of the '<em><b>Number Of Searched Sources</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Number Of Searched Sources</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult_NumberOfSearchedSources()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Int" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='numberOfSearchedSources'
   *        namespace='##targetNamespace'"
   * @generated
   */
  int getNumberOfSearchedSources();

  /**
   * Returns the value of the '<em><b>Lines Of Searched Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Lines Of Searched Code</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult_LinesOfSearchedCode()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Float" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='linesOfSearchedCode'
   *        namespace='##targetNamespace'"
   * @generated
   */
  float getLinesOfSearchedCode();

  /**
   * Returns the value of the '<em><b>Query Time In Ms</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Query Time In Ms</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult_QueryTimeInMs()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Int" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='queryTimeInMs' namespace='##targetNamespace'"
   * @generated
   */
  int getQueryTimeInMs();

} // ICodeSearchResult
