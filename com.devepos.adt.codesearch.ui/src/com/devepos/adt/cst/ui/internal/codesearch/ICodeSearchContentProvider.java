package com.devepos.adt.cst.ui.internal.codesearch;

import org.eclipse.jface.viewers.IStructuredContentProvider;

/**
 * Content provider for handling the the code search result
 *
 * @author Ludwig Stockbauer-Muhr
 *
 */
interface ICodeSearchContentProvider extends IStructuredContentProvider {
  void elementsChanged(Object[] updatedElements);
}