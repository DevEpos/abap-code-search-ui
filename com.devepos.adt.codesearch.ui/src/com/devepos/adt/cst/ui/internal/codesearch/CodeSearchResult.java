package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.IEditorMatchAdapter;
import org.eclipse.search.ui.text.IFileMatchAdapter;
import org.eclipse.search.ui.text.Match;

import com.devepos.adt.base.IAdtObjectTypeConstants;
import com.devepos.adt.base.adtobject.AdtObjectReferenceModelFactory;
import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseStrings;
import com.devepos.adt.base.ui.tree.AdtObjectReferenceNode;
import com.devepos.adt.base.ui.tree.FolderTreeNode;
import com.devepos.adt.base.ui.tree.IAdtObjectReferenceNode;
import com.devepos.adt.base.ui.tree.ICollectionTreeNode;
import com.devepos.adt.base.ui.tree.ITreeNode;
import com.devepos.adt.base.ui.tree.PackageNode;
import com.devepos.adt.base.util.StringUtil;
import com.devepos.adt.cst.model.codesearch.ICodeSearchMatch;
import com.devepos.adt.cst.model.codesearch.ICodeSearchObject;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.devepos.adt.cst.ui.internal.CodeSearchUIPlugin;
import com.devepos.adt.cst.ui.internal.IImages;
import com.sap.adt.tools.core.model.adtcore.IAdtMainObject;
import com.sap.adt.tools.core.model.adtcore.IAdtObjectReference;

public class CodeSearchResult extends AbstractTextSearchResult {

  private IEditorMatchAdapter editorMatchAdapter;
  private List<ITreeNode> flatResult = new ArrayList<>();
  private FileMatchesCache fileMatchesCache = new FileMatchesCache();
  private TreeBuilder treeBuilder;
  private CodeSearchQuery searchQuery;
  private ICollectionTreeNode searchResultRootNode;
  private int resultCount;

  public CodeSearchResult(final CodeSearchQuery searchQuery) {
    this.searchQuery = searchQuery;
  }

  private static class FileMatchesCache {
    private Map<String, Set<SearchMatchNode>> cache = new HashMap<>();

    public void addNode(final SearchMatchNode matchNode) {
      String fileUri = getFileUriFromNode(matchNode);
      Set<SearchMatchNode> matchNodesForUri = cache.get(fileUri);
      if (matchNodesForUri == null) {
        matchNodesForUri = new HashSet<>();
        cache.put(fileUri, matchNodesForUri);
      }
      matchNodesForUri.add(matchNode);
    }

    public void clear() {
      cache.clear();
    }

    public Set<SearchMatchNode> getNodes(final String fileUri) {
      return cache.get(fileUri);
    }

    public void removeNode(final SearchMatchNode matchNode) {
      String fileUri = getFileUriFromNode(matchNode);
      Set<SearchMatchNode> nodes = cache.get(fileUri);
      if (nodes != null) {
        nodes.remove(matchNode);
      }
    }

    private String getFileUriFromNode(final SearchMatchNode matchNode) {
      String fileUri = null;

      int fragmentPosition = matchNode.getUri().indexOf("#");
      if (fragmentPosition != -1) {
        fileUri = matchNode.getUri().substring(0, fragmentPosition);
      } else {
        // somehow we have a full file URI in the match???
        fileUri = matchNode.getUri();
      }
      return fileUri;
    }
  }

  private static class TreeBuilder {

    private FolderTreeNode rootNode;
    private String destinationId;
    private Map<String, IAdtObjectReferenceNode> urisToNodes = new HashMap<>();
    private List<String> urisInCorrectTreeOrder;
    private List<ITreeNode> flatResult;
    private FileMatchesCache fileMatchesCache;

    public TreeBuilder(final FileMatchesCache fileMatchesCache, final String destinationId) {
      this.fileMatchesCache = fileMatchesCache;
      rootNode = new FolderTreeNode(null, null, null, null);
      this.destinationId = destinationId;
    }

