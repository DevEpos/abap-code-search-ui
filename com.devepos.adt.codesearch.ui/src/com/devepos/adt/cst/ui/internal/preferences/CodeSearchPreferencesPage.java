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
import com.devepos.adt.cst.ui.internal.messages.Messages;
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
    createSearchDialogSettings(parent);
  }

  private void createGeneralSettings(final Composite parent) {
    final Group group = createGroup(Messages.CodeSearchPreferencesPage_generalSettingsGroup_xlbl,
        parent);

    Composite maxSearchResultsParent = createEditorParent(group);
    IntegerFieldEditor maxSearchResultsEditor = new IntegerFieldEditor(ICodeSearchPrefs.MAX_OBJECTS,
        Messages.CodeSearchPreferencesPage_maxNumberOfRequestObjectsPref_xlbl,
        maxSearchResultsParent, 5);
    maxSearchResultsEditor.setValidRange(100, 10000);
    maxSearchResultsEditor.getLabelControl(maxSearchResultsParent)
        .setToolTipText(Messages.CodeSearchPreferencesPage_maxNumberOfRequestObjectsPref_xtol);
    addEditor(maxSearchResultsEditor);

    addEditor(addBooleanEditor(ICodeSearchPrefs.REUSE_LAST_SEARCH_QUERY,
        Messages.CodeSearchPreferencesPage_reuseSearchQueryPref_xchk,
        Messages.CodeSearchPreferencesPage_reuseSearchQueryPref_xtol, createEditorParent(group)));
  }

  private Group createGroup(final String label, final Composite parent) {
    final Group group = new Group(parent, SWT.NONE);
    GridLayoutFactory.swtDefaults().applyTo(group);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(group);
    group.setText(label);
    return group;
  }

  private void createSearchDialogSettings(final Composite parent) {
    final Group group = createGroup(
        Messages.CodeSearchPreferencesPage_searchDialogSettingsGroup_xlbl, parent);

    final Group singlePatternModeGroup = createGroup(
        Messages.CodeSearchPreferencesPage_singlePatternModeSettingsGroup_xlbl, group);

    addEditor(addBooleanEditor(ICodeSearchPrefs.SINGLE_PATTERN_REGEX_CONCAT_WITH_LF,
        Messages.CodeSearchPreferencesPage_concatLinesWithLfRegexSinglePatternPref_xlbl,
        createEditorParent(singlePatternModeGroup)));
  }
}