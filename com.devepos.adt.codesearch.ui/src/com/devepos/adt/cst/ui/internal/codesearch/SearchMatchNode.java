package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseImages;
import com.devepos.adt.base.ui.tree.IStyledTreeNode;
import com.devepos.adt.base.ui.tree.ITreeNode;
import com.devepos.adt.base.ui.tree.TreeNodeBase;

/**
 * Tree node for a match from the code search
 *
 * @author Ludwig Stockbauer-Muhr
 */
public class SearchMatchNode extends TreeNodeBase implements IStyledTreeNode {
  private static final StyledString.Styler HIGHLIGHT_STYLER = StyledString
      .createColorRegistryStyler(null, "org.eclipse.search.ui.match.highlight");

  private String content;
  private String uri;

  public SearchMatchNode(final String snippetLine, final String match, final String uri,
      final ITreeNode parent) {
    super(snippetLine, snippetLine, "", parent);
    this.uri = uri;
    content = match;
  }

  public String getContent() {
    return content;
  }

  @Override
  public Image getImage() {
    return AdtBaseUIResources.getImage(IAdtBaseImages.SEARCH_MATCH);
  }

  @Override
  public StyledString getStyledText() {
    StyledString styledSnippet = new StyledString();
    String fragment = uri.substring(uri.indexOf("#"));
    if (fragment == null) {
      styledSnippet.append(content);
    } else {
      Pattern fragmentPattern = Pattern.compile("start=(\\d+),(\\d+);end=(\\d+),(\\d+)");
      Matcher fragmentMatcher = fragmentPattern.matcher(uri);
      if (fragmentMatcher.find()) {
        int startLine = Integer.valueOf(fragmentMatcher.group(1));
        int startOffset = Integer.valueOf(fragmentMatcher.group(2));
        int endLine = Integer.valueOf(fragmentMatcher.group(3));
        int endOffset = Integer.valueOf(fragmentMatcher.group(4));

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

  public String getUri() {
    return uri;
  }

}
