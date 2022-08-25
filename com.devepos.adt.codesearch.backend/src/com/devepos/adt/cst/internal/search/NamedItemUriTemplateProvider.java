package com.devepos.adt.cst.internal.search;

import com.devepos.adt.base.IAdtUriTemplateProvider;
import com.devepos.adt.base.project.IAbapProjectProvider;
import com.devepos.adt.base.util.IPropertyChangeListener;
import com.devepos.adt.base.util.PropertyChangeEvent;
import com.sap.adt.compatibility.uritemplate.IAdtUriTemplate;

/**
 * URI template provider for named items in the context of the ABAP code search
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
class NamedItemUriTemplateProvider implements IAdtUriTemplateProvider, IPropertyChangeListener {

  private IAbapProjectProvider projectProvider;
  private CodeSearchUriDiscovery uriDiscovery;

  public NamedItemUriTemplateProvider(final IAbapProjectProvider projectProvider) {
    this.projectProvider = projectProvider;
    this.projectProvider.addPropertyChangeListener(this);
  }

  @Override
  public IAdtUriTemplate getTemplateByDiscoveryTerm(final String discoveryTerm) {
    initUriDiscovery();
    return uriDiscovery != null ? uriDiscovery.getNamedItemTemplate(discoveryTerm) : null;
  }

  @Override
  public void propertyChanged(final PropertyChangeEvent event) {
    if (!event.getProperty().equals(IAbapProjectProvider.PROPERTY_PROJECT)) {
      return;
    }
    // upon project change the URI discovery needs to be reset
    if (event.getNewValue() == null || event.getNewValue() != event.getOldValue()) {
      uriDiscovery = null;
    }
  }

  private void initUriDiscovery() {
    if (uriDiscovery != null || !projectProvider.hasProject()) {
      return;
    }
    uriDiscovery = new CodeSearchUriDiscovery(projectProvider.getDestinationId());
  }

}