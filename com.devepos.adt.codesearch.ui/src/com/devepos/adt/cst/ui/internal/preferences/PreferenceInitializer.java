package com.devepos.adt.cst.ui.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;
import com.devepos.adt.cst.ui.internal.export.ResultExporter;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
   * initializeDefaultPreferences()
   */
  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore store = CodeSearchUIPlugin.getDefault().getPreferenceStore();
    store.setDefault(ICodeSearchPrefs.SINGLE_PATTERN_REGEX_CONCAT_WITH_LF, false);
    store.setDefault(ICodeSearchPrefs.REMEMBER_INCLUDE_SETTINGS, true);
    store.setDefault(ICodeSearchPrefs.CSV_EXPORT_USE_MATCH_MARKER, false);
    store.setDefault(ICodeSearchPrefs.CSV_EXPORT_MATCH_MARKER_SEQUENCE, "%%");
    store.setDefault(ICodeSearchPrefs.CSV_EXPORT_DELIMITER, ResultExporter.COMMA_DELIMITER);
  }

}
