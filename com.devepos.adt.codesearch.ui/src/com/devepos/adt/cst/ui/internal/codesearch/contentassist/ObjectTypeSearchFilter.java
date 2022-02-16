package com.devepos.adt.cst.ui.internal.codesearch.contentassist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.dialogs.SearchPattern;

import com.devepos.adt.base.IAdtObjectTypeConstants;
import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseImages;
import com.devepos.adt.base.ui.contentassist.ITextQueryProposalProvider;
import com.devepos.adt.base.ui.search.ISearchFilter;
import com.devepos.adt.base.ui.search.contentassist.SearchFilterValueProposal;
import com.devepos.adt.base.ui.util.AdtTypeUtil;
import com.devepos.adt.base.ui.util.IAdtObjectTypeProxy;
import com.devepos.adt.cst.ui.internal.codesearch.FilterName;
import com.devepos.adt.cst.ui.internal.messages.Messages;

/**
 * Search filter for object type
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class ObjectTypeSearchFilter implements ISearchFilter, ITextQueryProposalProvider {

  private static final String WILDCARD = "*";
  private Map<String, IAdtObjectTypeProxy> adtTypeMap = new TreeMap<>();
  private String description;
  private Image image;

  public ObjectTypeSearchFilter() {
  }

  @Override
  public String getDescription() {
    if (description == null) {
      description = NLS.bind(Messages.SearchFilters_objectTypeFilterDescription_xmsg, new Object[] {
          FilterName.OBJECT_TYPE.getContentAssistName(), "clas" });
    }
    return description;
  }

  @Override
  public Image getImage() {
    if (image == null) {
      image = AdtBaseUIResources.getImage(IAdtBaseImages.WB_OBJECT_TYPE);
    }
    return image;
  }

  @Override
  public String getLabel() {
    return FilterName.OBJECT_TYPE.getContentAssistName();
  }

  @Override
  public List<IContentProposal> getProposalList(final String query) throws CoreException {
    if (adtTypeMap.isEmpty()) {
      initAdtTypes();
    }
    List<IContentProposal> proposals = new ArrayList<>();
    if (query == null || query.isEmpty()) {
      adtTypeMap.forEach((key, value) -> {
        proposals.add(new SearchFilterValueProposal(key, this, value.getDescription(), "", value
            .getImage()));
      });
    } else {
      SearchPattern searchPattern = createPattern(query.toLowerCase());
      for (String typeName : adtTypeMap.keySet()) {
        if (searchPattern.matches(typeName)) {
          IAdtObjectTypeProxy adtType = adtTypeMap.get(typeName);
          proposals.add(new SearchFilterValueProposal(typeName, this, adtType.getDescription(),
              query, adtType.getImage()));
        }
      }
    }

    return proposals;
  }

  @Override
  public boolean isBuffered() {
    return true;
  }

  @Override
  public boolean supportsMultipleValues() {
    return true;
  }

  @Override
  public boolean supportsNegatedValues() {
    return true;
  }

  @Override
  public boolean supportsPatternValues() {
    return false;
  }

  private SearchPattern createPattern(final String query) {
    String patternString = null;
    if (query == null) {
      patternString = WILDCARD;
    } else if (!query.endsWith(WILDCARD)) {
      patternString = String.valueOf(query) + WILDCARD;
    } else {
      patternString = query;
    }
    SearchPattern pattern = new SearchPattern();
    pattern.setPattern(patternString);
    return pattern;
  }

  private void initAdtTypes() {
    AdtTypeUtil typeUtil = AdtTypeUtil.getInstance();
    adtTypeMap.put("CLAS", typeUtil.getType(IAdtObjectTypeConstants.CLASS));
    adtTypeMap.put("INTF", typeUtil.getType(IAdtObjectTypeConstants.INTERFACE));
    adtTypeMap.put("PROG", typeUtil.getType(IAdtObjectTypeConstants.PROGRAM));
    adtTypeMap.put("TYPE", typeUtil.getType(IAdtObjectTypeConstants.TYPE_GROUP));
    adtTypeMap.put("DDLS", typeUtil.getType(IAdtObjectTypeConstants.DATA_DEFINITION));
    adtTypeMap.put("DDLX", typeUtil.getType(IAdtObjectTypeConstants.METADATA_EXTENSION));
    adtTypeMap.put("DCLS", typeUtil.getType(IAdtObjectTypeConstants.ACCESS_CONTROL));
    adtTypeMap.put("BDEF", typeUtil.getType(IAdtObjectTypeConstants.BEHAVIOR_DEFINITION));
    adtTypeMap.put("XSLT", typeUtil.getType(IAdtObjectTypeConstants.SIMPLE_TRANSFORMATION));
    adtTypeMap.put("FUGR", typeUtil.getType(IAdtObjectTypeConstants.FUNCTION_GROUP));
  }
}
