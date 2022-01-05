/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Result</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult#getSearchObjects
 * <em>Search Objects</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult()
 * @model extendedMetaData="kind='elementOnly' name='result'"
 * @generated
 */
public interface ICodeSearchResult extends EObject {
  /**
   * Returns the value of the '<em><b>Search Objects</b></em>' containment
   * reference list. The list contents are of type
   * {@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject}. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the value of the '<em>Search Objects</em>' containment reference
   *         list.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchResult_SearchObjects()
   * @model containment="true" extendedMetaData="kind='element'
   *        name='searchObject' namespace='##targetNamespace'"
   * @generated
   */
  EList<ICodeSearchObject> getSearchObjects();

} // ICodeSearchResult
