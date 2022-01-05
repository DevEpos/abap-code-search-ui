/**
 */
package com.devepos.adt.cst.model.codesearch.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.devepos.adt.cst.model.codesearch.ICodeSearchFactory;
import com.devepos.adt.cst.model.codesearch.ICodeSearchMatch;
import com.devepos.adt.cst.model.codesearch.ICodeSearchObject;
import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.devepos.adt.cst.model.codesearch.ICodeSearchSettings;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class CodeSearchFactory extends EFactoryImpl implements ICodeSearchFactory {
  /**
   * Creates the default factory implementation. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @generated
   */
  public static ICodeSearchFactory init() {
    try {
      ICodeSearchFactory theCodeSearchFactory = (ICodeSearchFactory) EPackage.Registry.INSTANCE
          .getEFactory(ICodeSearchPackage.eNS_URI);
      if (theCodeSearchFactory != null) {
        return theCodeSearchFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CodeSearchFactory();
  }

  /**
   * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc
   * -->
   *
   * @generated
   */
  public CodeSearchFactory() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EObject create(final EClass eClass) {
    switch (eClass.getClassifierID()) {
    case ICodeSearchPackage.CODE_SEARCH_OBJECT:
      return createCodeSearchObject();
    case ICodeSearchPackage.CODE_SEARCH_MATCH:
      return createCodeSearchMatch();
    case ICodeSearchPackage.CODE_SEARCH_RESULT:
      return createCodeSearchResult();
    case ICodeSearchPackage.CODE_SEARCH_SETTINGS:
      return createCodeSearchSettings();
    default:
      throw new IllegalArgumentException("The class '" + eClass.getName()
          + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public ICodeSearchObject createCodeSearchObject() {
    CodeSearchObject codeSearchObject = new CodeSearchObject();
    return codeSearchObject;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public ICodeSearchMatch createCodeSearchMatch() {
    CodeSearchMatch codeSearchMatch = new CodeSearchMatch();
    return codeSearchMatch;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public ICodeSearchResult createCodeSearchResult() {
    CodeSearchResult codeSearchResult = new CodeSearchResult();
    return codeSearchResult;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public ICodeSearchSettings createCodeSearchSettings() {
    CodeSearchSettings codeSearchSettings = new CodeSearchSettings();
    return codeSearchSettings;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public ICodeSearchPackage getCodeSearchPackage() {
    return (ICodeSearchPackage) getEPackage();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ICodeSearchPackage getPackage() {
    return ICodeSearchPackage.eINSTANCE;
  }

} // CodeSearchFactory
