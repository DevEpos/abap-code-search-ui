/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope Parameters</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters#getParameters
 * <em>Parameters</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchScopeParameters()
 * @model extendedMetaData="kind='elementOnly' name='searchScopeParameters'"
 * @generated
 */
public interface ICodeSearchScopeParameters extends EObject {
  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type
   * {@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchScopeParameters_Parameters()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='scopeParameter' namespace='##targetNamespace'"
   * @generated
   */
  EList<ICodeSearchScopeParameter> getParameters();

} // ICodeSearchScopeParameters
