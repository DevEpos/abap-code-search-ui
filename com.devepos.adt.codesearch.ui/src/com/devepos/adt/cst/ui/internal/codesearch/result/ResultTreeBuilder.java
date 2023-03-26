package com.devepos.adt.cst.ui.internal.codesearch.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devepos.adt.base.IAdtObjectTypeConstants;
import com.devepos.adt.base.adtobject.AdtObjectReferenceModelFactory;
import com.devepos.adt.base.ui.tree.FolderTreeNode;
import com.devepos.adt.base.ui.tree.IAdtObjectReferenceNode;
import com.devepos.adt.base.ui.tree.ICollectionTreeNode;
import com.devepos.adt.base.ui.tree.ITreeNode;
import com.devepos.adt.base.ui.tree.PackageNode;
import com.devepos.adt.base.ui.tree.launchable.LaunchableAdtObjectReferenceNode;
import com.devepos.adt.base.ui.tree.launchable.LaunchablePackageNode;
import com.devepos.adt.base.util.StringUtil;
import com.devepos.adt.cst.model.codesearch.ICodeSearchMatch;
import com.devepos.adt.cst.model.codesearch.ICodeSearchObject;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;
import com.sap.adt.tools.core.model.adtcore.IAdtMainObject;
import com.sap.adt.tools.core.model.adtcore.IAdtObjectReference;

