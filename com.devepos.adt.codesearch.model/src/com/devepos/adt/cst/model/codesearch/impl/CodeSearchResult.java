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

import com.devepos.adt.cst.model.codesearch.ICodeSearchObject;
import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Result</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult#getSearchObjects
 * <em>Search Objects</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CodeSearchResult extends MinimalEObjectImpl.Container implements ICodeSearchResult {
  /**
   * The cached value of the '{@link #getSearchObjects() <em>Search Objects</em>}'
   * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @see #getSearchObjects()
   * @generated
   * @ordered
   */
  protected EList<ICodeSearchObject> searchObjects;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  protected CodeSearchResult() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ICodeSearchPackage.Literals.CODE_SEARCH_RESULT;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<ICodeSearchObject> getSearchObjects() {
    if (searchObjects == null) {
      searchObjects = new EObjectContainmentEList<>(ICodeSearchObject.class, this,
          ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS);
    }
    return searchObjects;
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
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      return ((InternalEList<?>) getSearchObjects()).basicRemove(otherEnd, msgs);
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
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      return getSearchObjects();
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
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      getSearchObjects().clear();
      getSearchObjects().addAll((Collection<? extends ICodeSearchObject>) newValue);
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
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      getSearchObjects().clear();
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
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      return searchObjects != null && !searchObjects.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} // CodeSearchResult
