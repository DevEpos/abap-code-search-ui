package com.devepos.adt.cst.ui.internal.codesearch;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.IEditorMatchAdapter;
import org.eclipse.search.ui.text.Match;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;

import com.devepos.adt.base.ui.tree.ITreeNode;
import com.sap.adt.communication.content.AdtContentHandlingFactory;
import com.sap.adt.communication.content.IContentHandlingFactory;
import com.sap.adt.communication.content.plaintext.IPlainTextFragmentHandler;
import com.sap.adt.communication.content.plaintext.IPlainTextPosition;
import com.sap.adt.ris.search.ui.IAdtRisTextSearchEditorAdapter;
import com.sap.adt.tools.core.ui.editors.IAdtFormEditor;
import com.sap.adt.tools.core.urimapping.AdtUriMappingServiceFactory;

/**
 * Class to check if a given match of a code search result is shown in a certain
 * editor
 *
 * @author Ludwig Stockbauer-Muhr
 */
@SuppressWarnings("restriction")
public class CodeSearchEditorMatcher implements IEditorMatchAdapter {

  private IProject project;
  private CodeSearchResult searchResult;

  public CodeSearchEditorMatcher(final CodeSearchResult searchResult, final IProject project) {
    this.project = project;
    this.searchResult = searchResult;
  }

  @Override
  public Match[] computeContainedMatches(final AbstractTextSearchResult result,
      final IEditorPart editor) {
    List<Match> matches = new ArrayList<>();
    IFile file = getFile(editor);
    if (file == null || !file.exists()) {
      return null;
    }

    AtomicReference<IDocument> atomicDocument = new AtomicReference<>();
    IDocument document = null;

    if (Display.getCurrent() == null) {
      Display.getDefault().syncExec(() -> {
        atomicDocument.set(getLoadedDocument(editor));
      });
      document = atomicDocument.get();
    } else {
      document = getLoadedDocument(editor);
    }
    if (document == null) {
      return null;
    }

    URI fileUri = AdtUriMappingServiceFactory.createUriMappingService().getAdtUri(file);
    if (fileUri == null) {
      return new Match[0];
    }
    Set<SearchMatchNode> matchNodesInFile = searchResult.getMatchNodesForFileUri(fileUri
        .toString());
    if (matchNodesInFile != null) {
      for (SearchMatchNode matchNode : matchNodesInFile) {
        Match match = getMatch(matchNode, document, fileUri.toString());
        if (match != null) {
          matches.add(match);
        }
      }
    }
    return matches.toArray(new Match[matches.size()]);
  }

  @Override
  public boolean isShownInEditor(final Match match, final IEditorPart editor) {
    boolean isShown = false;
    IFile file = getFile(editor);
    if (file != null && match.getElement() instanceof ITreeNode) {
      String uri = getUri((ITreeNode) match.getElement());
      URI fileUri = AdtUriMappingServiceFactory.createUriMappingService().getAdtUri(file);
      if (uri != null && fileUri != null) {
        isShown = uri.startsWith(fileUri.toString()) && project.equals(file.getProject());
      }
    }
    return isShown;
  }

  private IFile getFile(final IEditorPart editor) {
    IFile file = null;
    if (editor instanceof IAdtFormEditor) {
      IEditorInput ei = editor.getEditorInput();
      if (ei instanceof IFileEditorInput) {
        file = ((IFileEditorInput) ei).getFile();
      }
    }
    return file;
  }

  /**
   * Loads the document from the server and pauses further execution until the
   * editor is truly opened and available
   * Note: This logic is taken from
   * com.sap.adt.ris.search.ui.internal.textsearch.AdtRisEditorMatcher#getLoadedDocument
   */
  private IDocument getLoadedDocument(final IEditorPart editor) {
    IDocument document = null;
    if (editor instanceof IAdtFormEditor) {
      IAdtFormEditor adtFormEditor = (IAdtFormEditor) editor;
      if (adtFormEditor.getSelectedPage() instanceof AbstractDecoratedTextEditor) {
        AbstractDecoratedTextEditor page = (AbstractDecoratedTextEditor) adtFormEditor
            .getSelectedPage();
        IAdtRisTextSearchEditorAdapter textEditorAdapter = page
            .<IAdtRisTextSearchEditorAdapter>getAdapter(IAdtRisTextSearchEditorAdapter.class);
        if (textEditorAdapter != null) {
          textEditorAdapter.waitForSource();
        }
        document = page.getDocumentProvider().getDocument(editor.getEditorInput());
      } else {
        for (int i = 0; i < adtFormEditor.getPageCount(); i++) {
          IEditorPart ed = adtFormEditor.getEditor(i);
          if (ed instanceof AbstractDecoratedTextEditor) {
            AbstractDecoratedTextEditor page = (AbstractDecoratedTextEditor) ed;
            IAdtRisTextSearchEditorAdapter textEditorAdapter = ed
                .<IAdtRisTextSearchEditorAdapter>getAdapter(IAdtRisTextSearchEditorAdapter.class);
            if (textEditorAdapter != null) {
              textEditorAdapter.waitForSource();
            }
            document = page.getDocumentProvider().getDocument(page.getEditorInput());
          }
        }
      }
    }
    return document;
  }

  private Match getMatch(final SearchMatchNode matchNode, final IDocument document,
      final String fileUri) {
    if (fileUri == null /* || document == null */ || matchNode == null) {
      return null;
    }

    Match[] matches = searchResult.getMatches(matchNode);

    // each line should consist of exactly one match
    if (matches == null || matches.length != 1) {
      return null;
    }
    Match match = matches[0];

    // full length was already acquired
    if (match.getLength() != -1) {
      return match;
    }

    try {
      String fragment = new URI(matchNode.getUri()).getFragment();
      if (fragment == null) {
        return null;
      }
      IContentHandlingFactory factory = AdtContentHandlingFactory.getContentHandlingFactory();
      IPlainTextFragmentHandler plainTextFragmentHandler = factory.createPlainTextFragmentHandler();
      IPlainTextPosition position = plainTextFragmentHandler.parsePosition(fragment);
      if (position.getStartLine() <= 0) {
        return null;
      }

      int startLine = position.getStartLine() - 1;
      int endLine = position.getEndLine() - 1;

      match.setOffset(document.getLineOffset(startLine) + position.getStartLineOffset());
      int length = 0;

      if (startLine == endLine) {
        length = position.getEndLineOffset() - position.getStartLineOffset();
      } else {
        int currentLine = startLine;
        while (endLine >= currentLine) {
          int lineLength = document.getLineLength(currentLine);
          if (currentLine == startLine) {
            lineLength -= position.getStartLineOffset();
          }
          if (currentLine == endLine) {
            length += position.getEndLineOffset();
          } else {
            length += lineLength;
          }
          currentLine++;
        }
      }
      match.setLength(length);

      return match;
    } catch (Exception exception) {
    }
    return null;
  }

  private String getUri(final ITreeNode node) {
    String uri = null;
    if (node instanceof SearchMatchNode) {
      uri = ((SearchMatchNode) node).getUri();
    }
    return uri;
  }
}
