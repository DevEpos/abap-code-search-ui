package com.devepos.adt.cst.ui.internal;

import org.eclipse.core.expressions.PropertyTester;

import com.devepos.adt.base.ui.adtobject.IAdtObject;
import com.devepos.adt.cst.search.CodeSearchFactory;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchRelevantWbTypesUtil;

/**
 * Property tester concerning the ABAP Code Search feature
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class CodeSearchPropertyTester extends PropertyTester {
  private static final String IS_CODE_SEARCH_AVAILABLE_PROP = "isCodeSearchAvailable";
  private static final String IS_OBJECT_SEARCHABLE_PROP = "isObjectSearchable";

  public CodeSearchPropertyTester() {
  }

  @Override
  public boolean test(final Object receiver, final String property, final Object[] args,
      final Object expectedValue) {
    if (!(receiver instanceof IAdtObject)) {
      return false;
    }
    IAdtObject adtObj = (IAdtObject) receiver;
    if (IS_CODE_SEARCH_AVAILABLE_PROP.equals(property)) {
      return CodeSearchFactory.getCodeSearchService()
          .testCodeSearchFeatureAvailability(adtObj.getProject())
          .isOK();
    }
    if (IS_OBJECT_SEARCHABLE_PROP.equals(property)) {
      return isObjectSearchable(adtObj);
    }
    return false;
  }

  private boolean isObjectSearchable(final IAdtObject adtObj) {
    String adtType = adtObj.getReference().getType();
    if (adtType == null) {
      return false;
    }
    return CodeSearchRelevantWbTypesUtil.getRelevantTypesForHandler()
        .stream()
        .anyMatch(type -> type.equalsIgnoreCase(adtType));
  }

}
