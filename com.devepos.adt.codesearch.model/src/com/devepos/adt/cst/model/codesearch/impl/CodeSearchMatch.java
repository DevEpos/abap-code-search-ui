/**
 */
package com.devepos.adt.cst.model.codesearch.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import com.devepos.adt.cst.model.codesearch.ICodeSearchMatch;
import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Match</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch#getUri
 * <em>Uri</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchMatch#getSnippet
 * <em>Snippet</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CodeSearchMatch extends MinimalEObjectImpl.Container implements ICodeSearchMatch {
  /**
   * The default value of the '{@link #getUri() <em>Uri</em>}' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getUri()
   * @generated
   * @ordered
   */
  protected static final String URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getUri()
   * @generated
   * @ordered
   */
  protected String uri = URI_EDEFAULT;

  /**
   * The default value of the '{@link #getSnippet() <em>Snippet</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getSnippet()
   * @generated
   * @ordered
   */
  protected static final String SNIPPET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSnippet() <em>Snippet</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getSnippet()
   * @generated
   * @ordered
   */
  protected String snippet = SNIPPET_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected CodeSearchMatch() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ICodeSearchPackage.Literals.CODE_SEARCH_MATCH;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getUri() {
    return uri;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setUri(final String newUri) {
    String oldUri = uri;
    uri = newUri;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_MATCH__URI, oldUri, uri));
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getSnippet() {
    return snippet;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setSnippet(final String newSnippet) {
    String oldSnippet = snippet;
    snippet = newSnippet;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_MATCH__SNIPPET, oldSnippet, snippet));
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_MATCH__URI:
      return getUri();
    case ICodeSearchPackage.CODE_SEARCH_MATCH__SNIPPET:
      return getSnippet();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eSet(final int featureID, final Object newValue) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_MATCH__URI:
      setUri((String) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_MATCH__SNIPPET:
      setSnippet((String) newValue);
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
    case ICodeSearchPackage.CODE_SEARCH_MATCH__URI:
      setUri(URI_EDEFAULT);
      return;
    case ICodeSearchPackage.CODE_SEARCH_MATCH__SNIPPET:
      setSnippet(SNIPPET_EDEFAULT);
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
    case ICodeSearchPackage.CODE_SEARCH_MATCH__URI:
      return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
    case ICodeSearchPackage.CODE_SEARCH_MATCH__SNIPPET:
      return SNIPPET_EDEFAULT == null ? snippet != null : !SNIPPET_EDEFAULT.equals(snippet);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
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
    result.append(", snippet: ");
    result.append(snippet);
    result.append(')');
    return result.toString();
  }

} // CodeSearchMatch
