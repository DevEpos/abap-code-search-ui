package com.devepos.adt.cst.ui.internal.codesearch;

/**
 * Search parameter which indicates what includes of a repository object
 * shall be searched.
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public class IncludeFlagsParameter {

  private int includeFlags;
  private IIncludeToSearch[] possibleIncludes;
  private boolean allIncludes = true;

  public IncludeFlagsParameter(final IIncludeToSearch[] possibleIncludes) {
    this.possibleIncludes = possibleIncludes;
  }

  /**
   * Returns the currently selected includes
   *
   * @return the currently selected includes
   */
  public int getIncludeFlags() {
    return includeFlags;
  }

  /**
   * Returns all the possible includes that can be searched
   *
   * @return all the possible includes that can be searched
   */
  public IIncludeToSearch[] getPossibleIncludes() {
    return possibleIncludes;
  }

  /**
   * Returns the count of the selected includes
   *
   * @return count of the selected includes
   */
  public int getSelectedIncludeCount() {
    int bitCount = 0;
    for (IIncludeToSearch incl : possibleIncludes) {
      if ((incl.getBit() & includeFlags) != 0) {
        bitCount++;
      }
    }
    return bitCount;
  }

  /**
   * Returns the {@link String} representation of the currently selected include flags
   *
   * @return {@link String} representation of the currently selected include flags
   */
  public String getUriParamValue() {
    return allIncludes ? IncludeFlagsUtil.ALL_INCLUDES_API_NAME
        : IncludeFlagsUtil.convertFlagsToString(includeFlags, possibleIncludes);
  }

  public boolean isAllIncludes() {
    return allIncludes;
  }

  public void setAllIncludes(final boolean allIncludes) {
    this.allIncludes = allIncludes;
  }

  public void setIncludeFlags(final int includeBits) {
    includeFlags = includeBits;
  }
}
