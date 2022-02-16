/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Settings</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isParallelEnabled
 * <em>Parallel Enabled</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#getParallelServerGroup
 * <em>Parallel Server Group</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreExtendedDisabled
 * <em>Pcre Extended Disabled</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreSingleLineEnabled
 * <em>Pcre Single Line Enabled</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchSettings()
 * @model extendedMetaData="kind='elementOnly' name='settings'"
 * @generated
 */
public interface ICodeSearchSettings extends EObject {
  /**
   * Returns the value of the '<em><b>Parallel Enabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Parallel Enabled</em>' attribute.
   * @see #setParallelEnabled(boolean)
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchSettings_ParallelEnabled()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
   *        extendedMetaData="kind='attribute' name='parallelEnabled' namespace='##targetNamespace'"
   * @generated
   */
  boolean isParallelEnabled();

  /**
   * Sets the value of the
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isParallelEnabled <em>Parallel
   * Enabled</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Parallel Enabled</em>' attribute.
   * @see #isParallelEnabled()
   * @generated
   */
  void setParallelEnabled(boolean value);

  /**
   * Returns the value of the '<em><b>Parallel Server Group</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Parallel Server Group</em>' attribute.
   * @see #setParallelServerGroup(String)
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchSettings_ParallelServerGroup()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   *        extendedMetaData="kind='attribute' name='parallelServerGroup'
   *        namespace='##targetNamespace'"
   * @generated
   */
  String getParallelServerGroup();

  /**
   * Sets the value of the
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#getParallelServerGroup
   * <em>Parallel Server Group</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Parallel Server Group</em>' attribute.
   * @see #getParallelServerGroup()
   * @generated
   */
  void setParallelServerGroup(String value);

  /**
   * Returns the value of the '<em><b>Pcre Extended Disabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Pcre Extended Disabled</em>' attribute.
   * @see #setPcreExtendedDisabled(boolean)
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchSettings_PcreExtendedDisabled()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
   *        extendedMetaData="kind='attribute' name='pcreExtendedDisabled'
   *        namespace='##targetNamespace'"
   * @generated
   */
  boolean isPcreExtendedDisabled();

  /**
   * Sets the value of the
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreExtendedDisabled
   * <em>Pcre Extended Disabled</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Pcre Extended Disabled</em>' attribute.
   * @see #isPcreExtendedDisabled()
   * @generated
   */
  void setPcreExtendedDisabled(boolean value);

  /**
   * Returns the value of the '<em><b>Pcre Single Line Enabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Pcre Single Line Enabled</em>' attribute.
   * @see #setPcreSingleLineEnabled(boolean)
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchSettings_PcreSingleLineEnabled()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
   *        extendedMetaData="kind='attribute' name='pcreSingleLineEnabled'
   *        namespace='##targetNamespace'"
   * @generated
   */
  boolean isPcreSingleLineEnabled();

  /**
   * Sets the value of the
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings#isPcreSingleLineEnabled
   * <em>Pcre Single Line Enabled</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Pcre Single Line Enabled</em>' attribute.
   * @see #isPcreSingleLineEnabled()
   * @generated
   */
  void setPcreSingleLineEnabled(boolean value);

} // ICodeSearchSettings
