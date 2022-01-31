package com.devepos.adt.cst.ui.internal.codesearch;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.RowLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Link;

/**
 * Radio button group composite for setting the source includes option of an ABAP workbench type
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class IncludeFlagsRadioButtonGroup {
  protected String groupTitle;
  protected IncludeFlagsParameter includeFlagsParam;
  private Link link;
  private Button allIncludesRadio;
  private Button selectedIncludesRadio;

  public IncludeFlagsRadioButtonGroup(final String groupTitle,
      final IncludeFlagsParameter includeFlagsParam) {
    this.groupTitle = groupTitle;
    this.includeFlagsParam = includeFlagsParam;
  }

  /**
   * Creates the contents of the control
   *
   * @param parent the parent composite
   */
  public void createControl(final Composite parent) {
    final Group group = new Group(parent, SWT.NONE);
    group.setText(groupTitle);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(group);
    RowLayoutFactory.swtDefaults().applyTo(group);

    allIncludesRadio = new Button(group, SWT.RADIO);
    allIncludesRadio.setText("All");
    allIncludesRadio.setSelection(includeFlagsParam.isAllIncludes());
    allIncludesRadio.addSelectionListener(widgetSelectedAdapter(l -> {
      includeFlagsParam.setAllIncludes(true);
    }));

    selectedIncludesRadio = new Button(group, SWT.RADIO);
    selectedIncludesRadio.setText("Selected");
    selectedIncludesRadio.setSelection(!includeFlagsParam.isAllIncludes());
    selectedIncludesRadio.addSelectionListener(widgetSelectedAdapter(l -> {
      includeFlagsParam.setAllIncludes(false);
    }));

    link = new Link(group, SWT.NONE);

    link.addSelectionListener(widgetSelectedAdapter(e -> {
      IncludeFlagsDialog includeSelectionDialog = createDialog();
      if (includeSelectionDialog.open() == Window.OK) {
        selectedIncludesRadio.setSelection(true);
        allIncludesRadio.setSelection(false);
        includeFlagsParam.setAllIncludes(false);
        includeFlagsParam.setIncludeFlags(includeSelectionDialog.getSelectedFlags());
        updateLinkText();
      }
    }));
    updateLinkText();
  }

  /**
   * Updates the radio buttons and link depending on the current
   * values of the underlying model
   */
  public void updateControlsFromModel() {
    if (allIncludesRadio == null || allIncludesRadio.isDisposed()) {
      return;
    }
    allIncludesRadio.setSelection(includeFlagsParam.isAllIncludes());
    selectedIncludesRadio.setSelection(!includeFlagsParam.isAllIncludes());
    updateLinkText();
  }

  public void updateLinkText() {
    if (link == null) {
      return;
    }

    link.setText(NLS.bind("(<a>{0} of {1} selected</a>)", new Object[] { includeFlagsParam
        .getSelectedIncludeCount(), includeFlagsParam.getPossibleIncludes().length }));
  }

  protected IncludeFlagsDialog createDialog() {
    return new IncludeFlagsDialog(link.getShell(), groupTitle, includeFlagsParam.getIncludeFlags(),
        includeFlagsParam.getPossibleIncludes());
  }
}