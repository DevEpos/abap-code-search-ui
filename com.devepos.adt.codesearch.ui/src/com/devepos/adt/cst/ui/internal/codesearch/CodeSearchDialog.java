package com.devepos.adt.cst.ui.internal.codesearch;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PreferencesUtil;

import com.devepos.adt.base.ui.project.AbapProjectProxy;
import com.devepos.adt.base.ui.project.IAbapProjectProvider;
import com.devepos.adt.base.ui.project.ProjectInput;
import com.devepos.adt.base.ui.project.ProjectUtil;
import com.devepos.adt.base.ui.search.IChangeableSearchPage;
import com.devepos.adt.base.ui.search.ISearchFilterProvider;
import com.devepos.adt.base.ui.search.SearchFilterHandler;
import com.devepos.adt.base.ui.search.SearchPageUtil;
import com.devepos.adt.base.ui.util.StatusUtil;
import com.devepos.adt.base.ui.util.TextControlUtil;
import com.devepos.adt.base.util.StringUtil;
import com.devepos.adt.cst.search.CodeSearchFactory;
import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;
import com.devepos.adt.cst.ui.internal.preferences.CodeSearchPreferencesPage;
import com.devepos.adt.cst.ui.internal.preferences.ICodeSearchPrefs;
import com.sap.adt.util.ui.swt.AdtSWTUtilFactory;

