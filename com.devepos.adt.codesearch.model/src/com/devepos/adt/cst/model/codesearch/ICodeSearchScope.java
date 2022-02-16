/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScope#getId <em>Id</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScope#getObjectCount <em>Object
 * Count</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchScope()
 * @model extendedMetaData="kind='elementOnly' name='searchScope'"
 * @generated
 */
public interface ICodeSearchScope extends EObject {
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Id</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchScope_Id()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
   * @generated
   */
  String getId();

  /**
   * Returns the value of the '<em><b>Object Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Object Count</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchScope_ObjectCount()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Int" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='objectCount' namespace='##targetNamespace'"
   * @generated
   */
  int getObjectCount();

} // ICodeSearchScope
