package com.devepos.adt.cst.internal;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * Plugin for accessing the server side ABAP API's of the ABAP Code search
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchPlugin extends Plugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "com.devepos.adt.codesearch.backend"; //$NON-NLS-1$

  // The shared instance
  private static CodeSearchPlugin plugin;

  /**
   * The constructor
   */
  public CodeSearchPlugin() {
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static CodeSearchPlugin getDefault() {
    return plugin;
  }

  @Override
  public void start(final BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  @Override
  public void stop(final BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

}
