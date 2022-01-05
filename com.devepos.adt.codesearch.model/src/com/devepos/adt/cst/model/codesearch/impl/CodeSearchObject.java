/**
 */
package com.devepos.adt.cst.model.codesearch.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.devepos.adt.base.model.adtbase.IAdtObjRef;
import com.devepos.adt.cst.model.codesearch.ICodeSearchMatch;
import com.devepos.adt.cst.model.codesearch.ICodeSearchObject;
import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Object</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject#getAdtObjectRef
 * <em>Adt Object Ref</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject#getMatches
 * <em>Matches</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject#getAdtMainObjectRef
 * <em>Adt Main Object Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CodeSearchObject extends MinimalEObjectImpl.Container implements ICodeSearchObject {
  /**
   * The cached value of the '{@link #getAdtObjectRef() <em>Adt Object Ref</em>}'
   * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getAdtObjectRef()
   * @generated
   * @ordered
   */
  protected IAdtObjRef adtObjectRef;

  /**
   * The cached value of the '{@link #getMatches() <em>Matches</em>}' containment
   * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getMatches()
   * @generated
   * @ordered
   */
  protected EList<ICodeSearchMatch> matches;

  /**
   * The cached value of the '{@link #getAdtMainObjectRef() <em>Adt Main Object
   * Ref</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @see #getAdtMainObjectRef()
   * @generated
   * @ordered
   */
  protected IAdtObjRef adtMainObjectRef;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected CodeSearchObject() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ICodeSearchPackage.Literals.CODE_SEARCH_OBJECT;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public IAdtObjRef getAdtObjectRef() {
    return adtObjectRef;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetAdtObjectRef(final IAdtObjRef newAdtObjectRef,
      NotificationChain msgs) {
    IAdtObjRef oldAdtObjectRef = adtObjectRef;
    adtObjectRef = newAdtObjectRef;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_OBJECT_REF, oldAdtObjectRef, newAdtObjectRef);
      if (msgs == null) {
        msgs = notification;
      } else {
        msgs.add(notification);
      }
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setAdtObjectRef(final IAdtObjRef newAdtObjectRef) {
    if (newAdtObjectRef != adtObjectRef) {
      NotificationChain msgs = null;
      if (adtObjectRef != null) {
        msgs = ((InternalEObject) adtObjectRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
            - ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_OBJECT_REF, null, msgs);
      }
      if (newAdtObjectRef != null) {
        msgs = ((InternalEObject) newAdtObjectRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
            - ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_OBJECT_REF, null, msgs);
      }
      msgs = basicSetAdtObjectRef(newAdtObjectRef, msgs);
      if (msgs != null) {
        msgs.dispatch();
      }
    } else if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_OBJECT_REF, newAdtObjectRef, newAdtObjectRef));
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<ICodeSearchMatch> getMatches() {
    if (matches == null) {
      matches = new EObjectContainmentEList<>(ICodeSearchMatch.class, this,
          ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES);
    }
    return matches;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public IAdtObjRef getAdtMainObjectRef() {
    return adtMainObjectRef;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetAdtMainObjectRef(final IAdtObjRef newAdtMainObjectRef,
      NotificationChain msgs) {
    IAdtObjRef oldAdtMainObjectRef = adtMainObjectRef;
    adtMainObjectRef = newAdtMainObjectRef;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT_REF, oldAdtMainObjectRef,
          newAdtMainObjectRef);
      if (msgs == null) {
        msgs = notification;
      } else {
        msgs.add(notification);
      }
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setAdtMainObjectRef(final IAdtObjRef newAdtMainObjectRef) {
    if (newAdtMainObjectRef != adtMainObjectRef) {
      NotificationChain msgs = null;
      if (adtMainObjectRef != null) {
        msgs = ((InternalEObject) adtMainObjectRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
            - ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT_REF, null, msgs);
      }
      if (newAdtMainObjectRef != null) {
        msgs = ((InternalEObject) newAdtMainObjectRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
            - ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT_REF, null, msgs);
      }
      msgs = basicSetAdtMainObjectRef(newAdtMainObjectRef, msgs);
      if (msgs != null) {
        msgs.dispatch();
      }
    } else if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT_REF, newAdtMainObjectRef,
          newAdtMainObjectRef));
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
      final NotificationChain msgs) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_OBJECT_REF:
      return basicSetAdtObjectRef(null, msgs);
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      return ((InternalEList<?>) getMatches()).basicRemove(otherEnd, msgs);
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT_REF:
      return basicSetAdtMainObjectRef(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_OBJECT_REF:
      return getAdtObjectRef();
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      return getMatches();
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT_REF:
      return getAdtMainObjectRef();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(final int featureID, final Object newValue) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_OBJECT_REF:
      setAdtObjectRef((IAdtObjRef) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      getMatches().clear();
      getMatches().addAll((Collection<? extends ICodeSearchMatch>) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT_REF:
      setAdtMainObjectRef((IAdtObjRef) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eUnset(final int featureID) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_OBJECT_REF:
      setAdtObjectRef((IAdtObjRef) null);
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      getMatches().clear();
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT_REF:
      setAdtMainObjectRef((IAdtObjRef) null);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean eIsSet(final int featureID) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_OBJECT_REF:
      return adtObjectRef != null;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      return matches != null && !matches.isEmpty();
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT_REF:
      return adtMainObjectRef != null;
    }
    return super.eIsSet(featureID);
  }

} // CodeSearchObject
