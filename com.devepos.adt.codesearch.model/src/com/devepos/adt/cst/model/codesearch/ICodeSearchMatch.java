/**
 */
package com.devepos.adt.cst.model.codesearch;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Match</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getUri <em>Uri</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.ICodeSearchMatch#getSnippet
 * <em>Snippet</em>}</li>
 * </ul>
 *
 * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchMatch()
 * @model extendedMetaData="kind='elementOnly' name='codeSearchMatch'"
 * @generated
 */
public interface ICodeSearchMatch extends EObject {
  /**
   * Returns the value of the '<em><b>Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Uri</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchMatch_Uri()
   * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI" suppressedSetVisibility="true"
   *        extendedMetaData="kind='attribute' name='uri' namespace='##targetNamespace'"
   * @generated
   */
  String getUri();

  /**
   * Returns the value of the '<em><b>Snippet</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Snippet</em>' attribute.
   * @see com.devepos.adt.cst.model.codesearch.ICodeSearchPackage#getCodeSearchMatch_Snippet()
   * @model suppressedSetVisibility="true"
   *        extendedMetaData="kind='element' name='snippet' namespace='##targetNamespace'"
   * @generated
   */
  String getSnippet();

} // ICodeSearchMatch
