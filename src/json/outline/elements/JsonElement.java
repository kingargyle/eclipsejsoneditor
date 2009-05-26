package json.outline.elements;

import java.util.Collections;
import java.util.List;

import json.JsonEditorPlugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public abstract class JsonElement {
	
	static List<JsonElement> NO_CHILDREN = Collections.emptyList();
	
	private JsonParent parent;
	
	private int start;
	
	private int length;
	
	public JsonElement(JsonParent parent) {
		this.parent = parent;
	}
	
	public JsonParent getParent() {
		return parent;
	}
	
	public abstract List<JsonElement> getChildren();
	
	public abstract void removeFromParent();
	
	public abstract Image getImage();
	
	protected Image createMyImage(String urlPath) {
		ImageDescriptor imgDescriptor = null;
		imgDescriptor = JsonEditorPlugin.imageDescriptorFromPlugin(JsonEditorPlugin.getDefault().getBundle().getSymbolicName(), urlPath);
		return imgDescriptor.createImage();
	}
	
	@Deprecated
	public abstract String getForegroundColor();
	
	public abstract StyledString getStyledString();

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void setPosition(int start, int length) {
		this.start = start;
		this.length = length;
	}
}