/**
 * Handles creating the result tree for the code search
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
class ResultTreeBuilder {
  private FolderTreeNode rootNode;
  private String destinationId;
  private Map<String, IAdtObjectReferenceNode> urisToNodes = new HashMap<>();
  private List<String> urisInCorrectTreeOrder;
  private List<PackageNode> newPackagesToAddToTree;
  private List<ITreeNode> flatResult;
  private List<ITreeNode> nodesWithMatches;
  private Map<String, PackageNode> packageNodeCache = new HashMap<>();
  private FileMatchesCache fileMatchesCache;

  /**
   * Creates new result tree instance
   *
   * @param fileMatchesCache cache to handle track matches to a given file URI
   * @param destinationId    destination id which was used to create the search result
   */
  public ResultTreeBuilder(final FileMatchesCache fileMatchesCache, final String destinationId) {
    this.fileMatchesCache = fileMatchesCache;
    rootNode = new FolderTreeNode(null, null, null, null);
    this.destinationId = destinationId;
  }

  /**
   * Converts the given <code>searchResult</code> to a tree compatible data structure
   *
   * @param searchResult result object from the code search
   */
  public void addResultToTree(final ICodeSearchResult searchResult) {
    flatResult = new ArrayList<>();
    urisInCorrectTreeOrder = new ArrayList<>();
    urisToNodes = new HashMap<>();
    nodesWithMatches = new ArrayList<>();
    newPackagesToAddToTree = new ArrayList<>();

    createTreeNodes(searchResult);
    connectPackageNodes();
    connectTreeNodes();
    propagateMatchCountsToRoot();
  }

  /**
   * Clears the buffer lists/maps of the last
   */
  public void clearBuffersOfLastResult() {
    if (flatResult != null) {
      flatResult.clear();
    }
    if (urisInCorrectTreeOrder != null) {
      urisInCorrectTreeOrder.clear();
    }
    if (urisToNodes != null) {
      urisToNodes.clear();
    }
    if (nodesWithMatches != null) {
      nodesWithMatches.clear();
    }
    if (newPackagesToAddToTree != null) {
      newPackagesToAddToTree.clear();
    }
  }

  public void clearPackageNodeCache() {
    packageNodeCache.clear();
  }

  public List<ITreeNode> getFlatResult() {
    return flatResult;
  }

  public ICollectionTreeNode getRootNode() {
    return rootNode;
  }

  public void removePackageNode(final PackageNode child) {
    packageNodeCache.remove(child.getObjectReference().getUri());
  }

  /*
   * Create package node from main object and add it to the node map
   */
  private ITreeNode addPackageNode(final ICodeSearchObject searchObject,
      final IAdtMainObject mainObject) {

    // Test if the package node already exists in the tree
    String uri = searchObject.getUri();
    PackageNode packageNode = packageNodeCache.get(uri);
    if (packageNode == null) {
      packageNode = new LaunchablePackageNode(mainObject.getName(), null, createObjectRef(
          destinationId, mainObject, searchObject));
      packageNodeCache.put(uri, packageNode);
      newPackagesToAddToTree.add(packageNode);
      urisToNodes.put(uri, packageNode);
      return packageNode;
    }
    urisToNodes.put(uri, packageNode);
    return null;
  }

  private void addSearchMatchNodes(final ICodeSearchObject searchObject,
      final IAdtObjectReferenceNode objectNode) {

    if (!searchObject.getMatches().isEmpty()) {
      for (ICodeSearchMatch match : searchObject.getMatches()) {
        SearchMatchNode matchNode = new SearchMatchNode(match, objectNode);
        objectNode.addChild(matchNode);
        flatResult.add(matchNode);
        fileMatchesCache.addNode(matchNode);
      }

      objectNode.setNodeValue(searchObject.getMatches().size());
      nodesWithMatches.add(objectNode);
    }
  }

  private void connectPackageNodes() {
    for (PackageNode packageNode : newPackagesToAddToTree) {
      IAdtObjectReferenceNode adtObjRefNode = urisToNodes.get(packageNode.getObjectReference()
          .getUri());
      IAdtObjectReference objectRefOfNode = adtObjRefNode.getObjectReference();

      if (objectRefOfNode.getParentUri() != null) {
        IAdtObjectReferenceNode parentNode = packageNodeCache.get(objectRefOfNode.getParentUri());

        if (parentNode == null) {
          throw new IllegalStateException("Inconsistent data in text search result: parent uri '"
              + objectRefOfNode.getParentUri() + "' can not be resolved");
        }
        parentNode.addChild(adtObjRefNode);
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

        if (parentNode == null) {
          throw new IllegalStateException("Inconsistent data in text search result: parent uri '"
              + objectRefOfNode.getParentUri() + "' can not be resolved");
        }
        parentNode.addChild(adtObjRefNode);
      }
    }
  }

  private IAdtObjectReferenceNode createAdtObjectRefNode(final String destinationId,
      final ICodeSearchObject searchObject, final IAdtMainObject mainObject) {
    IAdtObjectReferenceNode objectNode = new LaunchableAdtObjectReferenceNode(mainObject.getName(),
        mainObject.getName(), mainObject.getDescription(), createObjectRef(destinationId,
            mainObject, searchObject));
    return objectNode;
  }

  private IAdtObjectReference createObjectRef(final String destinationId,
      final IAdtMainObject adtMainObject, final ICodeSearchObject searchObject) {
    IAdtObjectReference adtObjectRef = AdtObjectReferenceModelFactory.createReference(destinationId,
        adtMainObject.getName(), adtMainObject.getType(), searchObject.getUri());
    adtObjectRef.setParentUri(searchObject.getParentUri());
    return adtObjectRef;
  }

  private void createTreeNodes(final ICodeSearchResult searchResult) {
    for (ICodeSearchObject searchObject : searchResult.getSearchObjects()) {
      IAdtMainObject mainObject = searchObject.getAdtMainObject();
      ITreeNode newNode = null;

      if (IAdtObjectTypeConstants.PACKAGE.equalsIgnoreCase(mainObject.getType())) {
        newNode = addPackageNode(searchObject, mainObject);
      } else {
        // non package types are all AdtObjectReference types
        IAdtObjectReferenceNode objectNode = createAdtObjectRefNode(destinationId, searchObject,
            mainObject);
        addSearchMatchNodes(searchObject, objectNode);
        String nodeUuid = null;
        if (IAdtObjectTypeConstants.FUNCTION_INCLUDE.equals(mainObject.getType())
            || IAdtObjectTypeConstants.PROGRAM_INCLUDE.equals(mainObject.getType())) {
          // double fail-safe if program include has matches in expanded include and resides under
          // package
          if (packageNodeCache.containsKey(searchObject.getParentUri())) {
            nodeUuid = searchObject.getUri();
          } else {
            nodeUuid = searchObject.getParentUri() + "::" + searchObject.getUri();
          }
        } else {
          nodeUuid = searchObject.getUri();
        }
        urisToNodes.put(nodeUuid, objectNode);
        urisInCorrectTreeOrder.add(nodeUuid);
        newNode = objectNode;

      }

      if (newNode != null && StringUtil.isEmpty(searchObject.getParentUri())) {
        rootNode.addChild(newNode);
      }
    }
  }

  private void propagateMatchCountsToRoot() {
    for (ITreeNode nodeWithMatches : nodesWithMatches) {
      propagateMatchCountToRoot(nodeWithMatches);
    }
  }

  private void propagateMatchCountToRoot(final ITreeNode node) {
    ICollectionTreeNode parentNode = node.getParent();
    while (parentNode != null && parentNode != rootNode) {
      Integer childMatchCount = (Integer) node.getNodeValue();
      if (childMatchCount == null) {
        return;
      }

      Integer parentMatchCount = (Integer) parentNode.getNodeValue();
      if (parentMatchCount == null) {
        parentNode.setNodeValue(childMatchCount);
      } else {
        parentNode.setNodeValue(parentMatchCount + childMatchCount);
      }

      parentNode = parentNode.getParent();
    }
  }
}