package com.devepos.adt.cst.ui.internal.codesearch;

import com.devepos.adt.cst.ui.internal.messages.Messages;

/**
 * Defines include in an ABAP Class
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public enum ClassInclude implements IIncludeToSearch {
  PUBLIC_SECTION("pubSec", Messages.ClassInclude_publicSectionInclude_xlbl, 0x10),
  PROTECTED_SECTION("proSec", Messages.ClassInclude_protectedSectionInclude_xlbl, 0x20),
  PRIVATE_SECTION("priSec", Messages.ClassInclude_privateSectionInclude_xlbl, 0x40),
  METHODS("methods", Messages.ClassInclude_methodsIncludes_xlbl, 0x80),
  LOCAL_DEFINITIONS("localDef", Messages.ClassInclude_localClassDefinitionsInclude_xlbl, 0x100),
  LOCAL_IMPLEMENTATIONS("localImpl", Messages.ClassInclude_localClassTypesInclude_xlbl, 0x200),
  TESTS("tests", Messages.ClassInclude_testClassesInclude_xlbl, 0x400),
  MACROS("macros", Messages.ClassInclude_macrosInclude_xlbl, 0x800);

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
