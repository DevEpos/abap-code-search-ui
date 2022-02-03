package com.devepos.adt.cst.ui.internal.codesearch;

/**
 * Defines an include of an ABAP function group
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public enum FunctionGroupInclude implements IIncludeToSearch {
  FUNCTION_INCLUDE("func", "&Functions", 0x10),
  NON_FUNCTION_INCLUDE("nonFunc", "&Other includes", 0x20);

  private String apiName;
  private int bit;
  private String label;

  FunctionGroupInclude(final String apiName, final String label, final int bit) {
    this.apiName = apiName;
    this.label = label;
    this.bit = bit;
  }

  @Override
  public String getApiName() {
    return apiName;
  }

  @Override
  public int getBit() {
    return bit;
  }

  @Override
  public String getLabel() {
    return label;
  }
}
