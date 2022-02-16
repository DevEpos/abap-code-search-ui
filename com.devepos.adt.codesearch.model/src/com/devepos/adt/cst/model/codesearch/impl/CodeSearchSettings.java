/**
 */
package com.devepos.adt.cst.model.codesearch.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;
import com.devepos.adt.cst.model.codesearch.ICodeSearchSettings;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Settings</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings#isParallelEnabled
 * <em>Parallel Enabled</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings#getParallelServerGroup
 * <em>Parallel Server Group</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings#isPcreExtendedDisabled
 * <em>Pcre Extended Disabled</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchSettings#isPcreSingleLineEnabled
 * <em>Pcre Single Line Enabled</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CodeSearchSettings extends MinimalEObjectImpl.Container implements
    ICodeSearchSettings {
  /**
   * The default value of the '{@link #isParallelEnabled() <em>Parallel Enabled</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #isParallelEnabled()
   * @generated
   * @ordered
   */
  protected static final boolean PARALLEL_ENABLED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isParallelEnabled() <em>Parallel Enabled</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #isParallelEnabled()
   * @generated
   * @ordered
   */
  protected boolean parallelEnabled = PARALLEL_ENABLED_EDEFAULT;

  /**
   * The default value of the '{@link #getParallelServerGroup() <em>Parallel Server Group</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getParallelServerGroup()
   * @generated
   * @ordered
   */
  protected static final String PARALLEL_SERVER_GROUP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getParallelServerGroup() <em>Parallel Server Group</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getParallelServerGroup()
   * @generated
   * @ordered
   */
  protected String parallelServerGroup = PARALLEL_SERVER_GROUP_EDEFAULT;

  /**
   * The default value of the '{@link #isPcreExtendedDisabled() <em>Pcre Extended Disabled</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #isPcreExtendedDisabled()
   * @generated
   * @ordered
   */
  protected static final boolean PCRE_EXTENDED_DISABLED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isPcreExtendedDisabled() <em>Pcre Extended Disabled</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #isPcreExtendedDisabled()
   * @generated
   * @ordered
   */
  protected boolean pcreExtendedDisabled = PCRE_EXTENDED_DISABLED_EDEFAULT;

  /**
   * The default value of the '{@link #isPcreSingleLineEnabled() <em>Pcre Single Line Enabled</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #isPcreSingleLineEnabled()
   * @generated
   * @ordered
   */
  protected static final boolean PCRE_SINGLE_LINE_ENABLED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isPcreSingleLineEnabled() <em>Pcre Single Line Enabled</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #isPcreSingleLineEnabled()
   * @generated
   * @ordered
   */
  protected boolean pcreSingleLineEnabled = PCRE_SINGLE_LINE_ENABLED_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected CodeSearchSettings() {
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
    return ICodeSearchPackage.Literals.CODE_SEARCH_SETTINGS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean isParallelEnabled() {
    return parallelEnabled;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setParallelEnabled(final boolean newParallelEnabled) {
    boolean oldParallelEnabled = parallelEnabled;
    parallelEnabled = newParallelEnabled;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_ENABLED, oldParallelEnabled,
          parallelEnabled));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String getParallelServerGroup() {
    return parallelServerGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setParallelServerGroup(final String newParallelServerGroup) {
    String oldParallelServerGroup = parallelServerGroup;
    parallelServerGroup = newParallelServerGroup;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_SERVER_GROUP, oldParallelServerGroup,
          parallelServerGroup));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean isPcreExtendedDisabled() {
    return pcreExtendedDisabled;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setPcreExtendedDisabled(final boolean newPcreExtendedDisabled) {
    boolean oldPcreExtendedDisabled = pcreExtendedDisabled;
    pcreExtendedDisabled = newPcreExtendedDisabled;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_EXTENDED_DISABLED, oldPcreExtendedDisabled,
          pcreExtendedDisabled));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean isPcreSingleLineEnabled() {
    return pcreSingleLineEnabled;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setPcreSingleLineEnabled(final boolean newPcreSingleLineEnabled) {
    boolean oldPcreSingleLineEnabled = pcreSingleLineEnabled;
    pcreSingleLineEnabled = newPcreSingleLineEnabled;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_SINGLE_LINE_ENABLED,
          oldPcreSingleLineEnabled, pcreSingleLineEnabled));
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
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_ENABLED:
      return isParallelEnabled();
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_SERVER_GROUP:
      return getParallelServerGroup();
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_EXTENDED_DISABLED:
      return isPcreExtendedDisabled();
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_SINGLE_LINE_ENABLED:
      return isPcreSingleLineEnabled();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eSet(final int featureID, final Object newValue) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_ENABLED:
      setParallelEnabled((Boolean) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_SERVER_GROUP:
      setParallelServerGroup((String) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_EXTENDED_DISABLED:
      setPcreExtendedDisabled((Boolean) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_SINGLE_LINE_ENABLED:
      setPcreSingleLineEnabled((Boolean) newValue);
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
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_ENABLED:
      setParallelEnabled(PARALLEL_ENABLED_EDEFAULT);
      return;
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_SERVER_GROUP:
      setParallelServerGroup(PARALLEL_SERVER_GROUP_EDEFAULT);
      return;
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_EXTENDED_DISABLED:
      setPcreExtendedDisabled(PCRE_EXTENDED_DISABLED_EDEFAULT);
      return;
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_SINGLE_LINE_ENABLED:
      setPcreSingleLineEnabled(PCRE_SINGLE_LINE_ENABLED_EDEFAULT);
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
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_ENABLED:
      return parallelEnabled != PARALLEL_ENABLED_EDEFAULT;
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PARALLEL_SERVER_GROUP:
      return PARALLEL_SERVER_GROUP_EDEFAULT == null ? parallelServerGroup != null
          : !PARALLEL_SERVER_GROUP_EDEFAULT.equals(parallelServerGroup);
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_EXTENDED_DISABLED:
      return pcreExtendedDisabled != PCRE_EXTENDED_DISABLED_EDEFAULT;
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS__PCRE_SINGLE_LINE_ENABLED:
      return pcreSingleLineEnabled != PCRE_SINGLE_LINE_ENABLED_EDEFAULT;
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
    result.append(" (parallelEnabled: ");
    result.append(parallelEnabled);
    result.append(", parallelServerGroup: ");
    result.append(parallelServerGroup);
    result.append(", pcreExtendedDisabled: ");
    result.append(pcreExtendedDisabled);
    result.append(", pcreSingleLineEnabled: ");
    result.append(pcreSingleLineEnabled);
    result.append(')');
    return result.toString();
  }

} // CodeSearchSettings
