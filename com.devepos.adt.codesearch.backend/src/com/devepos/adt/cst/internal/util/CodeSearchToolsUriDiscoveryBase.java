package com.devepos.adt.cst.internal.util;

import java.net.URI;

import com.devepos.adt.base.util.UriDiscoveryBase;

/**
 * URI Discovery for ABAP Code Search Plugins
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public abstract class CodeSearchToolsUriDiscoveryBase extends UriDiscoveryBase {

  protected static final String DISCOVERY_RELATION_ROOT = "http://www.devepos.com/adt/relations/codesearchtools";
  protected static final String DISCOVERY_SCHEME = "http://www.devepos.com/adt/codesearchtools";
  private static final String DISCOVERY_PATH = "/devepos/adt/codesearchtools/discovery";

  private static final String DISCOVERY_TERM_PLUGIN_FEATURES = "pluginFeatures";

  protected CodeSearchToolsUriDiscoveryBase(final String destinationId,
      final String discoveryScheme) {
    super(destinationId, DISCOVERY_PATH, discoveryScheme);
  }

  /**
   * Returns URI for retrieving available ADT backend plugin features
   *
   * @return URI for retrieving available ADT backend plugin features
   */
  public URI getPluginFeaturesUri() {
    return getUriFromCollectionMember(DISCOVERY_TERM_PLUGIN_FEATURES);
  }

}
