package json.model;

import json.model.jsonnode.JsonNode;
import json.model.node.Node;
import json.util.FileToDocUtility;

import org.eclipse.jface.text.IDocument;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsonReconcilingStrategyTest {
	
	public static int[] failingTests = new int[] {5,6,7};
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
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(75, jtop.nodes.size());
		
		Assert.assertEquals(15, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile2() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test2.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(315, jtop.nodes.size());
		
		Assert.assertEquals(48, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile3() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test3.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(386, jtop.nodes.size());
		
		Assert.assertEquals(63, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile4() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test4.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(97, jtop.nodes.size());
		
		Assert.assertEquals(30, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile5() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test5.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(4, jtop.nodes.size());
		
		Assert.assertEquals(3, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile6() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test6.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(7, jtop.nodes.size());
		Assert.assertEquals(3, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile7() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test7.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(10, jtop.nodes.size());
		Assert.assertEquals(4, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile8() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test8.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(7, jtop.nodes.size());
		Assert.assertEquals(3, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile9() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test9.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(21, jtop.nodes.size());
		Assert.assertEquals(8, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile12() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test12.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(27, jtop.nodes.size());
		Assert.assertEquals(8, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile13() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test13.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(9, jtop.nodes.size());
		Assert.assertEquals(3, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile14() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test14.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(17, jtop.nodes.size());
		Assert.assertEquals(4, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile15() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test15.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(17, jtop.nodes.size());
		Assert.assertEquals(4, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile16() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test16.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(9, jtop.nodes.size());
		Assert.assertEquals(3, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile17() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test17.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(10, jtop.nodes.size());
		Assert.assertEquals(4, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile18() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test18.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(10, jtop.nodes.size());
		Assert.assertEquals(4, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile19() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test19.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(8, jtop.nodes.size());
		Assert.assertEquals(4, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile20() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test20.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(12, jtop.nodes.size());
		Assert.assertEquals(4, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile21() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test21.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(16, jtop.nodes.size());
		Assert.assertEquals(5, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile22() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test22.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(19, jtop.nodes.size());
		Assert.assertEquals(6, jtop.jsonNodes.size());
	}
	
		@Test
	public void testFile23() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test23.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(27, jtop.nodes.size());
		Assert.assertEquals(7, jtop.jsonNodes.size());

    }

	@Test
	public void testFile24() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test24.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(25, jtop.nodes.size());
		Assert.assertEquals(5, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile25() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test25.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(9, jtop.nodes.size());
		Assert.assertEquals(3, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile26() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test26.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(31, jtop.nodes.size());
		Assert.assertEquals(14, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile27() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test27.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(17, jtop.nodes.size());
		Assert.assertEquals(7, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile28() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test28.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(29, jtop.nodes.size());
		Assert.assertEquals(8, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile29() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test29.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(17, jtop.nodes.size());
		Assert.assertEquals(7, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile30() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test30.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(7, jtop.nodes.size());
		Assert.assertEquals(3, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile31() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test31.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(27, jtop.nodes.size());
		Assert.assertEquals(8, jtop.jsonNodes.size());
	}
	
	@Test
	public void testFile32() {
		
		IDocument doc = FileToDocUtility.getDocument("./bin/json/outline/files/test32.json");
		
		JsonReconcilingStrategy jtop = new JsonReconcilingStrategy();
		jtop.setDocument(doc);
		
		jtop.initialReconcile();
		
		for (Node node : jtop.nodes) {
			System.out.println(node);
		}
		
		for (JsonNode node : jtop.jsonNodes) {
			System.out.println(node);
		}
		
		Assert.assertEquals(27, jtop.nodes.size());
		Assert.assertEquals(8, jtop.jsonNodes.size());
	}
}
