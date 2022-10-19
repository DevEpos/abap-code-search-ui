package com.devepos.adt.cst.ui.internal.codesearch.result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseImages;
import com.devepos.adt.base.ui.tree.ICollectionTreeNode;
import com.devepos.adt.base.ui.tree.IStyledTreeNode;
import com.devepos.adt.base.ui.tree.TreeNodeBase;
import com.devepos.adt.cst.model.codesearch.ICodeSearchMatch;
import com.devepos.adt.cst.ui.internal.messages.Messages;

/**
 * Tree node for a match from the code search
 *
 * @author Ludwig Stockbauer-Muhr
 */
public class SearchMatchNode extends TreeNodeBase implements IStyledTreeNode {
  private static final StyledString.Styler HIGHLIGHT_STYLER = StyledString
      .createColorRegistryStyler(null, "org.eclipse.search.ui.match.highlight"); //$NON-NLS-1$

  private ICodeSearchMatch match;

  public SearchMatchNode(final ICodeSearchMatch match, final ICollectionTreeNode parent) {
    super(match.getSnippet(), match.getSnippet(), "", parent); //$NON-NLS-1$
    this.match = match;
  }

  @Override
  public Image getImage() {
    return AdtBaseUIResources.getImage(IAdtBaseImages.SEARCH_MATCH);
  }

  @Override
  public StyledString getStyledText() {
    String uri = match.getUri();
    String content = match.getSnippet();
    StyledString styledSnippet = new StyledString();
    String fragment = uri.substring(uri.indexOf("#")); //$NON-NLS-1$
    if (fragment == null) {
      styledSnippet.append(content);
    } else {
      Pattern fragmentPattern = Pattern.compile("start=(\\d+),(\\d+);end=(\\d+),(\\d+)"); //$NON-NLS-1$
      Matcher fragmentMatcher = fragmentPattern.matcher(uri);
      if (fragmentMatcher.find()) {
        int startLine = Integer.parseInt(fragmentMatcher.group(1));
        int startOffset = Integer.parseInt(fragmentMatcher.group(2));
        int endLine = Integer.parseInt(fragmentMatcher.group(3));
        int endOffset = Integer.parseInt(fragmentMatcher.group(4));

        styledSnippet.append(content.substring(0, startOffset));

        if (endLine > startLine) {
          styledSnippet.append(content.substring(startOffset), HIGHLIGHT_STYLER);
        } else {
          styledSnippet.append(content.substring(startOffset, endOffset), HIGHLIGHT_STYLER);
          styledSnippet.append(content.substring(endOffset));
        }
      } else {
        styledSnippet.append(content);
      }
    }
    return styledSnippet;
  }

  public String getTooltip() {
    String longSnippet = match.getLongSnippet();
    if (longSnippet == null) {
      return null;
    }
    return Messages.SearchMatchNode_MatchTooltipIntro_xtol + "\n\n" + match.getLongSnippet(); // $NON-NLS-2$
  }

  public String getUri() {
    return match.getUri();
  }

}
