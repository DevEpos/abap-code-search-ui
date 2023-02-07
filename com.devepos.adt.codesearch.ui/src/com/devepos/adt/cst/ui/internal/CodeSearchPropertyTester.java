package com.devepos.adt.cst.ui.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.devepos.adt.base.destinations.DestinationUtil;
import com.devepos.adt.base.ui.adtobject.IAdtObject;
import com.devepos.adt.base.ui.projectexplorer.node.IAbapRepositoryFolderNode;
import com.devepos.adt.base.ui.projectexplorer.virtualfolders.IVirtualFolderNode;
import com.devepos.adt.cst.search.CodeSearchFactory;
import com.devepos.adt.cst.ui.internal.codesearch.CodeSearchRelevantWbTypesUtil;
import com.sap.adt.ris.search.ui.internal.contentassist.AdtRisObjectTypeRegistry;
import com.sap.adt.ris.search.ui.internal.contentassist.AdtRisParameterProposal;
import com.sap.adt.ris.search.ui.internal.contentassist.IAdtRisObjectTypeRegistry;

/**
 * Property tester concerning the ABAP Code Search feature
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
@SuppressWarnings("restriction")
public class CodeSearchPropertyTester extends PropertyTester {
  private static final String IS_CODE_SEARCH_AVAILABLE_PROP = "isCodeSearchAvailable";
  private static final String IS_OBJECT_SEARCHABLE_PROP = "isObjectSearchable";

  private static final List<String> VALID_VIRT_FOLDER_TYPES = Arrays.asList(
      IVirtualFolderNode.FOLDER_TYPE_APPL_COMP, IVirtualFolderNode.FOLDER_TYPE_CREATED,
      IVirtualFolderNode.FOLDER_TYPE_DATE, IVirtualFolderNode.FOLDER_TYPE_MONTH,
      IVirtualFolderNode.FOLDER_TYPE_PACKAGE, IVirtualFolderNode.FOLDER_TYPE_TYPE,
      IVirtualFolderNode.FOLDER_TYPE_GROUP, IVirtualFolderNode.FOLDER_TYPE_OWNER);

  private static final List<String> VALID_VIRT_FOLDER_TYPE_KEYS = new ArrayList<>();
  private static final List<String> VALID_ABAP_REPO_FOLDER_TYPE_KEYS = Arrays.asList(
      IAbapRepositoryFolderNode.CATEGORY_CORE_DATA_SERVICES,
      IAbapRepositoryFolderNode.CATEGORY_ACCESS_CONTROL_MGMT,
      IAbapRepositoryFolderNode.CATEGORY_DICTIONARY, IAbapRepositoryFolderNode.CATEGORY_SOURCE_LIB);

  static {
    VALID_VIRT_FOLDER_TYPE_KEYS.add(IVirtualFolderNode.FOLDER_KEY_CORE_DATA_SERVICES);
    VALID_VIRT_FOLDER_TYPE_KEYS.add(IVirtualFolderNode.FOLDER_KEY_TRANSFORMATIONS);
    VALID_VIRT_FOLDER_TYPE_KEYS.add(IVirtualFolderNode.FOLDER_KEY_SOURCE_LIBRARY);
    VALID_VIRT_FOLDER_TYPE_KEYS.addAll(CodeSearchRelevantWbTypesUtil
        .getPossibleValuesForTypeFilter()
        .stream()
        .map(t -> t.toLowerCase())
        .collect(Collectors.toList()));
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
          if (virtualFolder.getFolderType().equals(IVirtualFolderNode.FOLDER_TYPE_TYPE)
              || virtualFolder.getFolderType().equals(IVirtualFolderNode.FOLDER_TYPE_GROUP)) {
            return VALID_VIRT_FOLDER_TYPE_KEYS.contains(virtualFolder.getFolderKey());
          }
          return true;
        }
        return false;
      };
    } else if (receiver instanceof IAbapRepositoryFolderNode) {
      // should only be relevant for 7.40-7.50 as Repository Trees and therefore Virtual Folders
      // are available starting with 7.51
      IAbapRepositoryFolderNode folder = (IAbapRepositoryFolderNode) receiver;
      project = folder.getProject();
      String destinationId = DestinationUtil.getDestinationId(project);
      additionalCheck = () -> {
        String category = folder.getCategory();
        if (category != null) {
          if (!VALID_ABAP_REPO_FOLDER_TYPE_KEYS.contains(category)) {
            return false;
          }

          // Verify that the category contains the type
          // DICTIONARY (7.40) -> DDLS
          // DICTIONARY (7.50) -> invalid
          // CORE_DATA_SERVICES (7.50) -> DDLS,DCLS
          // do not block the property test too much
          if (IAbapRepositoryFolderNode.CATEGORY_DICTIONARY.equals(category)) {
            if (AdtRisObjectTypeRegistry.isLoaded(destinationId)) {
              try {
                IAdtRisObjectTypeRegistry typeRegistry = AdtRisObjectTypeRegistry.getInstance(
                    destinationId, new NullProgressMonitor());
                List<AdtRisParameterProposal> foundObjectTypes = typeRegistry
                    .getObjectTypeProposalList(
                        IAbapRepositoryFolderNode.CATEGORY_CORE_DATA_SERVICES);
                return foundObjectTypes == null || foundObjectTypes.isEmpty();
              } catch (Exception e) {
                // exception handling not necessary
              }
            }
          }
        }
        return true;
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
