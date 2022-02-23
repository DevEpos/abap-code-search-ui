package com.devepos.adt.cst.ui.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;

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
    store.setDefault(ICodeSearchPrefs.MAX_OBJECTS, 100);
    store.setDefault(ICodeSearchPrefs.SINGLE_PATTERN_REGEX_CONCAT_WITH_LF, false);
  }

}
