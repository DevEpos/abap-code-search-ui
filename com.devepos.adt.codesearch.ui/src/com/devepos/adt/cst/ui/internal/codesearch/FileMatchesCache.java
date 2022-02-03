package com.devepos.adt.cst.ui.internal.codesearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Cache to hold a set of matches for a given file URI
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
class FileMatchesCache {
  private Map<String, Set<SearchMatchNode>> cache = new HashMap<>();

  /**
   * Adds the given match node to the cache
   *
   * @param matchNode the match node to add to the cache
   */
  public void addNode(final SearchMatchNode matchNode) {
    String fileUri = getFileUriFromNode(matchNode);
    Set<SearchMatchNode> matchNodesForUri = cache.get(fileUri);
    if (matchNodesForUri == null) {
      matchNodesForUri = new HashSet<>();
      cache.put(fileUri, matchNodesForUri);
    }
    matchNodesForUri.add(matchNode);
  }

  /**
   * Clears the cache
   */
  public void clear() {
    cache.clear();
  }

  /**
   * Retrieves a set of match nodes the given URI of a file
   *
   * @param fileUri URI of a file
   * @return set of match nodes the given URI of a file
   */
  public Set<SearchMatchNode> getNodes(final String fileUri) {
    return cache.get(fileUri);
  }

  /**
   * Removes the given match node from the cache
   *
   * @param matchNode the match node to remove
   */
  public void removeNode(final SearchMatchNode matchNode) {
    String fileUri = getFileUriFromNode(matchNode);
    Set<SearchMatchNode> nodes = cache.get(fileUri);
    if (nodes != null) {
      nodes.remove(matchNode);
    }
  }

  /**
   * Retrieves the file URI associated with the given match node
   *
   * @param matchNode a match node
   * @return file URI associated with the given match node
   */
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