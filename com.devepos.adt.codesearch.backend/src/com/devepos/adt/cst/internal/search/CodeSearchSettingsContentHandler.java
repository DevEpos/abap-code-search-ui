package com.devepos.adt.cst.internal.search;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.devepos.adt.base.content.AbstractEmfContentHandler;
import com.devepos.adt.cst.model.codesearch.ICodeSearchSettings;
import com.devepos.adt.cst.model.codesearch.util.CodeSearchResourceFactory;
import com.sap.adt.communication.content.AdtMediaType;

/**
 * Content handler for serializing/deserializing settings for the Code Search
 * 
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchSettingsContentHandler extends
    AbstractEmfContentHandler<ICodeSearchSettings> {

  public CodeSearchSettingsContentHandler() {
    super(AdtMediaType.APPLICATION_XML, ".codesearchSettings");
  }

  @Override
  public Class<ICodeSearchSettings> getSupportedDataType() {
    return ICodeSearchSettings.class;
  }

  @Override
  protected Resource createResource() {
    return new CodeSearchResourceFactory().createResource(getVirtualResourceUri());
  }

  @Override
  protected ICodeSearchSettings getRootElement(final EObject rootElement) {
    return (ICodeSearchSettings) rootElement;
  }

}
