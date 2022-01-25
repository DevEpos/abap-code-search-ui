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
    store.setDefault(ICodeSearchPrefs.MAX_RESULTS, 100);
    store.setDefault(ICodeSearchPrefs.CLASS_SCOPE_OPTION, ClassSearchScopeOption.ALL.name());
    store.setDefault(ICodeSearchPrefs.CLASS_SCOPE_GLOBAL_ENABLED, false);
    store.setDefault(ICodeSearchPrefs.CLASS_SCOPE_LOCAL_DEF_ENABLED, false);
    store.setDefault(ICodeSearchPrefs.CLASS_SCOPE_LOCAL_IMPL_ENABLED, false);
    store.setDefault(ICodeSearchPrefs.CLASS_SCOPE_MACROS_ENABLED, false);
    store.setDefault(ICodeSearchPrefs.CLASS_SCOPE_TESTS_ENABLED, false);
  }

}
