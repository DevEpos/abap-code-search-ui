package com.devepos.adt.cst.ui.internal.preferences;

public enum ClassSearchScopeOption {

  ALL("All"), SPECIFIC("Only specific includes");

  private String label;

  ClassSearchScopeOption(final String label) {
    this.label = label;
  }

  /**
   * Creates key/value pair array from enum name and description
   *
   * @return key/value pair array from enum name and description
   */
  public static String[][] toNamesAndKeys() {
    final ClassSearchScopeOption[] types = ClassSearchScopeOption.values();
    final String[][] keyValue = new String[types.length][2];
    for (int i = 0; i < types.length; i++) {
      keyValue[i][0] = types[i].label;
      keyValue[i][1] = types[i].name();
    }
    return keyValue;
  }

  public String getLabel() {
    return label;
  }
}
