package com.devepos.adt.cst.ui.internal.codesearch.result;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.devepos.adt.cst.ui.internal.codesearch.result.CodeSearchRuntimeInformation.IRuntimeInfoListener;
import com.devepos.adt.cst.ui.internal.messages.Messages;

/**
 * Dialog to display runtime information a code search query
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchRuntimeInfoDialog extends StatusDialog implements IRuntimeInfoListener {

  private static final int UPDATE_BUTTON_ID = 50;
  private final CodeSearchRuntimeInformation runtimeInfo;
  private final NumberFormat numberFormat;

  private Label searchedObjects;
  private Label searchedSources;
  private Label searchedLoC;
  private Label foundMatches;
  private Label queryDuration;
  private Label averageRequestDuration;
  private Label queryStatus;
  private Button updateButton;
  private boolean isUpdatesOn;

  public CodeSearchRuntimeInfoDialog(final Shell shell,
      final CodeSearchRuntimeInformation runtimeInfo) {
    super(shell);
    this.runtimeInfo = runtimeInfo;
    setTitle(Messages.CodeSearchRuntimeInfoDialog_dialogTitle_xtit + " [" + runtimeInfo //$NON-NLS-1$
        .getSystemId() + "]"); //$NON-NLS-1$
    numberFormat = new DecimalFormat("###,###"); //$NON-NLS-1$

    if (runtimeInfo.isSearchRunning()) {
      runtimeInfo.addRuntimeInfoListener(this);
      runtimeInfo.startQueryListening();
    }
  }

  @Override
  public boolean close() {
    if (runtimeInfo != null) {
      runtimeInfo.removeRuntimeInfoListener(this);
      runtimeInfo.stopQueryListening();
    }
    return super.close();
  }

  @Override
  public void queryFinished() {
    Display.getDefault().asyncExec(() -> {
      updateControlsFromResult();
      if (updateButton != null) {
        updateButton.setEnabled(false);
      }
      queryStatus.setText(Messages.CodeSearchRuntimeInfoDialog_queryStatusFinished_xlbl);
    });
  }

  @Override
  public void updated() {
    if (!isUpdatesOn) {
      return;
    }
    Display.getDefault().asyncExec(() -> {
      updateControlsFromResult();
    });
  }

  @Override
  protected void buttonPressed(final int buttonId) {
    if (buttonId == UPDATE_BUTTON_ID) {
      if (isUpdatesOn) {
        updateButton.setText(Messages.CodeSearchRuntimeInfoDialog_startUpdatesAction_xbtn);
      } else {
        updateButton.setText(Messages.CodeSearchRuntimeInfoDialog_stopUpdatesAction_xbtn);
        updateControlsFromResult();
      }
      isUpdatesOn = !isUpdatesOn;
    } else {
      super.buttonPressed(buttonId);
    }
  }

  @Override
  protected void createButtonsForButtonBar(final Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    if (runtimeInfo.isSearchRunning()) {
      isUpdatesOn = true;
      updateButton = createButton(parent, UPDATE_BUTTON_ID,
          Messages.CodeSearchRuntimeInfoDialog_stopUpdatesAction_xbtn, false);
    }
  }

  @Override
  protected Control createDialogArea(final Composite parent) {
    Control dialogArea = super.createDialogArea(parent);

    Composite main = new Composite((Composite) dialogArea, SWT.NONE);
    GridDataFactory.fillDefaults()
        .grab(true, true)
        .hint(convertWidthInCharsToPixels(65), SWT.DEFAULT)
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

    Label queryStatusLabel = new Label(group, SWT.NONE);
    queryStatusLabel.setText(Messages.CodeSearchRuntimeInfoDialog_queryStatusLabel_xlbl);
    queryStatus = new Label(group, SWT.NONE);
    if (runtimeInfo.isSearchRunning()) {
      queryStatus.setText(Messages.CodeSearchRuntimeInfoDialog_queryStatusRunning_xlbl);
    } else {
      queryStatus.setText(runtimeInfo.isAllSearched()
          ? Messages.CodeSearchRuntimeInfoDialog_queryStatusFinished_xlbl
          : Messages.CodeSearchRuntimeInfoDialog_queryStatusCancelled_xlbl);
    }

    Label searchedObjectsLabel = new Label(group, SWT.NONE);
    searchedObjectsLabel.setText(Messages.CodeSearchRuntimeInfoDialog_searchedObjects_xlbl);
    searchedObjects = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(searchedObjects);

    Label searchedSourcesLabel = new Label(group, SWT.NONE);
    searchedSourcesLabel.setText(Messages.CodeSearchRuntimeInfoDialog_searchedSources_xlbl);
    searchedSources = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(searchedSources);

    Label searchedSourceLoCLabel = new Label(group, SWT.NONE);
    searchedSourceLoCLabel.setText(Messages.CodeSearchRuntimeInfoDialog_linesOfCode_xlbl);
    searchedLoC = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(searchedLoC);

    Label foundMatchesLabel = new Label(group, SWT.NONE);
    foundMatchesLabel.setText(Messages.CodeSearchRuntimeInfoDialog_foundMatchesLabel_xlbl);
    foundMatches = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(foundMatches);
  }

  private void createRuntimeInfoGroup(final Composite parent) {
    Group group = new Group(parent, SWT.NONE);
    group.setText(Messages.CodeSearchRuntimeInfoDialog_runtimeInformationGroup_xtit);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(group);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(group);

    Label queryRuntimeLabel = new Label(group, SWT.NONE);
    queryRuntimeLabel.setText(Messages.CodeSearchRuntimeInfoDialog_queryRuntimeInMs_xlbl);
    queryDuration = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(queryDuration);

    Label averageRuntimLabel = new Label(group, SWT.NONE);
    averageRuntimLabel.setText(
        Messages.CodeSearchRuntimeInfoDialog_averageRequestRuntimeLabel_xlbl);
    averageRequestDuration = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(averageRequestDuration);
  }

  private void updateControlsFromResult() {
    float percentage = (float) runtimeInfo.getSearchedObjectsCount() / runtimeInfo
        .getObjectScopeCount() * 100;

    searchedObjects.setText(String.format(
        Messages.CodeSearchRuntimeInfoDialog_searchedObjectCountPattern_xtxt, numberFormat.format(
            runtimeInfo.getSearchedObjectsCount()), numberFormat.format(runtimeInfo
                .getObjectScopeCount()), (int) percentage));

    searchedSources.setText(numberFormat.format(runtimeInfo.getSearchedSourcesCount()));
    searchedLoC.setText(numberFormat.format(runtimeInfo.getSearchedLinesOfCode()));
    foundMatches.setText(numberFormat.format(runtimeInfo.getResultCount()));

    queryDuration.setText(String.format(Messages.CodeSearchRuntimeInfoDialog_queryRuntimeInMs_xtxt,
        numberFormat.format(runtimeInfo.getOverallServerTimeInMs())));
    averageRequestDuration.setText(String.format(
        Messages.CodeSearchRuntimeInfoDialog_queryRuntimeInMs_xtxt, numberFormat.format(runtimeInfo
            .getAverageDuration())));

    searchedObjects.getParent().layout();
    queryDuration.getParent().layout();
  }

}
