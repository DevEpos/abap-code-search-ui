package com.devepos.adt.cst.ui.internal.codesearch;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.sap.adt.util.ui.swt.AdtSWTUtilFactory;
import com.sap.adt.util.ui.swt.IAdtSWTUtil;

/**
 * Dialog for selected relevant includes of an ABAP repository object
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class IncludeFlagsDialog extends TrayDialog {

  private List<Button> includeCheckBoxes = new ArrayList<>();
  private int includeFlags;
  private IIncludeToSearch[] includes;
  private String dialogTitle;
  private Composite checksGroup;

  public IncludeFlagsDialog(final Shell shell, final String dialogTitle, final int includeFlags,
      final IIncludeToSearch[] includes) {
    super(shell);
    this.includeFlags = includeFlags;
    this.includes = includes;
    this.dialogTitle = dialogTitle;
  }

  public int getSelectedFlags() {
    return includeFlags;
  }

  @Override
  protected final void configureShell(final Shell shell) {
    super.configureShell(shell);
    shell.setText(dialogTitle);
  }

  @Override
  protected Control createButtonBar(final Composite parent) {
    Control buttonBar = super.createButtonBar(parent);
    updateOkButtonState();
    return buttonBar;
  }

  /**
   * Creates the composite controls for the include checks. <br>
   * The default implementation creates a single composite to hold all the include options.
   *
   * @param parent the parent composite
   */
  protected void createCheckComposites(final Composite parent) {
    checksGroup = new Composite(parent, SWT.NONE);
    GridLayoutFactory.swtDefaults().margins(0, 0).applyTo(checksGroup);
    GridDataFactory.fillDefaults()
        .align(SWT.LEFT, SWT.BEGINNING)
        .grab(true, false)
        .hint(convertWidthInCharsToPixels(55), SWT.DEFAULT)
        .applyTo(checksGroup);
  }

  @Override
  protected Control createDialogArea(final Composite parent) {
    Composite contents = (Composite) super.createDialogArea(parent);

    Label label = new Label(contents, SWT.NONE);
    label.setText("Select the includes that should be searched");
    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).applyTo(label);

    createIncludeChecks(contents);
    createSelectButtons(contents);

    return contents;
  }

  /**
   * Retrieves the parent composite for the given include option
   *
   * @param incl the include option
   * @return the parent composite for the include options
   */
  protected Composite getParentForInclude(final IIncludeToSearch incl) {
    return checksGroup;
  }

  @Override
  protected boolean isResizable() {
    return true;
  }

  private void createIncludeCheckOption(final IIncludeToSearch incl) {
    Button includeCheckBox = new Button(getParentForInclude(incl), SWT.CHECK);
    includeCheckBox.setData(incl.getBit());
    includeCheckBoxes.add(includeCheckBox);
    includeCheckBox.setText(incl.getLabel());

    if ((includeFlags & incl.getBit()) != 0) {
      includeCheckBox.setSelection(true);
    }

    includeCheckBox.addSelectionListener(widgetSelectedAdapter(e -> {
      updateSelectedIncludeBits();
    }));
  }

  private void createIncludeChecks(final Composite parent) {
    createCheckComposites(parent);

    for (IIncludeToSearch incl : includes) {
      createIncludeCheckOption(incl);
    }
  }

  private void createSelectButtons(final Composite parent) {
    Composite buttonComposite = new Composite(parent, SWT.NONE);
    GridDataFactory.fillDefaults()
        .align(SWT.LEFT, SWT.BEGINNING)
        .grab(true, false)
        .applyTo(buttonComposite);
    GridLayoutFactory.swtDefaults().margins(0, 0).numColumns(2).applyTo(buttonComposite);

    IAdtSWTUtil swtUtil = AdtSWTUtilFactory.getOrCreateSWTUtil();
    Button selectAllButton = new Button(buttonComposite, SWT.PUSH);
    swtUtil.setButtonWidthHint(selectAllButton);
    selectAllButton.setText("Select &all");
    selectAllButton.addSelectionListener(widgetSelectedAdapter(e -> {
      performCheckBoxSelection(true);
    }));

    Button deselectAllButton = new Button(buttonComposite, SWT.PUSH);
    swtUtil.setButtonWidthHint(deselectAllButton);
    deselectAllButton.setText("&Deselect all");
    deselectAllButton.addSelectionListener(widgetSelectedAdapter(e -> {
      performCheckBoxSelection(false);
    }));

  }

  private void performCheckBoxSelection(final boolean select) {
    for (Button includeCheckBox : includeCheckBoxes) {
      includeCheckBox.setSelection(select);
    }
    updateSelectedIncludeBits();
    updateOkButtonState();
  }

  private void updateOkButtonState() {
    getButton(IDialogConstants.OK_ID).setEnabled(includeFlags != 0);
  }

  private void updateSelectedIncludeBits() {
    int selected = 0;
    for (Button check : includeCheckBoxes) {
      if (check.getSelection()) {
        selected |= (Integer) check.getData();
      }
    }
    includeFlags = selected;
    updateOkButtonState();
  }
}
