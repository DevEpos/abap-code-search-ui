package com.devepos.adt.cst.ui.internal;

import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.BundleContext;

import com.devepos.adt.base.ui.plugin.AbstractAdtUIPlugin;

/**
 * The activator class controls the plug-in life cycle
 *
 * @author Ludwig Stockbauer-Muhr
 */
public class CodeSearchUIPlugin extends AbstractAdtUIPlugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "com.devepos.adt.codesearch.ui"; //$NON-NLS-1$

  // The shared instance
  private static CodeSearchUIPlugin plugin;

  /**
   * The constructor
   */
  public CodeSearchUIPlugin() {
    super(PLUGIN_ID);
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static CodeSearchUIPlugin getDefault() {
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

  @Override
  protected void initializeImageRegistry(final ImageRegistry reg) {
    registerImage(reg, IImages.CODE_SEARCH, "icons/codesearch.png");
  }

}
