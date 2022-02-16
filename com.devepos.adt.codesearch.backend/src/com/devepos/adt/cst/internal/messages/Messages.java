package com.devepos.adt.cst.internal.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

  public static String CodeSearchSearchService_searchNotAvailableInProjectError_xmsg;

  private Messages() {
  }

  static {
    // initialize resource bundle
    NLS.initializeMessages("com.devepos.adt.cst.internal.messages.messages", Messages.class); //$NON-NLS-1$
  }
}
