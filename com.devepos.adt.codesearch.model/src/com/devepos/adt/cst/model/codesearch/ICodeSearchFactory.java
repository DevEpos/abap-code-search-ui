/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage
 * @generated
 */
public interface ICodeSearchFactory extends EFactory {
  /**
   * The singleton instance of the factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @generated
   */
  ICodeSearchFactory eINSTANCE = com.devepos.adt.cst.model.codesearch.impl.CodeSearchFactory.init();

  /**
   * Returns a new object of class '<em>Object</em>'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @return a new object of class '<em>Object</em>'.
   * @generated
   */
  ICodeSearchObject createCodeSearchObject();

  /**
   * Returns a new object of class '<em>Match</em>'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @return a new object of class '<em>Match</em>'.
   * @generated
   */
  ICodeSearchMatch createCodeSearchMatch();

  /**
   * Returns a new object of class '<em>Result</em>'. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @return a new object of class '<em>Result</em>'.
   * @generated
   */
  ICodeSearchResult createCodeSearchResult();

  /**
   * Returns a new object of class '<em>Settings</em>'. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return a new object of class '<em>Settings</em>'.
   * @generated
   */
  ICodeSearchSettings createCodeSearchSettings();

  /**
   * Returns the package supported by this factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   *
   * @return the package supported by this factory.
   * @generated
   */
  ICodeSearchPackage getCodeSearchPackage();

} // ICodeSearchFactory
