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
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject#getUri <em>Uri</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject#getParentUri <em>Parent
 * Uri</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject#getAdtMainObject <em>Adt
 * Main Object</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchObject#getMatches
 * <em>Matches</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CodeSearchObject extends MinimalEObjectImpl.Container implements ICodeSearchObject {
  /**
   * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getUri()
   * @generated
   * @ordered
   */
  protected static final String URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getUri()
   * @generated
   * @ordered
   */
  protected String uri = URI_EDEFAULT;

  /**
   * The default value of the '{@link #getParentUri() <em>Parent Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getParentUri()
   * @generated
   * @ordered
   */
  protected static final String PARENT_URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getParentUri() <em>Parent Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getParentUri()
   * @generated
   * @ordered
   */
  protected String parentUri = PARENT_URI_EDEFAULT;

  /**
   * The cached value of the '{@link #getAdtMainObject() <em>Adt Main Object</em>}' containment
   * reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getAdtMainObject()
   * @generated
   * @ordered
   */
  protected IAdtObjRef adtMainObject;

  /**
   * The cached value of the '{@link #getMatches() <em>Matches</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getMatches()
   * @generated
   * @ordered
   */
  protected EList<ICodeSearchMatch> matches;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected CodeSearchObject() {
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
    return ICodeSearchPackage.Literals.CODE_SEARCH_OBJECT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getUri() {
    return uri;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setUri(final String newUri) {
    String oldUri = uri;
    uri = newUri;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_OBJECT__URI, oldUri, uri));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getParentUri() {
    return parentUri;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setParentUri(final String newParentUri) {
    String oldParentUri = parentUri;
    parentUri = newParentUri;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_OBJECT__PARENT_URI, oldParentUri, parentUri));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public IAdtObjRef getAdtMainObject() {
    return adtMainObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetAdtMainObject(final IAdtObjRef newAdtMainObject,
      NotificationChain msgs) {
    IAdtObjRef oldAdtMainObject = adtMainObject;
    adtMainObject = newAdtMainObject;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT, oldAdtMainObject,
          newAdtMainObject);
      if (msgs == null) {
        msgs = notification;
      } else {
        msgs.add(notification);
      }
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setAdtMainObject(final IAdtObjRef newAdtMainObject) {
    if (newAdtMainObject != adtMainObject) {
      NotificationChain msgs = null;
      if (adtMainObject != null) {
        msgs = ((InternalEObject) adtMainObject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
            - ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT, null, msgs);
      }
      if (newAdtMainObject != null) {
        msgs = ((InternalEObject) newAdtMainObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
            - ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT, null, msgs);
      }
      msgs = basicSetAdtMainObject(newAdtMainObject, msgs);
      if (msgs != null) {
        msgs.dispatch();
      }
    } else if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT, newAdtMainObject,
          newAdtMainObject));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
      final NotificationChain msgs) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT:
      return basicSetAdtMainObject(null, msgs);
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      return ((InternalEList<?>) getMatches()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__URI:
      return getUri();
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__PARENT_URI:
      return getParentUri();
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT:
      return getAdtMainObject();
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      return getMatches();
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
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__URI:
      setUri((String) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__PARENT_URI:
      setParentUri((String) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT:
      setAdtMainObject((IAdtObjRef) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      getMatches().clear();
      getMatches().addAll((Collection<? extends ICodeSearchMatch>) newValue);
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
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__URI:
      setUri(URI_EDEFAULT);
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__PARENT_URI:
      setParentUri(PARENT_URI_EDEFAULT);
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT:
      setAdtMainObject((IAdtObjRef) null);
      return;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      getMatches().clear();
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
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__URI:
      return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__PARENT_URI:
      return PARENT_URI_EDEFAULT == null ? parentUri != null
          : !PARENT_URI_EDEFAULT.equals(parentUri);
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__ADT_MAIN_OBJECT:
      return adtMainObject != null;
    case ICodeSearchPackage.CODE_SEARCH_OBJECT__MATCHES:
      return matches != null && !matches.isEmpty();
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
    result.append(" (uri: ");
    result.append(uri);
    result.append(", parentUri: ");
    result.append(parentUri);
    result.append(')');
    return result.toString();
  }

} // CodeSearchObject
