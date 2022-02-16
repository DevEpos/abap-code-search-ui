package com.devepos.adt.cst.ui.internal.codesearch;

import org.eclipse.swt.graphics.Image;

import com.devepos.adt.base.nameditem.INamedItemType;
import com.devepos.adt.base.ui.AdtBaseUIResources;
import com.devepos.adt.base.ui.IAdtBaseImages;
import com.devepos.adt.base.ui.IImageProvider;

/**
 * Named Items in the context of the Code Search
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
public enum NamedItem implements INamedItemType, IImageProvider {
  APPLICATION_COMPONENT("applcomp", AdtBaseUIResources.getImage(
      IAdtBaseImages.APPLICATION_COMPONENT)),
  SERVER_GROUP("servergroup", null, true, true);

  private String discoveryTerm;
  private boolean caseSensitive;
  private boolean isBuffered;
  private Image image;

  NamedItem(final String discoveryTerm, final Image image) {
    this(discoveryTerm, image, false, false);
  }

  NamedItem(final String discoveryTerm, final Image image, final boolean caseSensitive,
      final boolean isBuffered) {
    this.discoveryTerm = discoveryTerm;
    this.caseSensitive = caseSensitive;
    this.isBuffered = isBuffered;
    this.image = image;
  }

  @Override
  public String getDiscoveryTerm() {
    return discoveryTerm;
  }

  @Override
  public boolean isCaseSensitive() {
    return caseSensitive;
  }

  @Override
  public boolean isBuffered() {
    return isBuffered;
  }

  @Override
  public Image getImage() {
    return image;
  }

}
