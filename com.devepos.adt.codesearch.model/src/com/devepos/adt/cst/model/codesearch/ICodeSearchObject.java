/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.devepos.adt.base.model.adtbase.IAdtObjRef;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Object</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getAdtObjectRef
 * <em>Adt Object Ref</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getMatches
 * <em>Matches</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getAdtMainObjectRef
 * <em>Adt Main Object Ref</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchObject()
 * @model extendedMetaData="kind='elementOnly' name='searchObject'"
 * @generated
 */
public interface ICodeSearchObject extends EObject {
  /**
   * Returns the value of the '<em><b>Adt Object Ref</b></em>' containment
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the value of the '<em>Adt Object Ref</em>' containment reference.
   * @see #setAdtObjectRef(IAdtObjRef)
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchObject_AdtObjectRef()
   * @model containment="true" extendedMetaData="kind='element' name='adtObjRef'
   *        namespace='http://www.devepos.com/adt/base'"
   * @generated
   */
  IAdtObjRef getAdtObjectRef();

  /**
   * Sets the value of the
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getAdtObjectRef
   * <em>Adt Object Ref</em>}' containment reference. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @param value the new value of the '<em>Adt Object Ref</em>' containment
   *              reference.
   * @see #getAdtObjectRef()
   * @generated
   */
  void setAdtObjectRef(IAdtObjRef value);

  /**
   * Returns the value of the '<em><b>Matches</b></em>' containment reference
   * list. The list contents are of type
   * {@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch}. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the value of the '<em>Matches</em>' containment reference list.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchObject_Matches()
   * @model containment="true" extendedMetaData="kind='element' name='match'
   *        namespace='##targetNamespace'"
   * @generated
   */
  EList<ICodeSearchMatch> getMatches();

  /**
   * Returns the value of the '<em><b>Adt Main Object Ref</b></em>' containment
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the value of the '<em>Adt Main Object Ref</em>' containment
   *         reference.
   * @see #setAdtMainObjectRef(IAdtObjRef)
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchObject_AdtMainObjectRef()
   * @model containment="true" extendedMetaData="kind='element'
   *        name='adtMainObjRef' namespace='http://www.devepos.com/adt/base'"
   * @generated
   */
  IAdtObjRef getAdtMainObjectRef();

  /**
   * Sets the value of the
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject#getAdtMainObjectRef
   * <em>Adt Main Object Ref</em>}' containment reference. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Adt Main Object Ref</em>' containment
   *              reference.
   * @see #getAdtMainObjectRef()
   * @generated
   */
  void setAdtMainObjectRef(IAdtObjRef value);

} // ICodeSearchObject
