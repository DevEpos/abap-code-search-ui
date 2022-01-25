/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter#getName
 * <em>Name</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter#getValue
 * <em>Value</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchScopeParameter()
 * @model extendedMetaData="kind='elementOnly' name='scopeParameter'"
 * @generated
 */
public interface ICodeSearchScopeParameter extends EObject {
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchScopeParameter_Name()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter#getName <em>Name</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchScopeParameter_Value()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   *        extendedMetaData="kind='attribute' name='value' namespace='##targetNamespace'"
   * @generated
   */
  String getValue();

  /**
   * Sets the value of the
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter#getValue
   * <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(String value);

} // ICodeSearchScopeParameter
