/**
 */
package com.devepos.adt.cst.model.codesearch.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import com.devepos.adt.cst.model.codesearch.ICodeSearchMatch;
import com.devepos.adt.cst.model.codesearch.ICodeSearchObject;
import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScope;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters;
import com.devepos.adt.cst.model.codesearch.ICodeSearchSettings;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage
 * @generated
 */
public class CodeSearchAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected static ICodeSearchPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public CodeSearchAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = ICodeSearchPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is
   * an instance object of the model.
   * <!-- end-user-doc -->
   *
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(final Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject) object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected CodeSearchSwitch<Adapter> modelSwitch = new CodeSearchSwitch<>() {
    @Override
    public Adapter caseCodeSearchObject(final ICodeSearchObject object) {
      return createCodeSearchObjectAdapter();
    }

    @Override
    public Adapter caseCodeSearchMatch(final ICodeSearchMatch object) {
      return createCodeSearchMatchAdapter();
    }

    @Override
    public Adapter caseCodeSearchResult(final ICodeSearchResult object) {
      return createCodeSearchResultAdapter();
    }

    @Override
    public Adapter caseCodeSearchSettings(final ICodeSearchSettings object) {
      return createCodeSearchSettingsAdapter();
    }

    @Override
    public Adapter caseCodeSearchScopeParameter(final ICodeSearchScopeParameter object) {
      return createCodeSearchScopeParameterAdapter();
    }

    @Override
    public Adapter caseCodeSearchScope(final ICodeSearchScope object) {
      return createCodeSearchScopeAdapter();
    }

    @Override
    public Adapter caseCodeSearchScopeParameters(final ICodeSearchScopeParameters object) {
      return createCodeSearchScopeParametersAdapter();
    }

    @Override
    public Adapter defaultCase(final EObject object) {
      return createEObjectAdapter();
    }
  };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(final Notifier target) {
    return modelSwitch.doSwitch((EObject) target);
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchObject <em>Object</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchObject
   * @generated
   */
  public Adapter createCodeSearchObjectAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch <em>Match</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchMatch
   * @generated
   */
  public Adapter createCodeSearchMatchAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchResult <em>Result</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchResult
   * @generated
   */
  public Adapter createCodeSearchResultAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchSettings <em>Settings</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchSettings
   * @generated
   */
  public Adapter createCodeSearchSettingsAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter <em>Scope
   * Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameter
   * @generated
   */
  public Adapter createCodeSearchScopeParameterAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScope <em>Scope</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScope
   * @generated
   */
  public Adapter createCodeSearchScopeAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class
   * '{@link com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters <em>Scope
   * Parameters</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters
   * @generated
   */
  public Adapter createCodeSearchScopeParametersAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} // CodeSearchAdapterFactory
