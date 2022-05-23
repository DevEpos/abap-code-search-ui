package com.devepos.adt.cst.ui.internal;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;

import com.devepos.adt.base.ui.adtobject.IAdtObject;
import com.devepos.adt.base.ui.virtualfolders.IVirtualFolderNode;
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
    if (IS_CODE_SEARCH_AVAILABLE_PROP.equals(property)) {
      return isCodeSearchAvailable(receiver);
    }
    if (IS_OBJECT_SEARCHABLE_PROP.equals(property)) {
      if (!(receiver instanceof IAdtObject)) {
        return false;
      }
      return isObjectSearchable((IAdtObject) receiver);
    }
    return false;
  }

  private boolean isCodeSearchAvailable(Object receiver) {
    IProject project = null;
    if (receiver instanceof IAdtObject) {
      project = ((IAdtObject) receiver).getProject();
    } else if (receiver instanceof IVirtualFolderNode) {
      project = ((IVirtualFolderNode) receiver).getProject();
    }

    return project != null ? CodeSearchFactory.getCodeSearchService()
        .testCodeSearchFeatureAvailability(project)
        .isOK() : false;
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
