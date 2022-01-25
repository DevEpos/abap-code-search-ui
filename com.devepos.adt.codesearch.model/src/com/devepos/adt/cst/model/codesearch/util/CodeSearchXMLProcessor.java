/**
 */
package com.devepos.adt.cst.model.codesearch.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class CodeSearchXMLProcessor extends XMLProcessor {

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public CodeSearchXMLProcessor() {
    super(EPackage.Registry.INSTANCE);
    ICodeSearchPackage.eINSTANCE.eClass();
  }

  /**
   * Register for "*" and "xml" file extensions the CodeSearchResourceFactory factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected Map<String, Resource.Factory> getRegistrations() {
    if (registrations == null) {
      super.getRegistrations();
      registrations.put(XML_EXTENSION, new CodeSearchResourceFactory());
      registrations.put(STAR_EXTENSION, new CodeSearchResourceFactory());
    }
    return registrations;
  }

} // CodeSearchXMLProcessor
