/**
 */
package com.devepos.adt.cst.model.codesearch.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage
 * @generated
 */
public class CodeSearchSwitch<T> extends Switch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected static ICodeSearchPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public CodeSearchSwitch() {
    if (modelPackage == null) {
      modelPackage = ICodeSearchPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(final EPackage ePackage) {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it
   * yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(final int classifierID, final EObject theEObject) {
    switch (classifierID) {
    case ICodeSearchPackage.CODE_SEARCH_OBJECT: {
      ICodeSearchObject codeSearchObject = (ICodeSearchObject) theEObject;
      T result = caseCodeSearchObject(codeSearchObject);
      if (result == null) {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case ICodeSearchPackage.CODE_SEARCH_MATCH: {
      ICodeSearchMatch codeSearchMatch = (ICodeSearchMatch) theEObject;
      T result = caseCodeSearchMatch(codeSearchMatch);
      if (result == null) {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case ICodeSearchPackage.CODE_SEARCH_RESULT: {
      ICodeSearchResult codeSearchResult = (ICodeSearchResult) theEObject;
      T result = caseCodeSearchResult(codeSearchResult);
      if (result == null) {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS: {
      ICodeSearchSettings codeSearchSettings = (ICodeSearchSettings) theEObject;
      T result = caseCodeSearchSettings(codeSearchSettings);
      if (result == null) {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case ICodeSearchPackage.CODE_SEARCH_SCOPE_PARAMETER: {
      ICodeSearchScopeParameter codeSearchScopeParameter = (ICodeSearchScopeParameter) theEObject;
      T result = caseCodeSearchScopeParameter(codeSearchScopeParameter);
      if (result == null) {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case ICodeSearchPackage.CODE_SEARCH_SCOPE: {
      ICodeSearchScope codeSearchScope = (ICodeSearchScope) theEObject;
      T result = caseCodeSearchScope(codeSearchScope);
      if (result == null) {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case ICodeSearchPackage.CODE_SEARCH_SCOPE_PARAMETERS: {
      ICodeSearchScopeParameters codeSearchScopeParameters = (ICodeSearchScopeParameters) theEObject;
      T result = caseCodeSearchScopeParameters(codeSearchScopeParameters);
      if (result == null) {
        result = defaultCase(theEObject);
      }
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Object</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Object</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeSearchObject(final ICodeSearchObject object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Match</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Match</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeSearchMatch(final ICodeSearchMatch object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Result</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Result</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeSearchResult(final ICodeSearchResult object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Settings</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Settings</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeSearchSettings(final ICodeSearchSettings object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Scope Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Scope Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeSearchScopeParameter(final ICodeSearchScopeParameter object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Scope</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Scope</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeSearchScope(final ICodeSearchScope object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Scope Parameters</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Scope Parameters</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCodeSearchScopeParameters(final ICodeSearchScopeParameters object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(final EObject object) {
    return null;
  }

} // CodeSearchSwitch
