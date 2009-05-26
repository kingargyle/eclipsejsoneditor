/**
 * 
 */
package json.outline.elements;

import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

/**
 * @author garner_m
 *
 */
public class JsonBoolean extends JsonEntry {
	
	private static final String iconPath = "/icons/JsonBoolean.gif";
	
	/**
	 * @param parent
	 * @param key
	 * @param value
	 */
	public JsonBoolean(JsonParent parent, String key, String value) {
		super(parent, key, value);
	}

	/**
	 * @param parent
	 * @param key
	 */
	public JsonBoolean(JsonParent parent, String key) {
		super(parent, key);
	}
	
	@Override
	public Image getImage() {
		return this.createMyImage(iconPath);
	}

	@Override
	public String getForegroundColor() {
		return "BLUE";
	}

	@Override
	public StyledString getStyledString() {
		StyledString styledString = new StyledString();
		if (getKey() != null && !"".equals(getKey())) {
			StyledString.Styler style1 = StyledString.createColorRegistryStyler("GREEN", "WHITE");
			styledString.append(getKey(), style1);
			StyledString.Styler style2 = StyledString.createColorRegistryStyler("BLACK", "WHITE");
			styledString.append(" : ", style2);
		}
		StyledString.Styler style3 = StyledString.createColorRegistryStyler(this.getForegroundColor(), "WHITE");
		styledString.append(getValue(), style3);
		return styledString;
	}
}
