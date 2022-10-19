package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.ArrayList;
import java.util.List;

import com.devepos.adt.base.project.IAbapProjectProvider;
import com.devepos.adt.base.ui.search.ISearchFilter;
import com.devepos.adt.base.ui.search.ISearchFilterProvider;
import com.devepos.adt.base.ui.search.contentassist.ApplicationComponentSearchFilter;
import com.devepos.adt.base.ui.search.contentassist.DateSearchFilter;
import com.devepos.adt.base.ui.search.contentassist.PackageSearchFilter;
import com.devepos.adt.base.ui.search.contentassist.UserSearchFilter;
import com.devepos.adt.cst.search.CodeSearchFactory;
import com.devepos.adt.cst.ui.internal.codesearch.contentassist.ObjectTypeSearchFilter;

/**
 * Represents all available search filters for the Code Search
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchScopeFilters implements ISearchFilterProvider {
  private static final List<ISearchFilter> EMPTY_FILTERS = new ArrayList<>();
  private List<ISearchFilter> parameters;
  private IAbapProjectProvider projectProvider;

  public CodeSearchScopeFilters(final IAbapProjectProvider projectProvider) {
    this.projectProvider = projectProvider;
  }

  @Override
  public List<ISearchFilter> getFilters() {
    if (parameters == null) {
      parameters = new ArrayList<>();
      parameters.add(new ObjectTypeSearchFilter());
      parameters.add(new UserSearchFilter(projectProvider, FilterName.OWNER
          .getContentAssistName()));
      parameters.add(new PackageSearchFilter(projectProvider));
      parameters.add(new ApplicationComponentSearchFilter(projectProvider, CodeSearchFactory
          .getCodeSearchService()
          .getNamedItemUriTemplateProvider(projectProvider), NamedItem.APPLICATION_COMPONENT));
      parameters.add(new DateSearchFilter(FilterName.CREATED_DATE.getContentAssistName()));
    }

    if (!projectProvider.ensureLoggedOn()) {
      return EMPTY_FILTERS;
    }
    return parameters;
  }
}
