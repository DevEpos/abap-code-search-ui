package com.devepos.adt.cst.ui.internal.preferences;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.devepos.adt.base.ui.preferences.FieldEditorPrefPageBase;
import com.devepos.adt.base.ui.preferences.ILinkToAdtPageBlock;
import com.devepos.adt.base.ui.preferences.LinkToAdtPageBlocksFactory;
import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;
import com.devepos.adt.cst.ui.internal.help.HelpContexts;
import com.devepos.adt.cst.ui.internal.help.HelpUtil;
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
  protected Control createContents(final Composite parent) {
    HelpUtil.setHelp(parent, HelpContexts.CODE_SEARCH_PREFERENCES);
    return super.createContents(parent);
  }

  @Override
  protected void createPreferenceControls(final Composite parent) {
    ILinkToAdtPageBlock linkToPropertyPage = LinkToAdtPageBlocksFactory.createLinkToPropertyPage(
        CodeSearchPropertyPage.PAGE_ID, null);
    linkToPropertyPage.createControl(parent, GridDataFactory.fillDefaults()
        .align(SWT.RIGHT, SWT.FILL)
        .create());

    createSearchDialogSettings(parent);
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