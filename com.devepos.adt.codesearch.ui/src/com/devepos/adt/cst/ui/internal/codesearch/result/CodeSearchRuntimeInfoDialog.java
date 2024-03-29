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
  private static final String UNIT_HOUR = "h"; //$NON-NLS-1$
  private static final String UNIT_MINUTE = "m"; //$NON-NLS-1$
  private static final String UNIT_SECOND = "s"; //$NON-NLS-1$

  private static final String UNIT_MILLISECOND = "ms"; //$NON-NLS-1$
  private static final float HOUR_DENOMINATOR = 3600000f;
  private static final float MINUTE_DENOMINATOR = 60000f;
  private static final float SECONDS_DENOMINATOR = 1000f;

  private final CodeSearchRuntimeInformation runtimeInfo;
  private final NumberFormat defaultFormat;
  private final NumberFormat formatWithDecimals;

  private Label searchedObjects;
  private Label searchedSources;
  private Label searchedLoC;
  private Label foundMatches;
  private Label queryDuration;
  private Label queryDurationUnit;
  private Label clientQueryDuration;
  private Label clientQueryDurationUnit;
  private Label averageRequestDuration;
  private Label averageRequestDurationUnit;
  private Label queryStatus;
  private Button updateButton;
  private boolean isUpdatesOn;

  public CodeSearchRuntimeInfoDialog(final Shell shell,
      final CodeSearchRuntimeInformation runtimeInfo) {
    super(shell);
    this.runtimeInfo = runtimeInfo;
    setTitle(Messages.CodeSearchRuntimeInfoDialog_dialogTitle_xtit + " [" + runtimeInfo //$NON-NLS-1$
        .getSystemId() + "]"); //$NON-NLS-1$
    defaultFormat = new DecimalFormat("###,###"); //$NON-NLS-1$
    formatWithDecimals = new DecimalFormat("###,###.00"); //$NON-NLS-1$

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

    var main = new Composite((Composite) dialogArea, SWT.NONE);
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

  private Label createDurationOutput(final Composite parent, final String label) {
    var durationLabel = new Label(parent, SWT.NONE);
    durationLabel.setText(label);

    var duration = new Label(parent, SWT.NONE);
    GridDataFactory.fillDefaults()
        .align(SWT.END, SWT.FILL)
        .indent(10, SWT.DEFAULT)
        .applyTo(duration);
    return duration;
  }

  private Label createDurationUnitOutput(final Composite parent) {
    var durationUnit = new Label(parent, SWT.NONE);
    GridDataFactory.fillDefaults()
        .align(SWT.BEGINNING, SWT.FILL)
        .indent(1, SWT.DEFAULT)
        .applyTo(durationUnit);

    // set default unit
    durationUnit.setText(UNIT_MILLISECOND);
    return durationUnit;
  }

  private void createProgressGroup(final Composite parent) {
    var group = new Group(parent, SWT.NONE);
    group.setText(Messages.CodeSearchRuntimeInfoDialog_searchProgressGroup_xtit);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(group);
    GridLayoutFactory.swtDefaults().numColumns(2).applyTo(group);

    var queryStatusLabel = new Label(group, SWT.NONE);
    queryStatusLabel.setText(Messages.CodeSearchRuntimeInfoDialog_queryStatusLabel_xlbl);
    queryStatus = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(queryStatus);

    if (runtimeInfo.isSearchRunning()) {
      queryStatus.setText(Messages.CodeSearchRuntimeInfoDialog_queryStatusRunning_xlbl);
    } else {
      queryStatus.setText(runtimeInfo.isQueryFinished()
          ? Messages.CodeSearchRuntimeInfoDialog_queryStatusFinished_xlbl
          : Messages.CodeSearchRuntimeInfoDialog_queryStatusCancelled_xlbl);
    }

    var searchedObjectsLabel = new Label(group, SWT.NONE);
    searchedObjectsLabel.setText(Messages.CodeSearchRuntimeInfoDialog_searchedObjects_xlbl);
    searchedObjects = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(searchedObjects);

    var searchedSourcesLabel = new Label(group, SWT.NONE);
    searchedSourcesLabel.setText(Messages.CodeSearchRuntimeInfoDialog_searchedSources_xlbl);
    searchedSources = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(searchedSources);

    var searchedSourceLoCLabel = new Label(group, SWT.NONE);
    searchedSourceLoCLabel.setText(Messages.CodeSearchRuntimeInfoDialog_linesOfCode_xlbl);
    searchedLoC = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(searchedLoC);

    var foundMatchesLabel = new Label(group, SWT.NONE);
    foundMatchesLabel.setText(Messages.CodeSearchRuntimeInfoDialog_foundMatchesLabel_xlbl);
    foundMatches = new Label(group, SWT.NONE);
    GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).applyTo(foundMatches);
  }

  private void createRuntimeInfoGroup(final Composite parent) {
    var group = new Group(parent, SWT.NONE);
    group.setText(Messages.CodeSearchRuntimeInfoDialog_runtimeInformationGroup_xtit);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(group);
    GridLayoutFactory.swtDefaults().numColumns(3).applyTo(group);

    clientQueryDuration = createDurationOutput(group,
        Messages.CodeSearchRuntimeInfoDialog_clientQueryRuntime_xlbl);
    clientQueryDurationUnit = createDurationUnitOutput(group);

    queryDuration = createDurationOutput(group,
        Messages.CodeSearchRuntimeInfoDialog_queryRuntime_xlbl);
    queryDurationUnit = createDurationUnitOutput(group);

    averageRequestDuration = createDurationOutput(group,
        Messages.CodeSearchRuntimeInfoDialog_averageRequestRuntimeLabel_xlbl);
    averageRequestDurationUnit = createDurationUnitOutput(group);
  }

  private void updateControlsFromResult() {
    if (searchedObjects == null || searchedObjects.isDisposed()) {
      return;
    }
    float percentage = (float) runtimeInfo.getSearchedObjectsCount() / runtimeInfo
        .getObjectScopeCount() * 100;

    searchedObjects.setText(String.format(
        Messages.CodeSearchRuntimeInfoDialog_searchedObjectCountPattern_xtxt, defaultFormat.format(
            runtimeInfo.getSearchedObjectsCount()), defaultFormat.format(runtimeInfo
                .getObjectScopeCount()), (int) percentage));

    searchedSources.setText(defaultFormat.format(runtimeInfo.getSearchedSourcesCount()));
    searchedLoC.setText(defaultFormat.format(runtimeInfo.getSearchedLinesOfCode()));
    foundMatches.setText(defaultFormat.format(runtimeInfo.getResultCount()));

    updateDurationLabels();

    searchedObjects.getParent().layout();
    queryDuration.getParent().layout();
  }

  private void updateDurationLabel(float duration, final Label durationLabel,
      final Label durationUnitLabel) {
    String durationUnit = UNIT_MILLISECOND;
    var numberFormat = defaultFormat;

    if (duration >= HOUR_DENOMINATOR) {
      durationUnit = UNIT_HOUR;
      duration /= HOUR_DENOMINATOR;
      numberFormat = formatWithDecimals;
    } else if (duration >= MINUTE_DENOMINATOR) {
      durationUnit = UNIT_MINUTE;
      duration /= MINUTE_DENOMINATOR;
      numberFormat = formatWithDecimals;
    } else if (duration >= SECONDS_DENOMINATOR) {
      durationUnit = UNIT_SECOND;
      duration /= SECONDS_DENOMINATOR;
      numberFormat = formatWithDecimals;
    }

    durationLabel.setText(numberFormat.format(duration));
    durationUnitLabel.setText(durationUnit);
  }

  private void updateDurationLabels() {
    updateDurationLabel(runtimeInfo.getOverallServerTimeInMs(), queryDuration, queryDurationUnit);
    updateDurationLabel(runtimeInfo.getOverallClientTimeInMs(), clientQueryDuration,
        clientQueryDurationUnit);
    updateDurationLabel(runtimeInfo.getAverageDuration(), averageRequestDuration,
        averageRequestDurationUnit);
  }

}
