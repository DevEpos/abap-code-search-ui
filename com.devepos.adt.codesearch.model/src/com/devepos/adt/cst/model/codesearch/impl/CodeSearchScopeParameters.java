/**
 */
package com.devepos.adt.cst.model.codesearch.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scope Parameters</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchScopeParameters#getParameters
 * <em>Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CodeSearchScopeParameters extends MinimalEObjectImpl.Container implements
    ICodeSearchScopeParameters {
  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference
   * list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList<ICodeSearchScopeParameter> parameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected CodeSearchScopeParameters() {
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
    return ICodeSearchPackage.Literals.CODE_SEARCH_SCOPE_PARAMETERS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<ICodeSearchScopeParameter> getParameters() {
    if (parameters == null) {
      parameters = new EObjectContainmentEList<>(ICodeSearchScopeParameter.class, this,
          ICodeSearchPackage.CODE_SEARCH_SCOPE_PARAMETERS__PARAMETERS);
    }
    return parameters;
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
    case ICodeSearchPackage.CODE_SEARCH_SCOPE_PARAMETERS__PARAMETERS:
      return ((InternalEList<?>) getParameters()).basicRemove(otherEnd, msgs);
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
    case ICodeSearchPackage.CODE_SEARCH_SCOPE_PARAMETERS__PARAMETERS:
      return getParameters();
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
    case ICodeSearchPackage.CODE_SEARCH_SCOPE_PARAMETERS__PARAMETERS:
      getParameters().clear();
      getParameters().addAll((Collection<? extends ICodeSearchScopeParameter>) newValue);
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
    case ICodeSearchPackage.CODE_SEARCH_SCOPE_PARAMETERS__PARAMETERS:
      getParameters().clear();
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
    case ICodeSearchPackage.CODE_SEARCH_SCOPE_PARAMETERS__PARAMETERS:
      return parameters != null && !parameters.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} // CodeSearchScopeParameters
