package com.devepos.adt.cst.ui.internal.preferences;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
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
  private Map<BooleanFieldEditor, Composite> classScopeOptions = new HashMap<>();
  private RadioGroupFieldEditor classSearchScopeOptionEditor;
  private ClassSearchScopeOption currentClassSearchScope;

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
    createClassSearchSettings(parent);
  }

  @Override
  protected void fieldValueChanged(final FieldEditor field, final Object oldValue,
      final Object newValue) {
    if (field.getPreferenceName().startsWith("codeSearch.classScope")) {
      if (field.getPreferenceName() == ICodeSearchPrefs.CLASS_SCOPE_OPTION) {
        currentClassSearchScope = ClassSearchScopeOption.valueOf(((RadioGroupFieldEditor) field)
            .getSelectionValue());
        updateClassScopeCheckBoxes();
      }
      if (currentClassSearchScope == ClassSearchScopeOption.SPECIFIC) {
        if (!classScopeOptions.keySet().stream().anyMatch(BooleanFieldEditor::getBooleanValue)) {
          setValid(false);
        } else {
          checkState();
        }
      } else {
        checkState();
      }
    }
  }

  @Override
  protected void initFields() {
    super.initFields();
    String classScopeSearchOptionPrefVal = getPreferenceStore().getString(
        ICodeSearchPrefs.CLASS_SCOPE_OPTION);
    if (classScopeSearchOptionPrefVal == null || classScopeSearchOptionPrefVal.isEmpty()) {
      classScopeSearchOptionPrefVal = getPreferenceStore().getDefaultString(
          ICodeSearchPrefs.CLASS_SCOPE_OPTION);
    }
    currentClassSearchScope = ClassSearchScopeOption.valueOf(classScopeSearchOptionPrefVal);
    updateClassScopeCheckBoxes();
  }

  @Override
  protected void performDefaults() {
    super.performDefaults();
    currentClassSearchScope = ClassSearchScopeOption.valueOf(classSearchScopeOptionEditor
        .getSelectionValue());
    updateClassScopeCheckBoxes();
    // the default values should produce a valid state of the page
    setValid(true);
  }

  private void createClassScopeOption(final String prefKey, final String label,
      final Composite group) {
    Composite parent = createEditorParent(group);
    classScopeOptions.put(addBooleanEditor(prefKey, label, parent), parent);

    // add indent to boolean editor
    GridData layoutData = (GridData) parent.getLayoutData();
    if (layoutData != null) {
      layoutData.horizontalIndent = convertWidthInCharsToPixels(3);
    }
  }

  private void createClassSearchSettings(final Composite parent) {
    final Group group = createGroup("Class Search Settings", parent);

    Composite classSearchScopeParent = createEditorParent(group);
    classSearchScopeOptionEditor = new RadioGroupFieldEditor(ICodeSearchPrefs.CLASS_SCOPE_OPTION,
        "Includes to search", 1, ClassSearchScopeOption.toNamesAndKeys(), classSearchScopeParent,
        true);
    Composite classSearchScopeRadioBox = classSearchScopeOptionEditor.getRadioBoxControl(
        classSearchScopeParent);
    addEditor(classSearchScopeOptionEditor);

    createClassScopeOption(ICodeSearchPrefs.CLASS_SCOPE_GLOBAL_ENABLED, "&Global Class",
        classSearchScopeRadioBox);
    createClassScopeOption(ICodeSearchPrefs.CLASS_SCOPE_LOCAL_DEF_ENABLED,
        "Class-relevant Local Types", classSearchScopeRadioBox);
    createClassScopeOption(ICodeSearchPrefs.CLASS_SCOPE_LOCAL_IMPL_ENABLED, "Local Types",
        classSearchScopeRadioBox);
    createClassScopeOption(ICodeSearchPrefs.CLASS_SCOPE_MACROS_ENABLED, "Test Classes",
        classSearchScopeRadioBox);
    createClassScopeOption(ICodeSearchPrefs.CLASS_SCOPE_TESTS_ENABLED, "Macros",
        classSearchScopeRadioBox);
  }

  private Group createGroup(final String label, final Composite parent) {
    final Group group = new Group(parent, SWT.NONE);
    GridLayoutFactory.swtDefaults().applyTo(group);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(group);
    group.setText(label);
    return group;
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

  }

  private void updateClassScopeCheckBoxes() {
    boolean enabled = currentClassSearchScope == ClassSearchScopeOption.SPECIFIC;
    classScopeOptions.keySet()
        .forEach(booleanEditor -> booleanEditor.setEnabled(enabled, classScopeOptions.get(
            booleanEditor)));
  }
}