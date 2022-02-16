package com.devepos.adt.cst.internal.search;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.devepos.adt.base.content.AbstractEmfContentHandler;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters;
import com.devepos.adt.cst.model.codesearch.util.CodeSearchResourceFactory;
import com.sap.adt.communication.content.AdtMediaType;

public class CodeSearchScopeParametersContentHandler extends
    AbstractEmfContentHandler<ICodeSearchScopeParameters> {

  public CodeSearchScopeParametersContentHandler() {
    super(AdtMediaType.APPLICATION_XML, ".codesearchscopeparameters");
  }

  @Override
  public Class<ICodeSearchScopeParameters> getSupportedDataType() {
    return ICodeSearchScopeParameters.class;
  }

  @Override
  protected Resource createResource() {
    return new CodeSearchResourceFactory().createResource(getVirtualResourceUri());
  }

  @Override
  protected ICodeSearchScopeParameters getRootElement(final EObject rootElement) {
    return (ICodeSearchScopeParameters) rootElement;
  }
}
