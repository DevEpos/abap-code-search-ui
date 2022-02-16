package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility for handling flags for source includes
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class IncludeFlagsUtil {

  /**
   * Bit to be used if all includes of a given workbench type shall be searched
   */
  public static int ALL_INCLUDES_FLAG = 0x1;
  /**
   * API name to indicate that all includes of a given workbench type shall be searched
   */
  public static String ALL_INCLUDES_API_NAME = "all";

  private IncludeFlagsUtil() {
  }

  public static String convertFlagsToString(final int flags, final IIncludeToSearch[] includes) {
    if (flags == ALL_INCLUDES_FLAG || (flags & ALL_INCLUDES_FLAG) != 0) {
      return ALL_INCLUDES_API_NAME;
    }
    List<String> settings = new ArrayList<>();
    for (IIncludeToSearch incl : includes) {
      if ((flags & incl.getBit()) != 0) {
        settings.add(incl.getApiName());
      }
    }
    return String.join(",", settings);

  }
}
