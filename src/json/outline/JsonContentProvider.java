/**
 * 
 */
package json.outline;

import json.outline.elements.JsonElement;
import json.outline.elements.JsonObject;
import json.outline.elements.JsonParent;

import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.texteditor.IDocumentProvider;

/**
 * JsonContentProvider provides the Tree Structure for the outline view.
 * 
 * @author Matt Garner
 *
 */
public class JsonContentProvider implements ITreeContentProvider {
	
	protected IDocumentProvider fDocumentProvider;
	protected Object fInput;
	protected JsonObject rootObject;
	protected final static String JSON_ELEMENTS = "__json_elements"; //$NON-NLS-1$
	protected IPositionUpdater fPositionUpdater= new DefaultPositionUpdater(JSON_ELEMENTS);
	
	protected void parse(IDocument document) {
		rootObject = new JsonTextOutlineParser(document).parse();
	}
	
	public JsonContentProvider(IDocumentProvider documentProvider) {
		super();
		fDocumentProvider = documentProvider;
	}
	
	public void setInput(Object input) {
		this.fInput = input;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement == fInput) {
			return (rootObject != null) ? new Object[]{ rootObject } : new Object[0];
		}
		if (parentElement instanceof JsonElement) {
			return ((JsonElement) parentElement).getChildren().toArray();
		}
		return new Object[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		if (element == rootObject)
			return fInput;
		if (element instanceof JsonElement) {
			return ((JsonElement) element).getParent();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		if (element == fInput) {
			return (rootObject != null) ? true : false;
		}
		if (element instanceof JsonParent) {
			return ((JsonParent) element).hasChildren();
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement == fInput) {
			return (rootObject != null) ? new Object[]{ rootObject } : new Object[0];
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
		if (rootObject != null) {
			rootObject = null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		System.out.println("Input changed");
		if (oldInput != null) {
			IDocument document= fDocumentProvider.getDocument(oldInput);
			if (document != null) {
				try {
					document.removePositionCategory(JSON_ELEMENTS);
				} catch (BadPositionCategoryException x) {
				}
				document.removePositionUpdater(fPositionUpdater);
			}
		}
		
		rootObject = null;
		
		if (newInput != null) {
			IDocument document = fDocumentProvider.getDocument(newInput);
			if (document != null) {
				document.addPositionCategory(JSON_ELEMENTS);
				document.addPositionUpdater(fPositionUpdater);

				parse(document);
			}
		}
	}
	
	/**
	 * Finds the element in the tree that is closest to the required text
	 * position.
	 * 
	 * @param start
	 * @param length
	 * @return
	 */
	public JsonElement findNearestElement(int start, int length) {
		
		if (rootObject == null) {
			return null;
		}
		
		return findNearestElement(rootObject, start, length);
	}
	
	/**
	 * Recursive search to find the nearest element in the tree.
	 * 
	 * @param parent
	 * @param start
	 * @param length
	 * @return
	 */
	private JsonElement findNearestElement(JsonElement parent, int start, int length) {
		
		JsonElement previous = null;
		boolean found = false;
		
		if (parent.getChildren().size() == 0) {
			return parent;
		}
		
		for (JsonElement jsonElement : parent.getChildren()) {
			
			if (start < jsonElement.getStart()) {
				found = true;
				if (previous != null) {
					previous = findNearestElement(previous, start, length);
				} else {
					previous = parent;
				}
				break;
			}
			previous = jsonElement;
		}
		
		if(!found) {
			previous = findNearestElement(previous, start, length);
		}
		
		return previous;
	}
}
