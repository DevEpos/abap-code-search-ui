package com.devepos.adt.cst.ui.internal.export;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.devepos.adt.base.destinations.DestinationUtil;
import com.devepos.adt.base.ui.tree.ICollectionTreeNode;
import com.devepos.adt.base.util.StringUtil;
import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;
import com.devepos.adt.cst.ui.internal.help.HelpContexts;
import com.devepos.adt.cst.ui.internal.help.HelpUtil;
import com.devepos.adt.cst.ui.internal.messages.Messages;
import com.devepos.adt.cst.ui.internal.preferences.ICodeSearchPrefs;
import com.sap.adt.util.ui.swt.AdtSWTUtilFactory;

/**
 * Dialog to export Code Search Results to file
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class ExportSearchResultsDialog extends TitleAreaDialog {
  private static final String DIALOG_SETTINGS_NAME = ExportSearchResultsDialog.class
      .getCanonicalName();
  private Composite mainComposite;
  private Button commaDelimiter;
  private Button semicolonDelimiter;
  private Button tabDelimiter;
  private Combo qualifierCombo;
  private Button generateColHeaders;
  private Button useQualifierWhenRequired;
  private Text fileInput;
  private Text matchMarker;
  private Button useMatchMarker;

  private boolean fileExists;
  private final ICollectionTreeNode rootResultNode;
  private final IProject project;
  private final IPreferenceStore prefStore;

  public ExportSearchResultsDialog(final Shell shell, final ICollectionTreeNode rootResultNode,
      final IProject project) {
    super(shell);
    setShellStyle(getShellStyle() | SWT.RESIZE);
    setHelpAvailable(false);
    this.rootResultNode = rootResultNode;
    this.project = project;
    prefStore = CodeSearchUIPlugin.getDefault().getPreferenceStore();
    setHelpAvailable(true);
  }

  @Override
  protected void cancelPressed() {
    super.cancelPressed();
  }

  @Override
  protected void configureShell(final Shell shell) {
    super.configureShell(shell);
    shell.setText(Messages.ExportSearchResultsDialog_Title_xtit);
  }

  @Override
  protected Control createButtonBar(final Composite parent) {
    final Control buttonBar = super.createButtonBar(parent);
    enableOkButton(false);
    setMessage(Messages.ExportSearchResultsDialog_SelectFileForExportPrompt_xmsg,
        IMessageProvider.INFORMATION);
    return buttonBar;
  }

  @Override
  protected Control createDialogArea(final Composite parent) {
    setTitle(Messages.ExportSearchResultsDialog_Title_xtit);
    final var dialogArea = (Composite) super.createDialogArea(parent);
    mainComposite = new Composite(dialogArea, SWT.NONE);
    GridLayoutFactory.swtDefaults()
        .extendedMargins(5, 5, 5, 5)
        .numColumns(2)
        .applyTo(mainComposite);
    GridDataFactory.fillDefaults().hint(450, 250).grab(true, true).applyTo(mainComposite);

    HelpUtil.setHelp(dialogArea, HelpContexts.CODE_SEARCH_EXPORT_DIALOG);

    createFileChooserSection();
    createDelimiterOptions();
    createOtherOptions();

    selectDelimiterFromPrefs();

    return dialogArea;
  }

  @Override
  protected IDialogSettings getDialogBoundsSettings() {
    return CodeSearchUIPlugin.getDefault().getDialogSettingsSection(DIALOG_SETTINGS_NAME);
  }

  @Override
  protected int getDialogBoundsStrategy() {
    return DIALOG_PERSISTSIZE;
  }

  @Override
  protected void okPressed() {
    var exporter = new ResultExporter(DestinationUtil.getSystemId(DestinationUtil.getDestinationId(
        project)), rootResultNode, getDelimiter(), generateColHeaders.getSelection(), fileInput
            .getText(), qualifierCombo.getText(), useQualifierWhenRequired.getSelection(),
        useMatchMarker.getSelection() ? matchMarker.getText() : null);
    try {
      exporter.run();
    } catch (IOException e) {
      MessageDialog.openError(getShell(),
          Messages.ExportSearchResultsDialog_FileOperationError_xtit, e.getLocalizedMessage());
      return;
    }
    updateDialogPrefs();
    super.okPressed();
  }

  private void createDelimiterOptions() {
    var group = new Group(mainComposite, SWT.NONE);
    GridLayoutFactory.swtDefaults().applyTo(group);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(group);

    group.setText(Messages.ExportSearchResultsDialog_DelimterOptions_xgrp);

    commaDelimiter = new Button(group, SWT.RADIO);
    commaDelimiter.setText(Messages.ExportSearchResultsDialog_CommaDelimiter_xbtn);
    GridDataFactory.fillDefaults().applyTo(commaDelimiter);
    commaDelimiter.setSelection(true);

    semicolonDelimiter = new Button(group, SWT.RADIO);
    semicolonDelimiter.setText(Messages.ExportSearchResultsDialog_SemicolonDelimiter_xbtn);
    GridDataFactory.fillDefaults().applyTo(semicolonDelimiter);

    tabDelimiter = new Button(group, SWT.RADIO);
    tabDelimiter.setText(Messages.ExportSearchResultsDialog_TabDelimiterF_xbtn);
    GridDataFactory.fillDefaults().applyTo(tabDelimiter);
  }

  private void createFileChooserSection() {
    var composite = new Composite(mainComposite, SWT.NONE);
    GridLayoutFactory.swtDefaults().margins(0, 0).numColumns(3).applyTo(composite);
    GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(composite);

    var label = new Label(composite, SWT.NONE);
    label.setText(Messages.ExportSearchResultsDialog_FileName_xlbl);
    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).applyTo(label);

    fileInput = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(fileInput);

    var browseButton = new Button(composite, SWT.PUSH);
    browseButton.setText(Messages.ExportSearchResultsDialog_FileBrowse_xbtn);
    browseButton.addSelectionListener(widgetSelectedAdapter(l -> {
      var fileDialog = new FileDialog(getShell(), SWT.SAVE);
      fileDialog.setFilterExtensions(new String[] { "*.txt", "*.csv", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      fileDialog.setFileName("codeSearchResult"); //$NON-NLS-1$
      var fileName = fileDialog.open();
      if (fileName != null) {
        fileInput.setText(fileName);
        var file = new File(fileName);
        fileExists = file.exists();
      } else {
        fileExists = false;
        fileInput.setText(""); //$NON-NLS-1$
      }
      validateInput();
    }));
    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).applyTo(browseButton);
    AdtSWTUtilFactory.getOrCreateSWTUtil().setButtonWidthHint(browseButton);
  }

  private void createOtherOptions() {
    var group = new Group(mainComposite, SWT.NONE);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(group);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(group);

    group.setText(Messages.ExportSearchResultsDialog_Options_xgrp);

    generateColHeaders = new Button(group, SWT.CHECK);
    generateColHeaders.setSelection(true);
    generateColHeaders.setText(Messages.ExportSearchResultsDialog_GenerateColHeadersOption_xchk);
    GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(generateColHeaders);

    useQualifierWhenRequired = new Button(group, SWT.CHECK);
    useQualifierWhenRequired.setText(
        Messages.ExportSearchResultsDialog_UseQualifierOnlyWhenRequiredOption_xchk);
    useQualifierWhenRequired.setSelection(true);
    GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(useQualifierWhenRequired);

    var qualifierLabel = new Label(group, SWT.NONE);
    qualifierLabel.setText(Messages.ExportSearchResultsDialog_QualifierOptions_xgrp);
    GridDataFactory.fillDefaults()
        .align(SWT.BEGINNING, SWT.CENTER)
        .indent(20, SWT.DEFAULT)
        .applyTo(qualifierLabel);

    qualifierCombo = new Combo(group, SWT.BORDER | SWT.DROP_DOWN | SWT.READ_ONLY);
    GridDataFactory.fillDefaults()
        .hint(30, SWT.DEFAULT)
        .align(SWT.BEGINNING, SWT.CENTER)
        .grab(true, false)
        .applyTo(qualifierCombo);
    qualifierCombo.setVisibleItemCount(2);

    qualifierCombo.add("\""); //$NON-NLS-1$
    qualifierCombo.add("'"); //$NON-NLS-1$
    qualifierCombo.select(0);

    useMatchMarker = new Button(group, SWT.CHECK);
    useMatchMarker.setText(Messages.ExportSearchResultsDialog_UseMarkerSequenceOption_xchk);
    useMatchMarker.setSelection(prefStore.getBoolean(ICodeSearchPrefs.CSV_EXPORT_USE_MATCH_MARKER));
    useMatchMarker.addSelectionListener(widgetSelectedAdapter(l -> {
      matchMarker.setEnabled(useMatchMarker.getSelection());
      validateInput();
    }));
    GridDataFactory.fillDefaults().span(2, 1).indent(0, 10).applyTo(useMatchMarker);

    var matchMarkerLabel = new Label(group, SWT.NONE);
    matchMarkerLabel.setText(Messages.ExportSearchResultsDialog_MarkerSequence_xlbl);
    GridDataFactory.fillDefaults().indent(20, 0).applyTo(matchMarkerLabel);

    matchMarker = new Text(group, SWT.BORDER);
    GridDataFactory.fillDefaults().hint(30, SWT.DEFAULT).applyTo(matchMarker);
    matchMarker.setText(prefStore.getString(ICodeSearchPrefs.CSV_EXPORT_MATCH_MARKER_SEQUENCE));
    matchMarker.addModifyListener(l -> {
      validateInput();
    });
    matchMarker.setEnabled(false);
  }

  private void enableOkButton(final boolean enable) {
    final var okButton = getButton(IDialogConstants.OK_ID);
    if (okButton != null && !okButton.isDisposed()) {
      okButton.setEnabled(enable);
    }
  }

  private String getDelimiter() {
    if (commaDelimiter.getSelection()) {
      return ResultExporter.COMMA_DELIMITER;
    }
    if (semicolonDelimiter.getSelection()) {
      return ResultExporter.SEMICOLON_DELIMITER;
    }
    if (tabDelimiter.getSelection()) {
      return ResultExporter.TAB_DELIMITER;
    }
    return ResultExporter.COMMA_DELIMITER;
  }

  private void selectDelimiterFromPrefs() {
    var delimiterInPrefs = prefStore.getString(ICodeSearchPrefs.CSV_EXPORT_DELIMITER);
    switch (delimiterInPrefs) {
    case ResultExporter.COMMA_DELIMITER:
      commaDelimiter.setSelection(true);
      semicolonDelimiter.setSelection(false);
      tabDelimiter.setSelection(false);
      break;
    case ResultExporter.SEMICOLON_DELIMITER:
      semicolonDelimiter.setSelection(true);
      commaDelimiter.setSelection(false);
      tabDelimiter.setSelection(false);
      break;
    case ResultExporter.TAB_DELIMITER:
      tabDelimiter.setSelection(true);
      semicolonDelimiter.setSelection(false);
      commaDelimiter.setSelection(false);
      break;
    }
  }

  private void updateDialogPrefs() {
    if (useMatchMarker.getSelection()) {
      prefStore.setValue(ICodeSearchPrefs.CSV_EXPORT_MATCH_MARKER_SEQUENCE, matchMarker.getText());
    }
    prefStore.setValue(ICodeSearchPrefs.CSV_EXPORT_DELIMITER, getDelimiter());
    prefStore.setValue(ICodeSearchPrefs.CSV_EXPORT_USE_MATCH_MARKER, useMatchMarker.getSelection());
  }

  private void validateInput() {
    boolean enableOkButton = true;

    setMessage(null);
    setErrorMessage(null);

    if (!StringUtil.isEmpty(fileInput.getText())) {
      boolean checkFileStatus = false;
      if (useMatchMarker.getSelection()) {
        if (StringUtil.isBlank(matchMarker.getText())) {
          setErrorMessage(Messages.ExportSearchResultsDialog_MarkerSequenceOption_xtxt);
          enableOkButton = false;
        } else {
          checkFileStatus = true;
        }
      } else {
        checkFileStatus = true;
      }
      if (checkFileStatus && fileExists) {
        setMessage(Messages.ExportSearchResultsDialog_FileWillBeOverwritten_xmsg,
            IMessageProvider.WARNING);
      }
    } else {
      enableOkButton = false;
      setMessage(Messages.ExportSearchResultsDialog_SelectFileForExportPrompt_xmsg,
          IMessageProvider.INFORMATION);
    }

    enableOkButton(enableOkButton);
  }
}
