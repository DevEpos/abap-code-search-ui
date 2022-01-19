package com.devepos.adt.cst.ui.internal.pages;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.progress.IProgressService;

import com.devepos.adt.base.destinations.DestinationUtil;
import com.devepos.adt.base.plugin.features.IAdtPluginFeatures;
import com.devepos.adt.base.ui.MessageLine;
import com.devepos.adt.base.ui.contentassist.ContentAssistSupport;
import com.devepos.adt.base.ui.project.AbapProjectProviderAccessor;
import com.devepos.adt.base.ui.project.IAbapProjectProvider;
import com.devepos.adt.base.ui.project.ProjectUtil;
import com.devepos.adt.base.ui.util.TextControlUtil;
import com.devepos.adt.cst.model.codesearch.ICodeSearchSettings;
import com.devepos.adt.cst.search.CodeSearchFactory;
import com.devepos.adt.cst.search.ICodeSearchService;
import com.devepos.adt.cst.ui.internal.codesearch.NamedItem;

/**
 * Describes project specific settings of the Code Search
 * 
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchPropertyPage extends PropertyPage implements IWorkbenchPropertyPage {

  public static final String PAGE_ID = "com.devepos.adt.codesearch.ui.pages.CodeSearchPropertyPage";
  /**
   * Identifies the feature to enable/disable the new RegEx engine which is
   * available starting with ABAP NW 7.55
   */
  public static final String PCRE_ENABLED_FEATURE = "pcreEnabled";

  private ICodeSearchService codeSearchService;
  private String destinationId;
  private boolean pageIsInvalid;
  private boolean pageIsUseable;
  private IStatus pageNotUseableStatus;
  private Button parallelEnabled;
  private Button pcreEnabled;
  private IProject project;
  private IAbapProjectProvider projectProvider;
  private IAdtPluginFeatures searchFeatures;
  private ICodeSearchSettings searchSettings;
  private Text serverGroupText;

  public CodeSearchPropertyPage() {
    codeSearchService = CodeSearchFactory.getCodeSearchService();
  }

  @Override
  public boolean performOk() {
    // backend not available
    if (!pageIsUseable) {
      return true;
    }
    boolean dirty = isPageDirty();
    if (!dirty && !pageIsInvalid) {
      return true;
    }
    if (dirty) {
      updateModelFromUi();
    } else if (pageIsInvalid) {
      return false;
    }

    updateSettings();
    return getErrorMessage() == null;
  }

  @Override
  public void setElement(final IAdaptable element) {
    super.setElement(element);
    pageIsUseable = true;

    project = getElement().<IProject>getAdapter(IProject.class);

    destinationId = DestinationUtil.getDestinationId(project);
    projectProvider = AbapProjectProviderAccessor.getProviderForDestination(destinationId);
    checkLoggedOnToProject();
    checkCodeSearchFeatureAvailability();
    determineAvailableFeatures();
    readSettings();

    if (!pageIsUseable) {
      noDefaultAndApplyButton();
    }
  }

  @Override
  protected Control createContents(final Composite parent) {
    Composite main = new Composite(parent, SWT.NONE);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(main);
    GridLayoutFactory.swtDefaults().applyTo(main);

    if (pageIsUseable) {
      createRegexSettings(main);
      createParallelSettings(main);
      updateInputFromModel();

      checkProjectDependentFeatures();
    } else {
      createErrorControl(main);
    }

    return main;
  }

  @Override
  protected void performDefaults() {
    super.performDefaults();
    setDefaultValues();
  }

  private void checkCodeSearchFeatureAvailability() {
    if (!pageIsUseable) {
      return;
    }
    IStatus status = codeSearchService.testTagsFeatureAvailability(project);
    if (!status.isOK()) {
      pageIsUseable = false;
      pageNotUseableStatus = status;
    }
  }

  private void checkLoggedOnToProject() {
    if (!pageIsUseable) {
      return;
    }
    IStatus loggedOnStatus = ProjectUtil.ensureLoggedOnToProject(project);
    if (!loggedOnStatus.isOK()) {
      pageIsUseable = false;
      pageNotUseableStatus = loggedOnStatus;
    }
  }

  private void checkProjectDependentFeatures() {
    if (!pageIsUseable) {
      return;
    }
    // check if the setting is available in the backend
    pcreEnabled.setEnabled(searchFeatures != null && searchFeatures.isFeatureEnabled(
        PCRE_ENABLED_FEATURE));
  }

  private void createErrorControl(Composite parent) {
    MessageLine error = new MessageLine(parent);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(error);
    error.setStatus(pageNotUseableStatus);
  }

  private Group createGroup(final String label, final Composite parent) {
    return createGroup(label, parent, true);
  }

  private Group createGroup(final String label, final Composite parent,
      final boolean createDefaultLayout) {
    final Group group = new Group(parent, SWT.NONE);
    if (createDefaultLayout) {
      GridLayoutFactory.swtDefaults().applyTo(group);
    }
    GridDataFactory.fillDefaults().grab(true, false).applyTo(group);
    group.setText(label);
    return group;
  }

  private void createParallelSettings(final Composite parent) {
    Group group = createGroup("Parallel processing", parent, false);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(group);

    parallelEnabled = new Button(group, SWT.CHECK);
    parallelEnabled.setText("Enable &Parallel Processing");
    parallelEnabled.addSelectionListener(widgetSelectedAdapter(e -> {
      boolean isParallelEnabled = parallelEnabled.getSelection();
      serverGroupText.setEnabled(isParallelEnabled);
    }));
    GridDataFactory.fillDefaults().span(2, 1).applyTo(parallelEnabled);

    Label serverGroupLabel = new Label(group, SWT.NONE);
    serverGroupLabel.setText("&Server group:");
    serverGroupLabel.setToolTipText(
        "The server group to be used for parallel processing (see transaction RZ12)\nIf the value is empty the default server group will be used");

    serverGroupText = new Text(group, SWT.NONE | SWT.BORDER);
    serverGroupText.setTextLimit(20);
    serverGroupText.setEnabled(false);
    GridDataFactory.fillDefaults()
        .hint(convertWidthInCharsToPixels(28), SWT.DEFAULT)
        .applyTo(serverGroupText);
    TextControlUtil.addWordSupport(serverGroupText, "\\s+");
    ContentAssistSupport.createNamedItemContentAssist(serverGroupText, projectProvider,
        codeSearchService.getNamedItemUriTemplateProvider(projectProvider), NamedItem.SERVER_GROUP,
        null);
  }

  private void createRegexSettings(final Composite parent) {
    Group group = createGroup("Settings for Regular Expression Engine", parent);

    pcreEnabled = new Button(group, SWT.CHECK);

    pcreEnabled.setText("Enable PCRE (Perl compatible regular expressions)");
    pcreEnabled.setToolTipText(
        "This advanced Regular Expression engine is only available starting with NetWeaver 7.55\n"
            + "If enabled, but not available the old engine will be used automatically");
  }

  private void determineAvailableFeatures() {
    if (!pageIsUseable) {
      return;
    }
    searchFeatures = codeSearchService.getSearchSettingsFeatures(destinationId);
  }

  private boolean isPageDirty() {
    if (searchSettings == null) {
      return false;
    }
    if (searchSettings.isParallelEnabled() != parallelEnabled.getSelection() || pcreEnabled
        .getEnabled() && searchSettings.isPcreEnabled() != pcreEnabled.getSelection()
        || !serverGroupText.getText().equals(searchSettings.getParallelServerGroup())) {
      return true;
    }
    return false;
  }

  private void readSettings() {
    if (!pageIsUseable) {
      return;
    }
    Job getSettingsJob = new Job("Retrieve Code Search Settings") {
      @Override
      protected IStatus run(final IProgressMonitor monitor) {
        searchSettings = codeSearchService.getSettings(destinationId);
        return Status.OK_STATUS;
      }

    };
    getSettingsJob.addJobChangeListener(new JobChangeAdapter() {
      @Override
      public void done(final IJobChangeEvent event) {
        Display.getDefault().asyncExec(() -> {
          Control pageControl = getControl();
          if (pageControl != null && !pageControl.isDisposed()) {
            updateInputFromModel();
          }
        });
      }
    });
    getSettingsJob.schedule();
  }

  private void setDefaultValues() {
    if (pcreEnabled == null || pcreEnabled.isDisposed()) {
      return;
    }
    pcreEnabled.setSelection(false);
    parallelEnabled.setSelection(false);
    serverGroupText.setText("");
    serverGroupText.setEnabled(false);
    setErrorMessage(null);
    pageIsInvalid = false;

    updateModelFromUi();
  }

  private void updateInputFromModel() {
    if (searchSettings != null) {
      pcreEnabled.setSelection(searchSettings.isPcreEnabled());
      parallelEnabled.setSelection(searchSettings.isParallelEnabled());
      serverGroupText.setText(searchSettings.getParallelServerGroup());
      if (parallelEnabled.getSelection()) {
        serverGroupText.setEnabled(true);
      }
    }
  }

  private void updateModelFromUi() {
    if (searchSettings != null) {
      searchSettings.setPcreEnabled(pcreEnabled.getSelection());
      searchSettings.setParallelEnabled(parallelEnabled.getSelection());
      searchSettings.setParallelServerGroup(serverGroupText.getText());
    }
  }

  private void updateSettings() {
    IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
    AtomicReference<String> errorMessage = new AtomicReference<>();

    try {
      progressService.busyCursorWhile(monitor -> {
        IStatus updateOperationStatus = codeSearchService.updateSettings(destinationId,
            searchSettings);
        if (!updateOperationStatus.isOK()) {
          pageIsInvalid = true;
          errorMessage.set(updateOperationStatus.getMessage());
        }
      });
      if (!getControl().isDisposed()) {
        setErrorMessage(errorMessage.get());
      }
    } catch (InvocationTargetException e) {
      setErrorMessage(e.getCause().getLocalizedMessage());
      e.printStackTrace();
    } catch (InterruptedException e) {
    }
  }
}
