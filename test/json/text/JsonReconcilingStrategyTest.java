/**
 * 
 */
package json.text;

import json.util.FileToDocUtility;

import org.eclipse.jface.text.IDocument;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Matt Garner
 *
 */
public class JsonReconcilingStrategyTest {
	
	@Before
	public void onSetup() {
		
	}
	
	
	public void tearDown() {
		
	}
	
	@Test
	public void testFile1() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test1.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		Assert.assertEquals(2, jtop.fPositions.size());
	}
	
	@Test
	public void testFile2() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test2.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		Assert.assertEquals(6, jtop.fPositions.size());
	}
	
	@Test
	public void testFile3() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test3.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		Assert.assertEquals(7, jtop.fPositions.size());
	}
	
	@Test
	public void testFile4() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test4.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		Assert.assertEquals(7, jtop.fPositions.size());
	}
	
	@Test
	public void testFile5() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test5.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		Assert.assertEquals(1, jtop.fPositions.size());
	}
	
	@Test
	public void testFile6() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test6.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		Assert.assertEquals(1, jtop.fPositions.size());
	}
	
	@Test
	public void testFile7() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test7.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		Assert.assertEquals(1, jtop.fPositions.size());
	}
	
	@Test
	public void testFile8() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test8.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		Assert.assertEquals(1, jtop.fPositions.size());
	}
	
	@Test
	public void testFile9() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test9.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		Assert.assertEquals(2, jtop.fPositions.size());
	}
}
