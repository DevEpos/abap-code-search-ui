package com.devepos.adt.cst.ui.internal.preferences;

public enum ClassInclude {
  GLOBAL_CLASS("global"),
  TESTS("tests"),
  LOCAL_DEFINITIONS("localDef"),
  LOCAL_IMPLEMENTATIONS("localImpl"),
  MACROS("macros");

  private String apiName;

  private ClassInclude(String apiName) {
    this.apiName = apiName;
  }

  public String getApiName() {
    return apiName;
  }

}
