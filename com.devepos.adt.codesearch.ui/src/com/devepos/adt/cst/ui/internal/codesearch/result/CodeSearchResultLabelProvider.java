package com.devepos.adt.cst.ui.internal.codesearch.result;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IToolTipProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

import com.devepos.adt.base.IAdtObjectTypeConstants;
import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseImages;
import com.devepos.adt.base.ui.tree.IAdtObjectReferenceNode;
import com.devepos.adt.base.ui.tree.IStyledTreeNode;
import com.devepos.adt.base.ui.tree.ITreeNode;
import com.devepos.adt.base.ui.util.AdtTypeUtil;
import com.devepos.adt.cst.ui.internal.messages.Messages;

/**
 * Custom view label provider for the Result Tree
 *
 * @author Ludwig Stockbauer-Muhr
 */
class CodeSearchResultLabelProvider extends LabelProvider implements ILabelProvider,
    IStyledLabelProvider, IToolTipProvider {

  private final NumberFormat matchCountFormat = new DecimalFormat("###,###");

  @Override
  public Image getImage(final Object element) {
    Image image;
    final ITreeNode searchResult = (ITreeNode) element;
    image = searchResult.getImage();
    if (image == null && element instanceof IAdtObjectReferenceNode) {
      final IAdtObjectReferenceNode adtObjRefNode = (IAdtObjectReferenceNode) element;
      String adtType = adtObjRefNode.getObjectReference().getType();
      if (adtType.equals(IAdtObjectTypeConstants.CLASS_INCLUDE)) {
        return AdtBaseUIResources.getImage(IAdtBaseImages.NEUTRAL_METHOD);
      }
      if (IAdtObjectTypeConstants.DATA_DEFINITION.equals(adtType)) {
        adtType = IAdtObjectTypeConstants.CDS_VIEW;
      }
      image = AdtTypeUtil.getInstance().getTypeImage(adtType);
    }
    return image;
  }

  @Override
  public StyledString getStyledText(final Object element) {
    StyledString text = new StyledString();
    final ITreeNode searchResult = (ITreeNode) element;

    if (element instanceof IStyledTreeNode) {
      text = ((IStyledTreeNode) element).getStyledText();
      if (text == null) {
        text = new StyledString();
      }
    } else {
      if (element instanceof IAdtObjectReferenceNode) {
        text.append(searchResult.getDisplayName());
        String type = ((IAdtObjectReferenceNode) element).getAdtObjectType();
        if (IAdtObjectTypeConstants.DATA_DEFINITION.equals(type)) {
          type = IAdtObjectTypeConstants.CDS_VIEW;
        }
        String typeDescription = null;
        if (IAdtObjectTypeConstants.BUSINESS_OBJ_TYPE_PROGRAM.equals(type)) {
          typeDescription = Messages.CodeSearchResultLabelProvider_1;
        } else if (!IAdtObjectTypeConstants.CLASS_INCLUDE.equals(type)) {
          typeDescription = AdtTypeUtil.getInstance().getTypeDescription(type);
        }
        if (typeDescription != null) {
          text.append(" (" + typeDescription + ")", StyledString.QUALIFIER_STYLER);
        }
        Integer matchCount = (Integer) searchResult.getNodeValue();
        if (matchCount != null) {
          text.append(String.format(" %s %s", matchCountFormat.format(matchCount), matchCount > 1
              ? Messages.CodeSearchResultLabelProvider_multiMatchesSuffix_xmsg
              : Messages.CodeSearchResultLabelProvider_singleMatchSuffix_xmsg),
              StyledString.COUNTER_STYLER);
        }
      }
    }

    return text;
  }

  @Override
  public String getText(final Object element) {
    final ITreeNode searchResult = (ITreeNode) element;

    return searchResult.getName();
  }

  @Override
  public String getToolTipText(final Object element) {
    if (element instanceof SearchMatchNode) {
      final SearchMatchNode matchNode = (SearchMatchNode) element;
      return matchNode.getTooltip();
    }
    return null;
  }
}