public class CodeSearchDialog extends DialogPage implements ISearchPage,
    IChangeableSearchPage<CodeSearchQuery> {
  public static final int DEFAULT_SCALE = 2;

  public static final int MAX_SCALE = 21;
  public static final String PAGE_ID = "com.devepos.adt.codesearch.ui.searchpage.codeSearch"; //$NON-NLS-1$

  private static final String LAST_PROJECT_PREF = "codeSearch.lastProject"; //$NON-NLS-1$
  private static final int MULTIPLIER = 50;

  private Map<ValidationSource, IStatus> allValidationStatuses;
  private ISearchPageContainer container;
  private SearchFilterHandler filterHandler;

  private Composite mainComposite;
  private Composite statusArea;
  private Label maxResultsLabel;
  private Label searchStatusImageLabel;
  private Label searchStatusTextLabel;

  private Button ignoreCaseCheck;
  private Button multilineSearchOption;
  private Button singlePattern;
  private Button useRegExpCheck;
  private Button matchAllPatterns;
  private Button ignoreCommentLinesCheck;

  private Text patternsText;
  private Text objectNameInput;
  private Text filterInput;
  private Scale maxResultsScale;
  private ProjectInput projectInput;

  private IPreferenceStore prefStore;
  private IAbapProjectProvider projectProvider;
  private CodeSearchQuerySpecification querySpecs;
  private int maxResults;
  private boolean allResults;

  public CodeSearchDialog() {
    prefStore = CodeSearchUIPlugin.getDefault().getPreferenceStore();
    projectProvider = new AbapProjectProxy(null);
    querySpecs = new CodeSearchQuerySpecification();
    querySpecs.setProjectProvider(projectProvider);

    prefStore.setDefault(LAST_PROJECT_PREF, ""); //$NON-NLS-1$
    prefStore.setDefault(ICodeSearchPrefs.MAX_RESULTS, 100);

    allValidationStatuses = new HashMap<>();
    for (ValidationSource s : ValidationSource.values()) {
      allValidationStatuses.put(s, Status.OK_STATUS);
    }
  }

  private enum ValidationSource {
    FILTERS,
    PROJECT,
    SEARCH_PATTERN;
  }

  @Override
  public void createControl(final Composite parent) {
    initializeDialogUnits(parent);
    mainComposite = new Composite(parent, SWT.NONE);
    GridLayoutFactory.swtDefaults().applyTo(mainComposite);
    setControl(mainComposite);

    createPatternsGroup(mainComposite);

    // create middle composite
    Composite middle = new Composite(mainComposite, SWT.NONE);
    GridDataFactory.fillDefaults().applyTo(middle);
    GridLayoutFactory.swtDefaults().margins(0, 0).numColumns(2).applyTo(middle);

    createObjectScopeGroup(middle);
    createAdditionalSettingsGroup(middle);
    createProjectInput(mainComposite);
    createStatusArea(mainComposite);
    registerContentAssist();

    setInitialData();
    updateOKStatus();

    if (patternsText != null && !patternsText.isDisposed()) {
      patternsText.setFocus();
    }

    SearchPageUtil.notifySearchPageListeners(this);
  }

  @Override
  public boolean performAction() {
    collectQuerySpecs();
    CodeSearchQuery query = new CodeSearchQuery(querySpecs);

    query.setProjectProvider(projectProvider);
    NewSearchUI.runQueryInBackground(query);
    return true;
  }

  @Override
  public void setContainer(final ISearchPageContainer container) {
    this.container = container;
  }

  @Override
  public void setInputFromSearchQuery(final CodeSearchQuery query) {
    CodeSearchQuerySpecification querySpecs = query.getQuerySpecification();

    final IAbapProjectProvider projectProvider = querySpecs.getProjectProvider();
    final String searchTerm = querySpecs.getPatterns();
    patternsText.setText(searchTerm);
    if (projectProvider != null) {
      projectInput.setProjectName(projectProvider.getProjectName());
    }
    objectNameInput.setText(querySpecs.getObjectNames());
    filterInput.setText(querySpecs.getObjectScopeFiltersString());

    if (querySpecs.isAllResults()) {
      updateMaxResultsByScale(MAX_SCALE);
    } else {
      updateMaxResultsByScale(querySpecs.getMaxResults() / MULTIPLIER);
    }
    updateMaxResultsLabel();

    matchAllPatterns.setSelection(querySpecs.isMatchAllPatterns());
    useRegExpCheck.setSelection(querySpecs.isUseRegExp());
    singlePattern.setSelection(querySpecs.isSinglePattern());
    multilineSearchOption.setSelection(querySpecs.isMultilineSearchOption());
    ignoreCommentLinesCheck.setSelection(querySpecs.isIgnoreCommentLines());
    ignoreCaseCheck.setSelection(querySpecs.isIgnoreCaseCheck());

    if (querySpecs.isUseRegExp()) {
      singlePattern.setEnabled(false);
    }
    if (querySpecs.isSinglePattern()) {
      useRegExpCheck.setEnabled(false);
      matchAllPatterns.setEnabled(false);
      multilineSearchOption.setEnabled(false);
    }
  }

  @Override
  public void setVisible(final boolean visible) {
    super.setVisible(visible);
    updateOKStatus();
  }

  private void collectQuerySpecs() {
    querySpecs.setPatterns(patternsText.getText());
    String objectScopeFilterText = filterInput.getText();
    querySpecs.setObjectScopeFilters(filterHandler.getSearchFiltersAsStringMap(
        objectScopeFilterText, FilterName.getContentAssistToUriParamNameMap(), ","),
        objectScopeFilterText);
    querySpecs.setIgnoreCaseCheck(ignoreCaseCheck.getSelection());
    querySpecs.setIgnoreCommentLines(ignoreCommentLinesCheck.getSelection());
    querySpecs.setObjectNames(objectNameInput.getText());

    if (singlePattern.getSelection()) {
      querySpecs.setSinglePattern(true);
      querySpecs.setMultilineSearchOption(true);
      querySpecs.setMatchAllPatterns(false);
      querySpecs.setUseRegExp(false);
    } else {
      querySpecs.setSinglePattern(false);
      querySpecs.setMultilineSearchOption(multilineSearchOption.getSelection());
      querySpecs.setMatchAllPatterns(matchAllPatterns.getSelection());
      querySpecs.setUseRegExp(useRegExpCheck.getSelection());
    }
    querySpecs.setMaxResults(maxResults);
    querySpecs.setAllResults(allResults);
  }

  private void createAdditionalSettingsGroup(final Composite parent) {
    final Group group = new Group(parent, SWT.NONE);
    GridDataFactory.fillDefaults().applyTo(group);
    GridLayoutFactory.swtDefaults().numColumns(1).applyTo(group);
    group.setText("Additional Settings");

    ignoreCommentLinesCheck = new Button(group, SWT.CHECK);
    ignoreCommentLinesCheck.setText("Ignore &comment lines");

    multilineSearchOption = new Button(group, SWT.CHECK);
    multilineSearchOption.setText("M&ultiline search");
    multilineSearchOption.setToolTipText("Each source code object will be searched as a whole.\n"
        + "That way it is possible to find patterns that span multiple lines\n\n"
        + "Note: This setting should only be used together with 'Single Pattern mode' or 'Regular Expressions'");

    matchAllPatterns = new Button(group, SWT.CHECK);
    matchAllPatterns.setText("Match all");
    matchAllPatterns.setToolTipText("An object is only included in the result list if "
        + "all entered search patterns are found");

    Link preferencesLink = new Link(group, SWT.NONE);
    preferencesLink.setText("<a>-> Search preferences</a>");
    preferencesLink.setToolTipText("Open ABAP Code Search preferences");
    preferencesLink.addSelectionListener(widgetSelectedAdapter(e -> {
      PreferencesUtil.createPreferenceDialogOn(null, CodeSearchPreferencesPage.PAGE_ID,
          new String[] { CodeSearchPreferencesPage.PAGE_ID }, (Object) null).open();
    }));
  }

  private void createObjectScopeGroup(final Composite parent) {
    Group objectScopeGroup = new Group(parent, SWT.NONE);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(objectScopeGroup);
    GridLayoutFactory.swtDefaults().applyTo(objectScopeGroup);
    objectScopeGroup.setText("Scope / Limit");

    Label name = new Label(objectScopeGroup, SWT.NONE);
    name.setText("Object &Name:");
    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).applyTo(name);

    objectNameInput = new Text(objectScopeGroup, SWT.BORDER);
    AdtSWTUtilFactory.getOrCreateSWTUtil().addTextEditMenu(objectNameInput);
    GridDataFactory.fillDefaults()
        .align(SWT.FILL, SWT.CENTER)
        .grab(true, false)
        .applyTo(objectNameInput);

    Label filter = new Label(objectScopeGroup, SWT.NONE);
    filter.setText("&Filters:");
    GridDataFactory.fillDefaults()
        .indent(SWT.DEFAULT, 5)
        .align(SWT.FILL, SWT.CENTER)
        .applyTo(filter);

    filterInput = new Text(objectScopeGroup, SWT.BORDER);
    AdtSWTUtilFactory.getOrCreateSWTUtil().addTextEditMenu(filterInput);
    TextControlUtil.addWordSupport(filterInput);
    GridDataFactory.fillDefaults()
        .align(SWT.FILL, SWT.CENTER)
        .grab(true, false)
        .applyTo(filterInput);

    filterInput.addModifyListener(event -> {
      validateFilterPattern();
      updateOKStatus();
    });

    Label maxResults = new Label(objectScopeGroup, SWT.NONE);
    maxResults.setText("&Max. number of results:");
    GridDataFactory.fillDefaults()
        .indent(SWT.DEFAULT, 5)
        .align(SWT.FILL, SWT.CENTER)
        .applyTo(maxResults);

    Composite scaleComposite = new Composite(objectScopeGroup, SWT.NONE);
    GridDataFactory.fillDefaults().applyTo(scaleComposite);
    GridLayoutFactory.swtDefaults().margins(0, 0).numColumns(2).applyTo(scaleComposite);

    maxResultsScale = new Scale(scaleComposite, SWT.HORIZONTAL);
    maxResultsScale.setIncrement(1);
    maxResultsScale.setMinimum(1);
    maxResultsScale.setMaximum(MAX_SCALE);
    maxResultsScale.addSelectionListener(widgetSelectedAdapter(e -> {
      updateMaxResultsByScale(maxResultsScale.getSelection());
      updateMaxResultsLabel();
    }));
    GridDataFactory.fillDefaults()
        .align(SWT.FILL, SWT.CENTER)
        .grab(true, false)
        .applyTo(maxResultsScale);

    maxResultsLabel = new Label(scaleComposite, SWT.NONE);
    GridDataFactory.fillDefaults()
        .align(SWT.LEAD, SWT.CENTER)
        .hint(convertHorizontalDLUsToPixels(50), SWT.DEFAULT)
        .applyTo(maxResultsLabel);
  }

  private void createPatternsGroup(final Composite parent) {
    final Group patternsGroup = new Group(parent, SWT.NONE);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(patternsGroup);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(patternsGroup);
    patternsGroup.setText("Search P&attern: *");

    patternsText = new Text(patternsGroup, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
    AdtSWTUtilFactory.getOrCreateSWTUtil().addTextEditMenu(patternsText);
    GridDataFactory.fillDefaults()
        .grab(true, false)
        .hint(SWT.DEFAULT, 70)
        .align(SWT.FILL, SWT.TOP)
        .applyTo(patternsText);

    patternsText.addModifyListener(e -> {
      validateSearchPatterns();
      updateOKStatus();
    });

    // create composite for pattern options
    Composite patternOptions = new Composite(patternsGroup, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).applyTo(patternOptions);
    GridLayoutFactory.swtDefaults().applyTo(patternOptions);

    ignoreCaseCheck = new Button(patternOptions, SWT.CHECK);
    ignoreCaseCheck.setText("Ignore &Case");

    useRegExpCheck = new Button(patternOptions, SWT.CHECK);
    useRegExpCheck.setText("&Regular Expressions");
    useRegExpCheck.addSelectionListener(widgetSelectedAdapter(e -> {
      boolean useRegex = useRegExpCheck.getSelection();
      singlePattern.setEnabled(!useRegex);
      validateSearchPatterns();
      updateOKStatus();
    }));

    singlePattern = new Button(patternOptions, SWT.CHECK);
    singlePattern.setText("Single Pattern mode");
    singlePattern.setToolTipText(
        "If active the entered search pattern will be viewed as a single pattern"
            + ", otherwise each line will be viewed as a pattern");
    singlePattern.addSelectionListener(widgetSelectedAdapter(e -> {
      boolean isChecked = singlePattern.getSelection();

      multilineSearchOption.setEnabled(!isChecked);
      matchAllPatterns.setEnabled(!isChecked);
      useRegExpCheck.setEnabled(!isChecked);

      multilineSearchOption.setSelection(isChecked);
      matchAllPatterns.setSelection(false);
      matchAllPatterns.setSelection(false);
    }));
  }

  private void createProjectInput(final Composite parent) {
    projectInput = new ProjectInput(projectProvider, true);

    projectInput.createControl(parent);
    projectInput.addProjectValidator(project -> CodeSearchFactory.getCodeSearchService()
        .testTagsFeatureAvailability(project));
    projectInput.addStatusChangeListener(status -> {
      if (validateAndSetStatus(status, ValidationSource.PROJECT)) {
        validateFilterPattern();
        validateSearchPatterns();
      }
      updateOKStatus();
    });
  }

  private void createStatusArea(final Composite parent) {
    statusArea = new Composite(parent, SWT.NONE);

    GridDataFactory.swtDefaults()
        .align(SWT.FILL, SWT.FILL)
        .hint(500, SWT.DEFAULT)
        .grab(true, false)
        .applyTo(statusArea);
    GridLayoutFactory.fillDefaults().numColumns(2).applyTo(statusArea);

    searchStatusImageLabel = new Label(statusArea, SWT.NONE);
    GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER).applyTo(searchStatusImageLabel);

    searchStatusTextLabel = new Label(statusArea, SWT.NONE);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(searchStatusTextLabel);
  }

  private boolean isSearchPatternProvided() {
    if (patternsText == null || patternsText.isDisposed()) {
      return false;
    }
    String patterns = patternsText.getText();
    return patterns != null && !patterns.isEmpty();
  }

  private void registerContentAssist() {
    // set up content assist for the filters text
    ISearchFilterProvider filterProvider = new CodeSearchFilters(projectProvider);
    filterHandler = new SearchFilterHandler(filterProvider);
    filterHandler.addContentAssist(filterInput);
  }

  /*
   * Sets initial data
   */
  private void setInitialData() {
    ignoreCaseCheck.setSelection(true);
    setInitialMaxResultsScale();
    setInitialProject();
  }

  private void setInitialMaxResultsScale() {
    maxResultsScale.setSelection(DEFAULT_SCALE);
    updateMaxResultsByScale(DEFAULT_SCALE);
    updateMaxResultsLabel();
  }

  private void setInitialProject() {
    if (projectInput != null) {
      String projectName = null;

      final IProject currentAbapProject = ProjectUtil.getCurrentAbapProject();
      if (currentAbapProject != null) {
        projectName = currentAbapProject.getName();
      }
      if (projectName == null || projectName.isEmpty()) {
        projectName = prefStore.getString(LAST_PROJECT_PREF);
      }
      projectInput.setProjectName(projectName);
    }
  }

  private void setStatus(final IStatus status) {
    Display.getDefault().asyncExec(() -> {
      if (mainComposite.isDisposed() || searchStatusImageLabel == null
          || searchStatusTextLabel == null) {
        return;
      }
      if (status.getSeverity() == IStatus.OK || StringUtil.isEmpty(status.getMessage())) {
        searchStatusImageLabel.setImage(null);
        searchStatusTextLabel.setText(""); //$NON-NLS-1$
      } else {
        searchStatusImageLabel.setImage(StatusUtil.getImageForStatus(status.getSeverity()));
        searchStatusTextLabel.setText(status.getMessage());
        searchStatusTextLabel.setToolTipText(status.getMessage());
        searchStatusTextLabel.pack(true);
        searchStatusTextLabel.getParent().layout(true);
        getShell().pack(true);
      }
    });
  }

  private void updateMaxResultsByScale(final int selectedScale) {
    if (selectedScale == MAX_SCALE) {
      allResults = true;
    } else {
      allResults = false;
      maxResults = selectedScale * MULTIPLIER;
    }
  }

  private void updateMaxResultsLabel() {
    String count = allResults ? "All" : String.valueOf(maxResults);
    maxResultsLabel.setText(String.format("%s Results", count));
  }

  private void updateOKStatus() {
    Display.getDefault().asyncExec(() -> {
      if (getControl().isDisposed()) {
        return;
      }
      boolean isError = allValidationStatuses.values()
          .stream()
          .anyMatch(s -> s.getSeverity() == IStatus.ERROR);
      container.setPerformActionEnabled(!isError);
    });
  }

  private IStatus updateStatus(final IStatus status, final ValidationSource type) {
    final IStatus validatedStatus = status == null ? Status.OK_STATUS : status;
    allValidationStatuses.put(type, validatedStatus);
    return validatedStatus;
  }

  private boolean validateAndSetStatus(final IStatus status, final ValidationSource type) {
    final IStatus validatedStatus = updateStatus(status, type);
    if (validatedStatus.getSeverity() == IStatus.OK) {
      Optional<IStatus> lastErrorStatus = allValidationStatuses.entrySet()
          .stream()
          .filter(entry -> entry.getKey() != type && entry.getValue()
              .getSeverity() == IStatus.ERROR)
          .map(Entry::getValue)
          .findFirst();
      setStatus(lastErrorStatus.orElse(Status.OK_STATUS));
    } else {
      setStatus(validatedStatus);
    }
    return validatedStatus == null || validatedStatus.isOK();
  }

  private void validateFilterPattern() {
    if (filterHandler == null || filterInput == null || filterInput.isDisposed()) {
      return;
    }
    final String filterPattern = filterInput.getText();
    try {
      filterHandler.checkSearchFiltersComplete(filterPattern);
      validateAndSetStatus(new Status(IStatus.OK, CodeSearchUIPlugin.PLUGIN_ID, null, null),
          ValidationSource.FILTERS);
    } catch (final CoreException e) {
      validateAndSetStatus(new Status(IStatus.ERROR, CodeSearchUIPlugin.PLUGIN_ID, e.getMessage(),
          e), ValidationSource.FILTERS);
    }
  }

  private IStatus validateRegularExpression(final String regex) {
    return validateRegularExpression(regex, 0);
  }

  private IStatus validateRegularExpression(final String regex, final int line) {
    try {
      Pattern.compile(regex);
    } catch (PatternSyntaxException regexError) {
      if (line <= 0) {
        return new Status(IStatus.ERROR, CodeSearchUIPlugin.PLUGIN_ID, NLS.bind(
            "The pattern ''{0}'' is not a valid regular expression", regex));
      }
      return new Status(IStatus.ERROR, CodeSearchUIPlugin.PLUGIN_ID, NLS.bind(
          "The pattern ''{0}'' in line {1} is not a valid regular expression", new Object[] { regex,
              line }));

    }
    return Status.OK_STATUS;
  }

  private void validateSearchPatterns() {
    if (isSearchPatternProvided()) {
      String patterns = patternsText.getText();
      if (useRegExpCheck.getSelection()) {

        if (singlePattern.getSelection()) {
          if (!validateAndSetStatus(validateRegularExpression(patterns),
              ValidationSource.SEARCH_PATTERN)) {
            return;
          }
        } else {
          String[] tokens = patterns.split(System.lineSeparator());
          for (int i = 0; i < tokens.length; i++) {
            String pattern = tokens[i];
            if (!validateAndSetStatus(validateRegularExpression(pattern, i + 1),
                ValidationSource.SEARCH_PATTERN)) {
              return;
            }
          }
        }
      }
      // everything checked out
      validateAndSetStatus(new Status(IStatus.OK, CodeSearchUIPlugin.PLUGIN_ID, null, null),
          ValidationSource.SEARCH_PATTERN);
    } else {
      /*
       * reset the pattern validation status so the previous error status from a
       * different validation source is shown
       */
      validateAndSetStatus(Status.OK_STATUS, ValidationSource.SEARCH_PATTERN);
      updateStatus(new Status(IStatus.ERROR, CodeSearchUIPlugin.PLUGIN_ID, null, null),
          ValidationSource.SEARCH_PATTERN);
    }
  }

}
