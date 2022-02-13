package com.devepos.adt.cst.ui.internal.codesearch.result;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.devepos.adt.cst.ui.internal.messages.Messages;

public class CodeSearchRuntimeInfoDialog extends StatusDialog {

  private static final int REFRESH_BUTTON_ID = 50;
  private CodeSearchResult searchResult;
  private NumberFormat numberFormat;

  private Label searchedObjects;
  private Label searchedSources;
  private Label queryDuration;

  public CodeSearchRuntimeInfoDialog(final Shell shell, final CodeSearchResult searchResult) {
    super(shell);
    this.searchResult = searchResult;
    setTitle(Messages.CodeSearchRuntimeInfoDialog_dialogTitle_xtit);
    numberFormat = new DecimalFormat("###,###");
  }

  @Override
  protected void buttonPressed(final int buttonId) {
    if (buttonId == REFRESH_BUTTON_ID) {
      updateControlsFromResult();
    } else {
      super.buttonPressed(buttonId);
    }
  }

  @Override
  protected void createButtonsForButtonBar(final Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    createButton(parent, REFRESH_BUTTON_ID, Messages.CodeSearchRuntimeInfoDialog_refreshButton_xbtn,
        false);
  }

  @Override
  protected Control createDialogArea(final Composite parent) {
    Control dialogArea = super.createDialogArea(parent);

    Composite main = new Composite((Composite) dialogArea, SWT.NONE);
    GridDataFactory.fillDefaults()
        .grab(true, true)
        .hint(convertWidthInCharsToPixels(60), SWT.DEFAULT)
        .applyTo(main);
    GridLayoutFactory.swtDefaults().margins(0, 0).applyTo(main);

    createProgressGroup(main);
    createRuntimeInfoGroup(main);

    updateControlsFromResult();

    return dialogArea;
  }

  @Override
  protected boolean isResizable() {
    return true;
  }

  private void createProgressGroup(final Composite parent) {
    Group group = new Group(parent, SWT.NONE);
    group.setText(Messages.CodeSearchRuntimeInfoDialog_searchProgressGroup_xtit);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(group);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(group);

    Label searchedObjectsLabel = new Label(group, SWT.NONE);
    searchedObjectsLabel.setText(Messages.CodeSearchRuntimeInfoDialog_searchedObjects_xlbl);
    searchedObjects = new Label(group, SWT.NONE);

    Label searchedSourcesLabel = new Label(group, SWT.NONE);
    searchedSourcesLabel.setText(Messages.CodeSearchRuntimeInfoDialog_searchedSources_xlbl);
    searchedSources = new Label(group, SWT.NONE);
  }

  private void createRuntimeInfoGroup(final Composite parent) {
    Group group = new Group(parent, SWT.NONE);
    group.setText(Messages.CodeSearchRuntimeInfoDialog_runtimeInformationGroup_xtit);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(group);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(group);

    Label queryRuntimeLabel = new Label(group, SWT.NONE);
    queryRuntimeLabel.setText(Messages.CodeSearchRuntimeInfoDialog_queryRuntimeInMs_xlbl);

    queryDuration = new Label(group, SWT.NONE);
  }

  private void updateControlsFromResult() {
    float percentage = (float) searchResult.getSearchedObjectsCount() / searchResult
        .getObjectScopeCount() * 100;

    searchedObjects.setText(String.format(
        Messages.CodeSearchRuntimeInfoDialog_searchedObjectCountPattern_xtxt, numberFormat.format(
            searchResult.getSearchedObjectsCount()), numberFormat.format(searchResult
                .getObjectScopeCount()), (int) percentage));
    searchedSources.setText(numberFormat.format(searchResult.getSearchedSourcesCount()));
    queryDuration.setText(String.format(Messages.CodeSearchRuntimeInfoDialog_queryRuntimeInMs_xtxt,
        numberFormat.format(searchResult.getOverallServerTimeInMs())));

    searchedObjects.getParent().layout();
    queryDuration.getParent().layout();
  }

}
