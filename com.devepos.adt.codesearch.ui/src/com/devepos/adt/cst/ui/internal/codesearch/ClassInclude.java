package com.devepos.adt.cst.ui.internal.codesearch;

/**
 * Defines include in an ABAP Class
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public enum ClassInclude implements IIncludeToSearch {
  PUBLIC_SECTION("pubSec", "P&ublic Section", 0x10),
  PROTECTED_SECTION("proSec", "P&rotected Section", 0x20),
  PRIVATE_SECTION("priSec", "Pri&vate Section", 0x40),
  METHODS("methods", "M&ethods", 0x80),
  LOCAL_DEFINITIONS("localDef", "Class-relevant &Local Types", 0x100),
  LOCAL_IMPLEMENTATIONS("localImpl", "Local T&ypes", 0x200),
  TESTS("tests", "&Test Classes", 0x400),
  MACROS("macros", "Ma&cros", 0x800);

  private String apiName;
  private int bit;
  private String label;

  ClassInclude(final String apiName, final String label, final int bit) {
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
