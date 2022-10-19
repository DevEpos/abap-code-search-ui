package com.devepos.adt.cst.internal.search;

import java.net.URI;
import java.util.Map;

import com.devepos.adt.cst.internal.util.CodeSearchToolsUriDiscoveryBase;
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

  private static final String DISCOVERY_TERM_CODE_SEARCH = "codesearch";
  private static final String DISCOVERY_TERM_CODE_SEARCH_SCOPE = "codesearchScope";
  private static final String DISCOVERY_TERM_CODE_SEARCH_SETTINGS = "codesearchSettings";
  private static final String DISCOVERY_TERM_PATTERN_VALIDATOR = "patternValidator";

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
      fillTemplateWithParams(template, parameterMap);
      uri = URI.create(template.expand());
    }
    return uri;
  }

  /**
   * Retrieves the resource URI for the code search scope
   *
   * @return the resource URI for the code search scope
   */
  public URI getCodeSearchScopeUri() {
    return getUriFromCollectionMember(DISCOVERY_TERM_CODE_SEARCH_SCOPE);
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

  /**
   * Retrieves URI for Pattern Validation
   *
   * @return URI for Pattern Validation
   */
  public URI getPatternValidationUri() {
    return getUriFromCollectionMember(DISCOVERY_TERM_PATTERN_VALIDATOR);
  }

}
