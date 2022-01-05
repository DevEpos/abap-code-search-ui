package com.devepos.adt.cst.internal.search;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.devepos.adt.base.content.AbstractEmfContentHandler;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.devepos.adt.cst.model.codesearch.util.CodeSearchResourceFactory;
import com.sap.adt.communication.content.AdtMediaType;

/**
 * Content Handler for serializing/deserializing the result from Code Search
 *
 * @author Ludwig Stockbauer-Muhr
 */
public class CodeSearchContentHandler extends AbstractEmfContentHandler<ICodeSearchResult> {

  public CodeSearchContentHandler() {
    super(AdtMediaType.APPLICATION_XML, ".codesearchresult");
  }

  @Override
  public Class<ICodeSearchResult> getSupportedDataType() {
    return ICodeSearchResult.class;
  }

  @Override
  protected Resource createResource() {
    return new CodeSearchResourceFactory().createResource(getVirtualResourceUri());
  }

  @Override
  protected ICodeSearchResult getRootElement(final EObject rootElement) {
    return (ICodeSearchResult) rootElement;
  }

}
