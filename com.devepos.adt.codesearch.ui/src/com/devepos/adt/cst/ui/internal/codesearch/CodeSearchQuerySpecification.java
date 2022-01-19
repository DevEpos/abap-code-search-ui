package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Text;

import com.devepos.adt.base.ui.project.IAbapProjectProvider;
import com.devepos.adt.base.util.StringUtil;
import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;
import com.devepos.adt.cst.ui.internal.preferences.ClassInclude;
import com.devepos.adt.cst.ui.internal.preferences.ClassSearchScopeOption;
import com.devepos.adt.cst.ui.internal.preferences.ICodeSearchPrefs;
import com.sap.adt.destinations.model.IDestinationData;

/**
 * Describes all the options that are needed to execute a
 * {@link CodeSearchQuery}
 * 
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchQuerySpecification {

  private String destinationId;
  private IAbapProjectProvider projectProvider;

  private boolean allResults;
  private boolean ignoreCaseCheck;
  private boolean ignoreCommentLines;
  private boolean matchAllPatterns;
  private boolean multilineSearchOption;
  private boolean readPackageHierarchy;
  private boolean singlePattern;
  private boolean useRegExp;

  private String patterns;
  private String objectNames;
  private int maxResults;

  private Map<String, Object> objectScopeFilters;
  private String objectScopeFiltersString;

  public CodeSearchQuerySpecification() {
    destinationId = null;
    allResults = false;
  }

  /**
   * Builds map of necessary URI parameters for executing the object search
   * 
   * @return map of URI parameters
   */
  public Map<String, Object> buildSearchUriParameters() {
    Map<String, Object> uriParameters = new HashMap<>(getObjectScopeFilters());
    uriParameters.put(FilterName.SEARCH_PATTERN.getUriParamName(), getAdjustedPatterns());
    if (!StringUtil.isBlank(objectNames)) {
      uriParameters.put(FilterName.OBJECT_NAME.getUriParamName(), objectNames);
    }
    if (ignoreCaseCheck) {
      uriParameters.put(FilterName.IGNORE_CASE.getUriParamName(), true);
    }
    if (ignoreCommentLines) {
      uriParameters.put(FilterName.IGNORE_COMMENT_LINES.getUriParamName(), true);
    }
    if (multilineSearchOption) {
      uriParameters.put(FilterName.MULTI_LINE.getUriParamName(), true);
    }
    if (useRegExp) {
      uriParameters.put(FilterName.USE_REGEX.getUriParamName(), true);
    }
    if (matchAllPatterns) {
      uriParameters.put(FilterName.MATCH_ALL.getUriParamName(), true);
    }
    if (allResults) {
      uriParameters.put(FilterName.ALL_RESULTS.getUriParamName(), true);
    } else {
      uriParameters.put(FilterName.MAX_RESULTS.getUriParamName(), maxResults);
    }
    setParamsFromPrefStore(uriParameters);
    return uriParameters;
  }

  @Override
  public boolean equals(final Object object) {
    if (!(object instanceof CodeSearchQuerySpecification)) {
      return super.equals(object);
    }
    final CodeSearchQuerySpecification otherEntry = (CodeSearchQuerySpecification) object;
    return getQuery(false).equalsIgnoreCase(otherEntry.getQuery(false)) && getDestinationId()
        .equalsIgnoreCase(otherEntry.getDestinationId())
        && matchAllPatterns == otherEntry.matchAllPatterns;
  }

  public String getDestinationId() {
    return projectProvider != null ? projectProvider.getDestinationId()
        : destinationId != null ? destinationId : "";
  }

  public int getMaxResults() {
    return maxResults;
  }

  public String getObjectNames() {
    return objectNames;
  }

  public Map<String, Object> getObjectScopeFilters() {
    return objectScopeFilters != null ? objectScopeFilters : new HashMap<>();
  }

  public String getObjectScopeFiltersString() {
    return objectScopeFiltersString != null ? objectScopeFiltersString : "";
  }

  public String getPatterns() {
    return patterns != null ? patterns : "";
  }

  public IAbapProjectProvider getProjectProvider() {
    return projectProvider;
  }

  public String getQuery(boolean restrictString) {
//    StringBuffer query = new StringBuffer();
//    if (!StringUtil.isEmpty(objectNames) || !StringUtil.isEmpty(objectNames) || !StringUtil.isEmpty(
//        objectScopeFiltersString)) {
//      query.append("Pattern ");
//    }
//    query.append("'");
//    query.append();
//    query.append("'");
//    if (!StringUtil.isBlank(objectNames)) {
//      query.append(" names: '");
//      query.append(objectNames);
//      query.append("'");
//    }
//    if (!StringUtil.isBlank(objectScopeFiltersString)) {
//      query.append(" scope: '");
//      query.append(objectScopeFiltersString);
//      query.append("'");
//    }
//    return query.toString();
    String query = singlePattern ? patterns.replaceAll(Text.DELIMITER, "\\\\n")
        : patterns.replaceAll(Text.DELIMITER, ",");
    if (query.length() > 60) {
      query = query.substring(0, 60) + "...";
    }
    return String.format("'%s'", query);
  }

  public boolean isAllResults() {
    return allResults;
  }

  public boolean isIgnoreCaseCheck() {
    return ignoreCaseCheck;
  }

  public boolean isIgnoreCommentLines() {
    return ignoreCommentLines;
  }

  public boolean isMatchAllPatterns() {
    return matchAllPatterns;
  }

  public boolean isMultilineSearchOption() {
    return multilineSearchOption;
  }

  /**
   * @return the readPackageHierarchy
   */
  public boolean isReadPackageHierarchy() {
    return readPackageHierarchy;
  }

  public boolean isSinglePattern() {
    return singlePattern;
  }

  public boolean isUseRegExp() {
    return useRegExp;
  }

  public void setAllResults(boolean allResults) {
    this.allResults = allResults;
  }

  public void setDestinationId(final String destinationId) {
    this.destinationId = destinationId;
  }

  public void setIgnoreCaseCheck(boolean ignoreCaseCheck) {
    this.ignoreCaseCheck = ignoreCaseCheck;
  }

  public void setIgnoreCommentLines(boolean ignoreCommentLines) {
    this.ignoreCommentLines = ignoreCommentLines;
  }

  public void setMatchAllPatterns(boolean matchAllPatterns) {
    this.matchAllPatterns = matchAllPatterns;
  }

  public void setMaxResults(final int maxResults) {
    this.maxResults = maxResults;
  }

  public void setMultilineSearchOption(boolean multilineSearchOption) {
    this.multilineSearchOption = multilineSearchOption;
  }

  public void setObjectNames(String objectNames) {
    this.objectNames = objectNames.strip().replaceAll("\\s+", " ");
  }

  public void setObjectScopeFilters(final Map<String, Object> objectScopeFilters,
      final String objectScopeFiltersString) {
    this.objectScopeFilters = objectScopeFilters;
    this.objectScopeFiltersString = objectScopeFiltersString;
  }

  public void setPatterns(final String searchTerm) {
    this.patterns = searchTerm;
  }

  public void setProjectProvider(final IAbapProjectProvider projectProvider) {
    this.projectProvider = projectProvider;
  }

  /**
   * @param readPackageHierarchy the readPackageHierarchy to set
   */
  public void setReadPackageHierarchy(final boolean readPackageHierarchy) {
    this.readPackageHierarchy = readPackageHierarchy;
  }

  public void setSinglePattern(boolean singlePattern) {
    this.singlePattern = singlePattern;
  }

  public void setUseRegExp(boolean useRegExp) {
    this.useRegExp = useRegExp;
  }

  @Override
  public String toString() {
    final String destinationInfo = getDestinationInfo();
    if (destinationInfo.isEmpty()) {
      return getQuery(true);
    }
    return String.format("%s [%s]", getQuery(true), destinationInfo);
  }

  private Object getAdjustedPatterns() {
    if (singlePattern) {
      return patterns.replaceAll(Text.DELIMITER, System.lineSeparator());
    } else {
      return Stream.of(this.patterns.split(Text.DELIMITER))
          .filter(pattern -> !pattern.isBlank())
          .collect(Collectors.toList());
    }
  }

  private String getDestinationInfo() {
    if (projectProvider == null || !projectProvider.hasProject()) {
      return "";
    }
    final IDestinationData destData = projectProvider.getDestinationData();
    return String.format("%s-%s", destData.getSystemConfiguration().getSystemId(), destData
        .getClient());
  }

  private void setClassScopeParams(Map<String, Object> uriParameters, IPreferenceStore prefStore) {
    ClassSearchScopeOption scopeOption = ClassSearchScopeOption.valueOf(prefStore.getString(
        ICodeSearchPrefs.CLASS_SCOPE_OPTION));

    if (scopeOption == ClassSearchScopeOption.ALL) {
      uriParameters.put(FilterName.CLASS_SCOPE.getUriParamName(), "all");
    } else {
      List<String> scopeOptions = new ArrayList<>();
      if (prefStore.getBoolean(ICodeSearchPrefs.CLASS_SCOPE_GLOBAL_ENABLED)) {
        scopeOptions.add(ClassInclude.GLOBAL_CLASS.getApiName());
      }
      if (prefStore.getBoolean(ICodeSearchPrefs.CLASS_SCOPE_LOCAL_DEF_ENABLED)) {
        scopeOptions.add(ClassInclude.LOCAL_DEFINITIONS.getApiName());
      }
      if (prefStore.getBoolean(ICodeSearchPrefs.CLASS_SCOPE_LOCAL_IMPL_ENABLED)) {
        scopeOptions.add(ClassInclude.LOCAL_IMPLEMENTATIONS.getApiName());
      }
      if (prefStore.getBoolean(ICodeSearchPrefs.CLASS_SCOPE_MACROS_ENABLED)) {
        scopeOptions.add(ClassInclude.MACROS.getApiName());
      }
      if (prefStore.getBoolean(ICodeSearchPrefs.CLASS_SCOPE_TESTS_ENABLED)) {
        scopeOptions.add(ClassInclude.TESTS.getApiName());
      }

      uriParameters.put(FilterName.CLASS_SCOPE.getUriParamName(), String.join(",", scopeOptions));
    }
  }

  private void setParamsFromPrefStore(Map<String, Object> uriParameters) {
    IPreferenceStore prefStore = CodeSearchUIPlugin.getDefault().getPreferenceStore();
    uriParameters.put(FilterName.MAX_OBJECTS.getUriParamName(), prefStore.getInt(
        ICodeSearchPrefs.MAX_OBJECTS));
    setClassScopeParams(uriParameters, prefStore);
  }
}
