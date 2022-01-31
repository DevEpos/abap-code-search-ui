package com.devepos.adt.cst.ui.internal.preferences;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.devepos.adt.base.ui.preferences.FieldEditorPrefPageBase;
import com.devepos.adt.base.ui.preferences.ILinkToAdtPageBlock;
import com.devepos.adt.base.ui.preferences.LinkToAdtPageBlocksFactory;
import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;
import com.devepos.adt.cst.ui.internal.pages.CodeSearchPropertyPage;

/**
 * Preferences for the code search
 *
 * @author Ludwig Stockbauer-Muhr
 */
public class CodeSearchPreferencesPage extends FieldEditorPrefPageBase implements
    IWorkbenchPreferencePage {

  public static final String PAGE_ID = "com.devepos.adt.codesearch.ui.preferences.CodeSearchPreferencesPage";

  @Override
  public void init(final IWorkbench workbench) {
    setPreferenceStore(CodeSearchUIPlugin.getDefault().getPreferenceStore());
  }

  @Override
  protected void createPreferenceControls(final Composite parent) {
    ILinkToAdtPageBlock linkToPropertyPage = LinkToAdtPageBlocksFactory.createLinkToPropertyPage(
        CodeSearchPropertyPage.PAGE_ID, null);
    linkToPropertyPage.createControl(parent, GridDataFactory.fillDefaults()
        .align(SWT.RIGHT, SWT.FILL)
        .create());

    createGeneralSettings(parent);
  }

  private void createGeneralSettings(final Composite parent) {
    final Group group = createGroup("General settings", parent);

    Composite maxSearchResultsParent = createEditorParent(group);
    IntegerFieldEditor maxSearchResultsEditor = new IntegerFieldEditor(ICodeSearchPrefs.MAX_OBJECTS,
        "&Max. objects to search in a single request", maxSearchResultsParent, 5);
    maxSearchResultsEditor.setValidRange(100, 10000);
    maxSearchResultsEditor.getLabelControl(maxSearchResultsParent)
        .setToolTipText(
            "Restricts the maximum number of objects to be searched in a single search request");
    addEditor(maxSearchResultsEditor);

    addEditor(addBooleanEditor(ICodeSearchPrefs.REUSE_LAST_SEARCH_QUERY,
        "Reuse search query when opened from result view",
        "This will prevent the creation of a new history entry and overwrites the current result",
        createEditorParent(group)));
  }

  private Group createGroup(final String label, final Composite parent) {
    final Group group = new Group(parent, SWT.NONE);
    GridLayoutFactory.swtDefaults().applyTo(group);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(group);
    group.setText(label);
    return group;
  }
}