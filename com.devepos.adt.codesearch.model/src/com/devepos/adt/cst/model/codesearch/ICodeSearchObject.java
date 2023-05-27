/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.devepos.adt.base.model.adtbase.IAdtObjRef;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getUri <em>Uri</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getParentUri <em>Parent
 * Uri</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getAdtMainObject <em>Adt Main
 * Object</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getMatches
 * <em>Matches</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchObject()
 * @model extendedMetaData="kind='elementOnly' name='searchObject'"
 * @generated
 */
public interface ICodeSearchObject extends EObject {
  /**
   * Returns the value of the '<em><b>Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Uri</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchObject_Uri()
   * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='uri' namespace='##targetNamespace'"
   * @generated
   */
  String getUri();

  /**
   * Returns the value of the '<em><b>Parent Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Parent Uri</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchObject_ParentUri()
   * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='parentUri' namespace='##targetNamespace'"
   * @generated
   */
  String getParentUri();

  /**
   * Returns the value of the '<em><b>Adt Main Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Adt Main Object</em>' containment reference.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchObject_AdtMainObject()
   * @model containment="true" suppressedSetVisibility="true"
   *        extendedMetaData="kind='element' name='adtMainObject'
   *        namespace='http://www.devepos.com/adt/base'"
   * @generated
   */
  IAdtObjRef getAdtMainObject();

  /**
   * Returns the value of the '<em><b>Matches</b></em>' containment reference list.
   * The list contents are of type {@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Matches</em>' containment reference list.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchObject_Matches()
   * @model containment="true" suppressedSetVisibility="true"
   *        extendedMetaData="kind='element' name='match' namespace='##targetNamespace'"
   * @generated
   */
  EList<ICodeSearchMatch> getMatches();

} // ICodeSearchObject
