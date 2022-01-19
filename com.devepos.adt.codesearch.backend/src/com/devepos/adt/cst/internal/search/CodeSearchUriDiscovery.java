package com.devepos.adt.cst.internal.search;

import java.net.URI;
import java.util.Map;

import com.devepos.adt.cst.internal.util.CodeSearchToolsUriDiscoveryBase;
import com.sap.adt.compatibility.uritemplate.AdtUriTemplateFactory;
import com.sap.adt.compatibility.uritemplate.IAdtUriTemplate;

/**
 * URI Discovery for code search
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchUriDiscovery extends CodeSearchToolsUriDiscoveryBase {

  private static final String DISCOVERY_RELATION_CODE_SEARCH = DISCOVERY_RELATION_ROOT
      + "/codesearch"; //$NON-NLS-1$

  private static final String DISCOVERY_TERM_CODE_SEARCH = "codesearch"; //$NON-NLS-1$
  private static final String DISCOVERY_TERM_CODE_SEARCH_SETTINGS = "codesearchSettings"; //$NON-NLS-1$
  private static final String NAMED_ITEM_TEMPLATE = "{?maxItemCount,name,description,data}"; //$NON-NLS-1$

  public CodeSearchUriDiscovery(final String destinationId) {
    super(destinationId, DISCOVERY_SCHEME);
  }

  /**
   * Creates a valid REST resource URI for the Code Search from the given map of
   * parameter values
   *
   * @param parameterMap map of parameter key and their corresponding values
   * @return REST resource URI for Code Search
   */
  public URI createCodeSearchUriFromTemplate(final Map<String, Object> parameterMap) {
    final IAdtUriTemplate template = getCodeSearchTemplate();
    URI uri = null;
    if (template != null) {
      for (final String paramKey : parameterMap.keySet()) {
        if (template.containsVariable(paramKey)) {
          final Object paramValue = parameterMap.get(paramKey);
          if (paramValue != null) {
            template.set(paramKey, paramValue);
          }
        }
      }
      uri = URI.create(template.expand());
    }
    return uri;
  }

  /**
   * Retrieves URI for settings of the ABAP Code Search
   *
   * @return URI for settings of the ABAP Code Search
   */
  public URI getCodeSearchSettingsUri() {
    return getUriFromCollectionMember(DISCOVERY_TERM_CODE_SEARCH_SETTINGS);
  }

  /**
   * Retrieve URI template for the code search
   *
   * @return
   */
  public IAdtUriTemplate getCodeSearchTemplate() {
    return getTemplate(DISCOVERY_TERM_CODE_SEARCH, DISCOVERY_RELATION_CODE_SEARCH);
  }

  /**
   * Retrieves Resource URI for the code search
   *
   * @return
   */
  public URI getCodeSearchUri() {
    return getUriFromCollectionMember(DISCOVERY_TERM_CODE_SEARCH);
  }

  public IAdtUriTemplate getNamedItemTemplate(final String discoveryTerm) {
    final URI uri = getUriFromCollectionMember(discoveryTerm);
    return uri != null ? getNamedItemTemplateForUri(uri) : null;
  }

  private IAdtUriTemplate getNamedItemTemplateForUri(final URI uri) {
    IAdtUriTemplate uriTemplate = null;
    if (uri != null) {
      uriTemplate = AdtUriTemplateFactory.createUriTemplate(uri.toString() + NAMED_ITEM_TEMPLATE);
    }
    return uriTemplate;
  }
}
