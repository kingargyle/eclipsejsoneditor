package json.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;

public class FileToDocUtility {
	
	public static IDocument getDocument(String location) {
		
		try {
			//String string = IOUtils.toString(getResourceAsStream(location));
			String string = FileUtils.readFileToString(new File(location));
			return new Document(string);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
