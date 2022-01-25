/**
 */
package com.devepos.adt.cst.model.codesearch.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScope;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchScope#getId <em>Id</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchScope#getObjectCount <em>Object
 * Count</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CodeSearchScope extends MinimalEObjectImpl.Container implements ICodeSearchScope {
  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getObjectCount() <em>Object Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getObjectCount()
   * @generated
   * @ordered
   */
  protected static final int OBJECT_COUNT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getObjectCount() <em>Object Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getObjectCount()
   * @generated
   * @ordered
   */
  protected int objectCount = OBJECT_COUNT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected CodeSearchScope() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ICodeSearchPackage.Literals.CODE_SEARCH_SCOPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setId(final String newId) {
    String oldId = id;
    id = newId;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_SCOPE__ID, oldId, id));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public int getObjectCount() {
    return objectCount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setObjectCount(final int newObjectCount) {
    int oldObjectCount = objectCount;
    objectCount = newObjectCount;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_SCOPE__OBJECT_COUNT, oldObjectCount, objectCount));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_SCOPE__ID:
      return getId();
    case ICodeSearchPackage.CODE_SEARCH_SCOPE__OBJECT_COUNT:
      return getObjectCount();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(final int featureID, final Object newValue) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_SCOPE__ID:
      setId((String) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_SCOPE__OBJECT_COUNT:
      setObjectCount((Integer) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eUnset(final int featureID) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_SCOPE__ID:
      setId(ID_EDEFAULT);
      return;
    case ICodeSearchPackage.CODE_SEARCH_SCOPE__OBJECT_COUNT:
      setObjectCount(OBJECT_COUNT_EDEFAULT);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean eIsSet(final int featureID) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_SCOPE__ID:
      return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
    case ICodeSearchPackage.CODE_SEARCH_SCOPE__OBJECT_COUNT:
      return objectCount != OBJECT_COUNT_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) {
      return super.toString();
    }

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (id: ");
    result.append(id);
    result.append(", objectCount: ");
    result.append(objectCount);
    result.append(')');
    return result.toString();
  }

} // CodeSearchScope
