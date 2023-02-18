package com.devepos.adt.cst.search;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import com.devepos.adt.base.IAdtUriTemplateProvider;
import com.devepos.adt.base.plugin.features.IAdtPluginFeatures;
import com.devepos.adt.base.project.IAbapProjectProvider;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScope;
import com.devepos.adt.cst.model.codesearch.ICodeSearchScopeParameters;
import com.devepos.adt.cst.model.codesearch.ICodeSearchSettings;

/**
 * Service for searching in ABAP code
 *
 * @author Ludwig Stockbauer-Muhr
 */
public interface ICodeSearchService {

  /**
   * Creates the passed search scope in the ABAP project of the given destination id
   *
   * @param destinationId   the destination Id for the target ABAP project
   * @param scopeParameters the parameters of the search scope
   * @param monitor         progress monitor of the background job
   * @return the persisted search scope
   */
  ICodeSearchScope createScope(String destinationId, ICodeSearchScopeParameters scopeParameters,
      IProgressMonitor monitor);

  /**
   * Retrieves an URI template provider for named items in the code search context
   *
   * @param projectProvider provides ABAP project
   * @return URI template provider for named items
   */
  IAdtUriTemplateProvider getNamedItemUriTemplateProvider(IAbapProjectProvider projectProvider);

  /**
   * Retrieves the list of available features for the code search scope
   *
   * @param destinationId Id of the destination of an ABAP project
   * @return list of available features in the context of the code search scope
   */
  IAdtPluginFeatures getSearchScopeFeatures(String destinationId);

  /**
   * Retrieves the list of available features to configure the code search
   *
   * @param destinationId Id of the destination of an ABAP project
   * @return list of available features in the context of the code search
   */
  IAdtPluginFeatures getSearchSettingsFeatures(String destinationId);

  /**
   * Retrieves the project specific settings for the ABAP code search
   *
   * @param destinationId Id of the destination for an ABAP project
   * @return the found settings or {@code null}
   */
  ICodeSearchSettings getSettings(String destinationId);

  /**
   * Returns {@code true} if the parameter is supported in the Code Search API
   * 
   * @param queryParameter name of a query parameter
   * @param project        the project to use
   * @return
   */
  boolean isCodeSearchParameterSupported(IProject project, String queryParameter);

  /**
   * Find matches in ABAP Code
   *
   * @param destinationId destination Id for ABAP project
   * @param uriParameters map of URI parameters
   * @param monitor       progress monitor
   * @return search results
   */
  ICodeSearchResult search(String destinationId, Map<String, Object> uriParameters,
      IProgressMonitor monitor);

  /**
   * Tests the availablity of the ABAP Code search feature in the given project
   *
   * @param project project instance to be used for verifying the feature
   *                availability
   * @return the validation status
   */
  IStatus testCodeSearchFeatureAvailability(final IProject project);

  /**
   * Updates the given code search settings in the ABAP backend specified by the
   * {@code destinationId}
   *
   * @param destinationId Id of the destination for an ABAP project
   * @param settings      code search settings object
   * @return the status of the update operation
   */
  IStatus updateSettings(String destinationId, ICodeSearchSettings settings);

  /**
   * Validates the given search patterns
   *
   * @param destinationId Id of the destination for an ABAP project
   * @param patterns      the pattern string to be validated
   * @param uriParameters map of URI parameters for the request
   * @return the status of the validation call
   */
  IStatus validatePatterns(String destinationId, String patterns,
      Map<String, String> uriParameters);
}