    public void addResultToTree(final ICodeSearchResult searchResult) {
      flatResult = new ArrayList<>();
      urisInCorrectTreeOrder = new ArrayList<>();
      urisToNodes = new HashMap<>();
      createTreeNodes(searchResult);
      connectTreeNodes();
    }

    public List<ITreeNode> getFlatResult() {
      return flatResult;
    }

    public ICollectionTreeNode getRootNode() {
      return rootNode;
    }

    /*
     * Create package node from main object and add it to the node map
     */
    private ITreeNode addPackageNode(final String destinationId,
        final Map<String, IAdtObjectReferenceNode> urisToNodes,
        final ICodeSearchObject searchObject, final IAdtMainObject mainObject) {
      ITreeNode newNode;
      IAdtObjectReferenceNode packageNode = new PackageNode(mainObject.getName(), null,
          createObjectRef(destinationId, mainObject, searchObject));
      urisToNodes.put(searchObject.getUri(), packageNode);
      newNode = packageNode;
      return newNode;
    }

    private void addSearchMatchNodes(final ICodeSearchObject searchObject,
        final IAdtObjectReferenceNode objectNode) {
      if (!searchObject.getMatches().isEmpty()) {
        for (ICodeSearchMatch match : searchObject.getMatches()) {
          SearchMatchNode matchNode = new SearchMatchNode(match.getSnippet(), match.getSnippet(),
              match.getUri(), objectNode);
          objectNode.addChild(matchNode);
          flatResult.add(matchNode);
          fileMatchesCache.addNode(matchNode);
        }
      }
    }

    /*
     * add child nodes to appropriate parent nodes
     */
    private void connectTreeNodes() {
      for (String nodeUri : urisInCorrectTreeOrder) {
        IAdtObjectReferenceNode adtObjRefNode = urisToNodes.get(nodeUri);
        IAdtObjectReference objectRefOfNode = adtObjRefNode.getObjectReference();

        if (objectRefOfNode.getParentUri() != null) {
          IAdtObjectReferenceNode parentNode = urisToNodes.get(objectRefOfNode.getParentUri());

          // TODO: handle $TMP package - create a user specific $TMP package node
          // depending on the value of 'responsible' of the main object
          if (parentNode == null) {
            throw new IllegalStateException(
                "Inconsistent data in text search result: parent uri can not be resolved: "
                    + objectRefOfNode.getParentUri());
          }
          parentNode.addChild(adtObjRefNode);
        }
      }

      urisInCorrectTreeOrder.clear();
    }

    private IAdtObjectReferenceNode createAdtObjectRefNode(final String destinationId,
        final ICodeSearchObject searchObject, final IAdtMainObject mainObject) {
      IAdtObjectReferenceNode objectNode = new AdtObjectReferenceNode(mainObject.getName(),
          mainObject.getName(), mainObject.getDescription(), createObjectRef(destinationId,
              mainObject, searchObject));
      return objectNode;
    }

    private IAdtObjectReference createObjectRef(final String destinationId,
        final IAdtMainObject adtMainObject, final ICodeSearchObject searchObject) {
      IAdtObjectReference adtObjectRef = AdtObjectReferenceModelFactory.createReference(
          destinationId, adtMainObject.getName(), adtMainObject.getType(), searchObject.getUri());
      adtObjectRef.setParentUri(searchObject.getParentUri());
      return adtObjectRef;
    }

    private void createTreeNodes(final ICodeSearchResult searchResult) {

      for (ICodeSearchObject searchObject : searchResult.getSearchObjects()) {
        IAdtMainObject mainObject = searchObject.getAdtMainObject();
        ITreeNode newNode = null;

        if (IAdtObjectTypeConstants.PACKAGE.equalsIgnoreCase(mainObject.getType())) {
          newNode = addPackageNode(destinationId, urisToNodes, searchObject, mainObject);
        } else {
          // non package types are all AdtObjectReference types
          IAdtObjectReferenceNode objectNode = createAdtObjectRefNode(destinationId, searchObject,
              mainObject);
          addSearchMatchNodes(searchObject, objectNode);
          urisToNodes.put(searchObject.getUri(), objectNode);
          urisInCorrectTreeOrder.add(searchObject.getUri());
          newNode = objectNode;
        }

        if (newNode != null && StringUtil.isEmpty(searchObject.getParentUri())) {
          rootNode.addChild(newNode);
        }
      }
    }

  }

