package com.devepos.adt.cst.internal.search;

import java.net.URI;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

import com.devepos.adt.base.IAdtUriTemplateProvider;
import com.devepos.adt.base.destinations.DestinationUtil;
import com.devepos.adt.base.model.adtbase.IAdtPluginFeatureList;
import com.devepos.adt.base.plugin.features.AdtPluginFeaturesServiceFactory;
import com.devepos.adt.base.plugin.features.IAdtPluginFeatures;
import com.devepos.adt.base.ui.project.IAbapProjectProvider;
import com.devepos.adt.cst.internal.CodeSearchPlugin;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.devepos.adt.cst.model.codesearch.ICodeSearchSettings;
import com.devepos.adt.cst.search.ICodeSearchService;
import com.sap.adt.communication.resources.AdtRestResourceFactory;
import com.sap.adt.communication.resources.IRestResource;
import com.sap.adt.communication.resources.ResourceException;
import com.sap.adt.communication.session.AdtSystemSessionFactory;
import com.sap.adt.communication.session.ISystemSession;

/**
 * Standard implementation of the interface {@link ICodeSearchService}
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchSearchService implements ICodeSearchService {

  @Override
  public IAdtUriTemplateProvider getNamedItemUriTemplateProvider(
      final IAbapProjectProvider projectProvider) {
    if (projectProvider == null) {
      throw new IllegalArgumentException("Parameter 'projectProvider' must be filled!");
    }
    return new NamedItemUriTemplateProvider(projectProvider);
  }

  @Override
  public IAdtPluginFeatures getSearchSettingsFeatures(final String destinationId) {
    CodeSearchUriDiscovery uriDiscovery = new CodeSearchUriDiscovery(destinationId);
    URI pluginFeaturesUri = uriDiscovery.getPluginFeaturesUri();
    if (pluginFeaturesUri == null) {
      return null;
    }
    URI settingsUri = uriDiscovery.getCodeSearchSettingsUri();
    if (settingsUri == null) {
      return null;
    }

    IAdtPluginFeatureList featureList = AdtPluginFeaturesServiceFactory.createService()
        .getFeatures(destinationId, pluginFeaturesUri.toString());

    return featureList != null ? featureList.getFeaturesByEndpoint(settingsUri.toString()) : null;
  }

  @Override
  public ICodeSearchSettings getSettings(final String destinationId) {
    CodeSearchUriDiscovery uriDiscovery = new CodeSearchUriDiscovery(destinationId);
    URI settingsUri = uriDiscovery.getCodeSearchSettingsUri();
    if (settingsUri == null) {
      return null;
    }
    final ISystemSession session = AdtSystemSessionFactory.createSystemSessionFactory()
        .createStatelessSession(destinationId);

    final IRestResource restResource = AdtRestResourceFactory.createRestResourceFactory()
        .createRestResource(settingsUri, session);
    restResource.addContentHandler(new CodeSearchSettingsContentHandler());
    try {
      return restResource.get(new NullProgressMonitor(), ICodeSearchSettings.class);
    } catch (final ResourceException exc) {
      exc.printStackTrace();
    }
    return null;
  }

  @Override
  public ICodeSearchResult search(final String destinationId,
      final Map<String, Object> uriParameters, final IProgressMonitor monitor) {

    CodeSearchUriDiscovery discovery = new CodeSearchUriDiscovery(destinationId);
    URI resourceUri = discovery.createCodeSearchUriFromTemplate(uriParameters);
    if (resourceUri != null) {
      final ISystemSession session = AdtSystemSessionFactory.createSystemSessionFactory()
          .createStatelessSession(destinationId);

      final IRestResource restResource = AdtRestResourceFactory.createRestResourceFactory()
          .createRestResource(resourceUri, session);
      restResource.addContentHandler(new CodeSearchResultContentHandler());
      return restResource.get(monitor, ICodeSearchResult.class);
    }
    return null;
  }

  @Override
  public IStatus testTagsFeatureAvailability(final IProject project) {
    final String destinationId = DestinationUtil.getDestinationId(project);
    var uriDiscovery = new CodeSearchUriDiscovery(destinationId);
    if (uriDiscovery.isResourceDiscoverySuccessful() && uriDiscovery.getCodeSearchUri() != null) {
      return Status.OK_STATUS;
    }
    return new Status(IStatus.ERROR, CodeSearchPlugin.PLUGIN_ID, NLS.bind(
        "ABAP Code Search is not available in project {0}", project.getName()));
  }

  @Override
  public IStatus updateSettings(final String destinationId, final ICodeSearchSettings settings) {
    if (settings == null) {
      throw new IllegalArgumentException("Parameter 'settings' must not be null");
    }

    CodeSearchUriDiscovery uriDiscovery = new CodeSearchUriDiscovery(destinationId);
    URI settingsUri = uriDiscovery.getCodeSearchSettingsUri();
    if (settingsUri == null) {
      return null;
    }
    final ISystemSession session = AdtSystemSessionFactory.createSystemSessionFactory()
        .createStatelessSession(destinationId);

    final IRestResource restResource = AdtRestResourceFactory.createRestResourceFactory()
        .createRestResource(settingsUri, session);
    restResource.addContentHandler(new CodeSearchSettingsContentHandler());
    try {
      restResource.put(new NullProgressMonitor(), ICodeSearchSettings.class, settings);
      return Status.OK_STATUS;
    } catch (final ResourceException exc) {
      exc.printStackTrace();
      return new Status(IStatus.ERROR, CodeSearchPlugin.PLUGIN_ID, exc.getMessage());
    }
  }
}
