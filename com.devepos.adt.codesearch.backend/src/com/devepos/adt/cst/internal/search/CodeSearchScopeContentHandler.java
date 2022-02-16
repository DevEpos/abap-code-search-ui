package com.devepos.adt.cst.internal.search;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.devepos.adt.base.content.AbstractEmfContentHandler;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScope;
import com.devepos.adt.cst.model.codesearch.util.CodeSearchResourceFactory;
import com.sap.adt.communication.content.AdtMediaType;

public class CodeSearchScopeContentHandler extends AbstractEmfContentHandler<ICodeSearchScope> {

  public CodeSearchScopeContentHandler() {
    super(AdtMediaType.APPLICATION_XML, ".codesearchscope");
  }

  @Override
  public Class<ICodeSearchScope> getSupportedDataType() {
    return ICodeSearchScope.class;
  }

  @Override
  protected Resource createResource() {
    return new CodeSearchResourceFactory().createResource(getVirtualResourceUri());
  }

  @Override
  protected ICodeSearchScope getRootElement(final EObject rootElement) {
    return (ICodeSearchScope) rootElement;
  }

}
