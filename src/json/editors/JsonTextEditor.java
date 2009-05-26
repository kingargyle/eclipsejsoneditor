package json.editors;

import json.outline.JsonContentOutlinePage;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * JsonTextEditor is the TextEditor instance used by the plugin.
 * 
 * @author Matt Garner
 *
 */
public class JsonTextEditor extends TextEditor {
	
	private SourceViewerConfiguration viewerConfiguration;
	
	/** The outline page */
	private JsonContentOutlinePage fOutlinePage;
	
	public JsonTextEditor() {
		super();
		
	}
	
	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId("#JsonTextEditorContext"); //$NON-NLS-1$
		setRulerContextMenuId("#JsonTextRulerContext"); //$NON-NLS-1$
		viewerConfiguration = new JsonSourceViewerConfiguration();
		setSourceViewerConfiguration(viewerConfiguration);
	}
	
	public void dispose() {
		if (fOutlinePage != null)
			fOutlinePage.setInput(null);
		super.dispose();
	}
	
	public void doRevertToSaved() {
		super.doRevertToSaved();
		if (fOutlinePage != null)
			fOutlinePage.update();
	}
	
	public void doSave(IProgressMonitor monitor) {
		super.doSave(monitor);
		if (fOutlinePage != null)
			fOutlinePage.update();
	}
	
	/** The <code>JavaEditor</code> implementation of this 
	 * <code>AbstractTextEditor</code> method performs any extra 
	 * save as behavior required by the java editor.
	 */
	public void doSaveAs() {
		super.doSaveAs();
		if (fOutlinePage != null)
			fOutlinePage.update();
	}
	
	/** The <code>JavaEditor</code> implementation of this 
	 * <code>AbstractTextEditor</code> method performs sets the 
	 * input of the outline page after AbstractTextEditor has set input.
	 * 
	 * @param input the editor input
	 * @throws CoreException in case the input can not be set
	 */ 
	public void doSetInput(IEditorInput input) throws CoreException {
		super.doSetInput(input);
		if (fOutlinePage != null)
			fOutlinePage.setInput(input);
	}
	
	/** The <code>JavaEditor</code> implementation of this 
	 * <code>AbstractTextEditor</code> method performs gets
	 * the java content outline page if request is for a an 
	 * outline page.
	 * 
	 * @param required the required type
	 * @return an adapter for the required type or <code>null</code>
	 */ 
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class required) {
		if (IContentOutlinePage.class.equals(required)) {
			if (fOutlinePage == null) {
				fOutlinePage= new JsonContentOutlinePage(getDocumentProvider(), this);
				if (getEditorInput() != null)
					fOutlinePage.setInput(getEditorInput());
			}
			return fOutlinePage;
		}
		
		return super.getAdapter(required);
	}
}
