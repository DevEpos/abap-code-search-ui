package com.devepos.adt.cst.ui.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;

import com.devepos.adt.base.ui.adtobject.IAdtObject;
import com.devepos.adt.base.ui.projectexplorer.virtualfolders.IVirtualFolderNode;
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

  private static final List<String> VALID_VIRT_FOLDER_TYPES = Arrays.asList(
      IVirtualFolderNode.FOLDER_TYPE_APPL_COMP, IVirtualFolderNode.FOLDER_TYPE_CREATED,
      IVirtualFolderNode.FOLDER_TYPE_DATE, IVirtualFolderNode.FOLDER_TYPE_MONTH,
      IVirtualFolderNode.FOLDER_TYPE_PACKAGE, IVirtualFolderNode.FOLDER_TYPE_TYPE,
      IVirtualFolderNode.FOLDER_TYPE_OWNER);

  private static final List<String> VALID_VIRT_FOLDER_TYPE_KEYS = new ArrayList<>();

  static {
    VALID_VIRT_FOLDER_TYPE_KEYS.add(IVirtualFolderNode.FOLDER_KEY_CORE_DATA_SERVICES);
    VALID_VIRT_FOLDER_TYPE_KEYS.add(IVirtualFolderNode.FOLDER_KEY_SOURCE_LIBRARY);
    VALID_VIRT_FOLDER_TYPE_KEYS.addAll(CodeSearchRelevantWbTypesUtil
        .getPossibleValuesForTypeFilter());
  }

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

  private boolean isCodeSearchAvailable(final Object receiver) {
    IProject project = null;
    Supplier<Boolean> additionalCheck = null;

    if (receiver instanceof IAdtObject) {
      project = ((IAdtObject) receiver).getProject();
    } else if (receiver instanceof IVirtualFolderNode) {
      IVirtualFolderNode virtualFolder = (IVirtualFolderNode) receiver;
      project = virtualFolder.getProject();
      additionalCheck = () -> {
        if (virtualFolder.isTree()) {
          return true;
        }
        if (VALID_VIRT_FOLDER_TYPES.contains(virtualFolder.getFolderType())) {
          if (virtualFolder.getFolderType().equals(IVirtualFolderNode.FOLDER_TYPE_TYPE)) {
            return VALID_VIRT_FOLDER_TYPE_KEYS.contains(virtualFolder.getFolderKey());
          }
          return true;
        }
        return false;
      };
    }

    boolean isProjectValid = project != null ? CodeSearchFactory.getCodeSearchService()
        .testCodeSearchFeatureAvailability(project)
        .isOK() : false;

    return additionalCheck != null ? additionalCheck.get() && isProjectValid : isProjectValid;
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
