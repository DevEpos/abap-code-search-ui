package com.devepos.adt.cst.ui.internal.export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.StyledString;

import com.devepos.adt.base.ui.tree.IAdtObjectReferenceNode;
import com.devepos.adt.base.ui.tree.ICollectionTreeNode;
import com.devepos.adt.base.ui.tree.ITreeNode;
import com.devepos.adt.base.ui.tree.PackageNode;
import com.devepos.adt.base.ui.util.AdtTypeUtil;
import com.devepos.adt.cst.ui.internal.codesearch.result.IResultPropertyNameConstants;
import com.devepos.adt.cst.ui.internal.codesearch.result.SearchMatchNode;
import com.devepos.adt.cst.ui.internal.messages.Messages;
import com.sap.adt.communication.content.AdtContentHandlingFactory;
import com.sap.adt.communication.content.IContentHandlingFactory;
import com.sap.adt.communication.content.plaintext.IPlainTextFragmentHandler;
import com.sap.adt.communication.content.plaintext.IPlainTextPosition;
import com.sap.adt.tools.core.model.adtcore.IAdtObjectReference;

/**
 * Class to export Code Search Results
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class ResultExporter {
  public static final String COMMA_DELIMITER = ",";
  public static final String SEMICOLON_DELIMITER = ";";
  public static final String TAB_DELIMITER = "\t";

  private IPlainTextFragmentHandler plainTextFragmentHandler;
  private final ICollectionTreeNode rootResultNode;
  private final String separator;
  private final String fileName;
  private BufferedWriter bufferedWriter;
  private final String qualifier;
  private final boolean useQualifierWhenRequiredOnly;
  private final boolean generateHeaders;
  private final String projectId;
  private final String matchMarker;

  public ResultExporter(final String projectId, final ICollectionTreeNode rootResultNode,
      final String separator, final boolean generateHeaders, final String fileName,
      final String qualifier, final boolean useQualifierWhenRequiredOnly,
      final String matchMarker) {
    this.rootResultNode = rootResultNode;
    this.separator = separator;
    this.fileName = fileName;
    this.qualifier = qualifier;
    this.generateHeaders = generateHeaders;
    this.useQualifierWhenRequiredOnly = useQualifierWhenRequiredOnly;
    this.projectId = projectId;
    this.matchMarker = matchMarker;
  }

  public void run() throws IOException {
    IContentHandlingFactory factory = AdtContentHandlingFactory.getContentHandlingFactory();
    plainTextFragmentHandler = factory.createPlainTextFragmentHandler();

    bufferedWriter = new BufferedWriter(new FileWriter(fileName));

    // write header
    if (generateHeaders) {
      bufferedWriter.write(String.join(separator,
          Messages.ResultExporter_PackageHierarchyPathHeader_xtit,
          Messages.ResultExporter_SourcePackageHeader_xtit,
          Messages.ResultExporter_OwnerHeader_xtit, Messages.ResultExporter_ObjectTypeHeader_xtit,
          Messages.ResultExporter_NameHeader_xtit, Messages.ResultExporter_SubObjectTypeHeader_xtit,
          Messages.ResultExporter_SubObjectNameHeader_xtit,
          Messages.ResultExporter_LineNumberHeader_xtit,
          Messages.ResultExporter_SourceCodeExtractHeader_xtit,
          Messages.ResultExporter_LinkToMatchHeader_xtit));
      bufferedWriter.newLine();
    }

    writeResults(rootResultNode.getChildren(), "", ""); //$NON-NLS-1$ //$NON-NLS-2$
    bufferedWriter.close();
  }

  private String getCsvValue(String value) {
    value = value == null ? "" : value; //$NON-NLS-1$
    boolean useQualifier = false;
    if (useQualifierWhenRequiredOnly) {
      useQualifier = /* value.contains(qualifier) || */ value.contains(separator);
    } else {
      useQualifier = true;
    }

    return useQualifier ? qualifier + value.replaceAll(qualifier, qualifier + qualifier) + qualifier
        : value;
  }

  private String getMatchLink(final String uri) {
    return getCsvValue(String.format("=HYPERLINK(\"adt://%s%s\")", projectId, uri));
  }

  private String getSnippet(final StyledString snippetText, final IPlainTextPosition position) {
    if (matchMarker == null) {
      return getCsvValue(snippetText.getString());
    }
    var snippet = snippetText.getString();
    var styleRanges = snippetText.getStyleRanges();
    if (styleRanges.length == 1) {
      var styledMatch = styleRanges[0];
      snippet = snippet.substring(0, styledMatch.start)
          .concat(matchMarker)
          .concat(snippet.substring(styledMatch.start, styledMatch.start + styledMatch.length))
          .concat(matchMarker)
          .concat(snippet.substring(styledMatch.start + styledMatch.length));
    }
    return getCsvValue(snippet);
  }

  private void writeResult(final SearchMatchNode node, final String packageHierarchy,
      final String currentPackage) throws IOException {

    var adtTypeUtil = AdtTypeUtil.getInstance();
    var rowEntries = new ArrayList<String>();
    rowEntries.add(packageHierarchy);
    rowEntries.add(currentPackage);

    IAdtObjectReferenceNode objectNode = null;
    IAdtObjectReference subObject = null;

    var parentNode1 = node.getParent();
    var parentNode2 = parentNode1.getParent();

    if (parentNode2 instanceof PackageNode) {
      objectNode = (IAdtObjectReferenceNode) parentNode1;
    } else {
      // must be adt sub object reference node
      objectNode = (IAdtObjectReferenceNode) parentNode2;
      // must be adt object reference node
      subObject = ((IAdtObjectReferenceNode) parentNode1).getObjectReference();
    }

    IAdtObjectReference object = objectNode.getObjectReference();

    rowEntries.add(getCsvValue(objectNode.getPropertyValue(
        IResultPropertyNameConstants.OBJECT_RESPONSIBLE)));
    rowEntries.add(getCsvValue(adtTypeUtil.getTypeDescription(object.getType())));
    rowEntries.add(getCsvValue(object.getName()));

    rowEntries.add(getCsvValue(subObject != null ? adtTypeUtil.getTypeDescription(subObject
        .getType()) : "")); //$NON-NLS-1$
    rowEntries.add(getCsvValue(subObject != null ? subObject.getName() : "")); //$NON-NLS-1$

    try {
      String fragment = new URI(node.getUri()).getFragment();
      if (fragment == null) {
        return;
      }
      IPlainTextPosition position = plainTextFragmentHandler.parsePosition(fragment);
      if (position.getStartLine() <= 0) {
        return;
      }
      rowEntries.add(getCsvValue(String.valueOf(position.getStartLine())));
      rowEntries.add(getSnippet(node.getStyledText(), position));

      rowEntries.add(getMatchLink(node.getUri()));
      bufferedWriter.write(String.join(separator, rowEntries.toArray(String[]::new)));
      bufferedWriter.newLine();
    } catch (Exception exc) {
      exc.printStackTrace();
    }
  }

  private void writeResults(final List<ITreeNode> children, String packageHierarchy,
      final String currentPackage) throws IOException {

    for (var node : children) {
      if (node instanceof PackageNode) {
        var packageNode = (PackageNode) node;
        packageHierarchy = packageHierarchy.length() == 0 ? packageNode.getName()
            : packageHierarchy + " > " + packageNode.getName(); //$NON-NLS-1$
        writeResults(packageNode.getChildren(), packageHierarchy, packageNode.getName());
      } else if (node instanceof IAdtObjectReferenceNode) {
        // must have children
        writeResults(((IAdtObjectReferenceNode) node).getChildren(), packageHierarchy,
            currentPackage);
      } else if (node instanceof SearchMatchNode) {
        writeResult((SearchMatchNode) node, packageHierarchy, currentPackage);
      }
    }

  }
}
