package json.outline;

import json.outline.elements.JsonObject;
import json.outline.elements.JsonElement;
import json.util.FileToDocUtility;

import org.eclipse.jface.text.IDocument;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsonTextOutlineParserTest {
	
	public final static String JSON_ELEMENTS = "__json_elements"; //$NON-NLS-1$
	
	@Before
	public void onSetup() {
		
	}
	
	
	public void tearDown() {
		
	}
	
	@Test
	public void testFile1() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test1.json");
		doc.addPositionCategory(JSON_ELEMENTS);
		JsonTextOutlineParser jtop = new JsonTextOutlineParser(doc);
		
		JsonObject top = jtop.parse();
		
		Assert.assertEquals(4, top.getChildren().size());
		
		JsonElement version = top.getChildren().get(0);
		
		Assert.assertEquals(8, version.getChildren().size());
	}
	
	@Test
	public void testFile2() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test2.json");
		doc.addPositionCategory(JSON_ELEMENTS);
		JsonTextOutlineParser jtop = new JsonTextOutlineParser(doc);
		
		JsonObject top = jtop.parse();
		
		Assert.assertEquals(1, top.getChildren().size());
		
		JsonElement response = top.getChildren().get(0);
		
		Assert.assertEquals(13, response.getChildren().size());
		
		JsonElement representations = response.getChildren().get(3);
		
		Assert.assertEquals(15, representations.getChildren().size());
		
		JsonElement exif = response.getChildren().get(4).getChildren().get(0);
		
		Assert.assertEquals(7, exif.getChildren().size());
		
		JsonElement comp = exif.getChildren().get(5);
		
		Assert.assertEquals(4, comp.getChildren().size());
		
		
	}
	

	@Test
	public void testFile3() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test3.json");
		doc.addPositionCategory(JSON_ELEMENTS);
		JsonTextOutlineParser jtop = new JsonTextOutlineParser(doc);
		
		JsonObject top = jtop.parse();
		
		Assert.assertEquals(3, top.getChildren().size());
		
		JsonElement response = top.getChildren().get(2);
		
		Assert.assertEquals(1, response.getChildren().size());
		
		JsonElement representations = response.getChildren().get(0);
		
		Assert.assertEquals(2, representations.getChildren().size());
		
		JsonElement shares = representations.getChildren().get(0).getChildren().get(29);
		
		Assert.assertEquals(1, shares.getChildren().size());
		
		JsonElement array1 = shares.getChildren().get(0);
		
		Assert.assertEquals(17, array1.getChildren().size());
		
		JsonElement entries = representations.getChildren().get(0).getChildren().get(30);
		
		Assert.assertEquals(0, entries.getChildren().size());
		
	}
	
	@Test
	public void testFile4() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test4.json");
		doc.addPositionCategory(JSON_ELEMENTS);
		JsonTextOutlineParser jtop = new JsonTextOutlineParser(doc);
		
		JsonObject top = jtop.parse();
		
		Assert.assertEquals(3, top.getChildren().size());
		
		JsonElement response = top.getChildren().get(2);
		
		Assert.assertEquals(1, response.getChildren().size());
		
		JsonElement representations = response.getChildren().get(0);
		
		Assert.assertEquals(2, representations.getChildren().size());
		
		JsonElement meta = representations.getChildren().get(0);
		
		Assert.assertEquals(4, meta.getChildren().size());
		
		JsonElement entries = meta.getChildren().get(1);
		
		Assert.assertEquals(4, entries.getChildren().size());
		
		JsonElement entries2 = meta.getChildren().get(2);
		
		Assert.assertEquals(4, entries2.getChildren().size());
		
		JsonElement entries3 = meta.getChildren().get(3);
		
		Assert.assertEquals(4, entries3.getChildren().size());
		
	}
	
	@Test
	public void testFile5() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test5.json");
		doc.addPositionCategory(JSON_ELEMENTS);
		JsonTextOutlineParser jtop = new JsonTextOutlineParser(doc);
		
		JsonObject top = jtop.parse();
		
		Assert.assertEquals(1, top.getChildren().size());
		
	}
	
	@Test
	public void testFile6() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test6.json");
		doc.addPositionCategory(JSON_ELEMENTS);
		JsonTextOutlineParser jtop = new JsonTextOutlineParser(doc);
		
		JsonObject top = jtop.parse();
		
		Assert.assertEquals(2, top.getChildren().size());
		
	}
	
	@Test
	public void testFile7() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test7.json");
		doc.addPositionCategory(JSON_ELEMENTS);
		JsonTextOutlineParser jtop = new JsonTextOutlineParser(doc);
		
		JsonObject top = jtop.parse();
		
		Assert.assertEquals(2, top.getChildren().size());
		
	}
	
	@Test
	public void testFile8() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test8.json");
		doc.addPositionCategory(JSON_ELEMENTS);
		JsonTextOutlineParser jtop = new JsonTextOutlineParser(doc);
		
		JsonObject top = jtop.parse();
		
		Assert.assertEquals(1, top.getChildren().size());
		
	}
}
