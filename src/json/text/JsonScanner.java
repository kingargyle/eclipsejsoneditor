/**
 * 
 */
package json.text;

import java.util.LinkedList;
import java.util.List;

import json.util.JsonColorProvider;
import json.util.JsonWhitespaceDetector;
import json.util.JsonWordDetector;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

/**
 * JsonScanner is used to scan the json text and apply coloring.
 * 
 * @author Matt Garner
 *
 */
public class JsonScanner extends RuleBasedScanner {

	public JsonScanner(JsonColorProvider provider) {
		super();
		
		IToken string = new Token(new TextAttribute(provider.getColor(JsonColorProvider.STRING)));
		IToken value = new Token(new TextAttribute(provider.getColor(JsonColorProvider.VALUE)));
		IToken defaultText = new Token(new TextAttribute(provider.getColor(JsonColorProvider.DEFAULT)));
		IToken nullValue = new Token(new TextAttribute(provider.getColor(JsonColorProvider.NULL)));
		
		List<IRule> rules= new LinkedList<IRule>();
		
		rules.add(new SingleLineRule(":\"", "\"", value, '\\'));
		rules.add(new SingleLineRule("\"", "\"", string, '\\')); //$NON-NLS-2$ //$NON-NLS-1$
		WordRule wordRule= new WordRule(new JsonWordDetector(), defaultText);
		wordRule.addWord("null", nullValue);
		rules.add(wordRule);
		rules.add(new WhitespaceRule(new JsonWhitespaceDetector()));
		
		IRule[] result= new IRule[rules.size()];
		rules.toArray(result);
		setRules(result);
	}
}
