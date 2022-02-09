package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.Arrays;
import java.util.List;

import com.devepos.adt.base.IAdtObjectTypeConstants;

public class CodeSearchRelevantWbTypesUtil {

  private static List<String> RELEVANT_TYPES;

  /**
   * Retrieves list of ADT workbench types that are relevant for the Code Search Handler
   *
   * @return list of ADT workbench types
   */
  public static List<String> getRelevantTypesForHandler() {
    if (RELEVANT_TYPES == null) {
      RELEVANT_TYPES = Arrays.asList(IAdtObjectTypeConstants.CLASS,
          IAdtObjectTypeConstants.CLASS_INCLUDE, IAdtObjectTypeConstants.INTERFACE,
          IAdtObjectTypeConstants.TYPE_GROUP, IAdtObjectTypeConstants.FUNCTION_GROUP,
          IAdtObjectTypeConstants.FUNCTION_INCLUDE, IAdtObjectTypeConstants.FUNCTION_MODULE,
          IAdtObjectTypeConstants.SIMPLE_TRANSFORMATION, IAdtObjectTypeConstants.PROGRAM,
          IAdtObjectTypeConstants.PROGRAM_INCLUDE, IAdtObjectTypeConstants.BEHAVIOR_DEFINITION,
          IAdtObjectTypeConstants.ACCESS_CONTROL, IAdtObjectTypeConstants.DATA_DEFINITION,
          IAdtObjectTypeConstants.METADATA_EXTENSION);
    }
    return RELEVANT_TYPES;
  }
}