  /**
   * Take query result and convert it into a flat and tree like result for the
   * search view
   */
  public void addResult(final ICodeSearchResult result) {
    if (result == null || result.getNumberOfResults() <= 0) {
      return;
    }

    resultCount += result.getNumberOfResults();

    if (treeBuilder == null) {
      treeBuilder = new TreeBuilder(fileMatchesCache, searchQuery.getProjectProvider()
          .getDestinationId());
    }
    if (searchResultRootNode == null) {
      searchResultRootNode = treeBuilder.getRootNode();
    }
    treeBuilder.addResultToTree(result);
    List<ITreeNode> currentFlatResult = treeBuilder.getFlatResult();
    if (currentFlatResult != null) {
      flatResult.addAll(currentFlatResult);
      for (ITreeNode matchNode : currentFlatResult) {
        addMatch(new Match(matchNode, -1, -1));
      }
    }
  }

  @Override
  public IEditorMatchAdapter getEditorMatchAdapter() {
    if (editorMatchAdapter == null) {
      editorMatchAdapter = new CodeSearchEditorMatcher(this, searchQuery.getProjectProvider()
          .getProject());
    }
    return editorMatchAdapter;
  }

  @Override
  public IFileMatchAdapter getFileMatchAdapter() {
    return null;
  }

  public List<ITreeNode> getFlatResult() {
    return flatResult;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return CodeSearchUIPlugin.getDefault().getImageDescriptor(IImages.CODE_SEARCH);
  }

  @Override
  public String getLabel() {
    if (searchQuery == null) {
      return "ABAP Code Search+ result";
    }
    String resultsLabel = null;
    boolean hasMoreResults = false;
    if (resultCount == 1) {
      resultsLabel = AdtBaseUIResources.getString(IAdtBaseStrings.SearchUI_OneResult_xmsg);
    } else if (resultCount > 1) {
      if (hasMoreResults) {
        resultsLabel = AdtBaseUIResources.format(IAdtBaseStrings.SearchUI_ResultsExceedMaximum_xmsg,
            searchQuery.getQuerySpecification().getMaxResults());
      } else {
        resultsLabel = AdtBaseUIResources.format(IAdtBaseStrings.SearchUI_SpecificResults_xmsg,
            resultCount);
      }
    } else {
      resultsLabel = AdtBaseUIResources.getString(IAdtBaseStrings.SearchUI_NoResults_xmsg);
    }
    return String.format("%s %s - %s", "Code Matches for", searchQuery.getQuerySpecification(),
        resultsLabel);
  }

  public Set<SearchMatchNode> getMatchNodesForFileUri(final String fileUri) {
    return fileMatchesCache.getNodes(fileUri);
  }

  @Override
  public ISearchQuery getQuery() {
    return searchQuery;
  }

  public ICollectionTreeNode getResultTree() {
    return searchResultRootNode;
  }

  @Override
  public String getTooltip() {
    return searchQuery != null ? searchQuery.getQuerySpecification().getQuery(false) : null;
  }

  @Override
  public void removeAll() {
    if (flatResult != null) {
      flatResult.clear();
    }
    resultCount = 0;
    fileMatchesCache.clear();
    if (searchResultRootNode != null) {
      searchResultRootNode.removeAllChildren();
    }
    searchResultRootNode = null;
    super.removeAll();
  }

  public void removeSearchResult(final ITreeNode match) {
    if (flatResult != null) {
      flatResult.remove(match);
    }
    if (match instanceof SearchMatchNode) {
      fileMatchesCache.removeNode((SearchMatchNode) match);
    }
    ICollectionTreeNode parent = match.getParent();
    if (parent != null) {
      parent.removeChild(match);
    }
  }
}
