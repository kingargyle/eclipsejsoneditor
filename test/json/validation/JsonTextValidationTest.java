/**
 * 
 */
package json.validation;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Matt Garner
 *
 */
public class JsonTextValidationTest {
	
	@Before
	public void onSetup() {
		
	}
	
	
	public void tearDown() {
		
	}
	
	@Test
	public void testFile1() throws Exception {
		
		File file = new File("./bin/json/outline/files/test1.json");
		
		JsonTextValidator jtop = new JsonTextValidator(file) {
			
			@Override
			public void reportProblem(String msg, Location loc, int violation, boolean isError) {
				
				Assert.fail();
			}
		};
		
		jtop.parse();

	}
	
	@Test
	public void testFile2() throws Exception {
		
		File file = new File("./bin/json/outline/files/test2.json");
		
		JsonTextValidator jtop = new JsonTextValidator(file){
			
			@Override
			public void reportProblem(String msg, Location loc, int violation, boolean isError) {
				
				Assert.fail();
			}
		};
		
		jtop.parse();

	}
	
	@Test
	public void testFile3() throws Exception {
		
		File file = new File("./bin/json/outline/files/test3.json");
		
		JsonTextValidator jtop = new JsonTextValidator(file) {
			
			@Override
			public void reportProblem(String msg, Location loc, int violation, boolean isError) {
				
				Assert.fail();
			}
		};
		
		jtop.parse();

	}
	
	@Test
	public void testFile4() throws Exception {
		
		File file = new File("./bin/json/outline/files/test4.json");
		
		JsonTextValidator jtop = new JsonTextValidator(file) {
			
			@Override
			public void reportProblem(String msg, Location loc, int violation, boolean isError) {
				
				Assert.fail();
			}
		};
		
		jtop.parse();

	}
	
	@Test
	public void testFile5() throws Exception {
		
		File file = new File("./bin/json/outline/files/test5.json");
		
		JsonTextValidator jtop = new JsonTextValidator(file) {
			
			@Override
			public void reportProblem(String msg, Location loc, int violation, boolean isError) {
				
				Assert.assertEquals(loc.charStart, 16);
			}
		};
		
		jtop.parse();
		
	}
	
	@Test
	public void testFile6() throws Exception {
		
		File file = new File("./bin/json/outline/files/test6.json");
		
		JsonTextValidator jtop = new JsonTextValidator(file) {
			
			@Override
			public void reportProblem(String msg, Location loc, int violation, boolean isError) {
				
				Assert.assertEquals(loc.charStart, 19);
			}
		};
		
		jtop.parse();
	
	}
	
	@Test
	public void testFile7() throws Exception {
		
		File file = new File("./bin/json/outline/files/test7.json");
		
		JsonTextValidator jtop = new JsonTextValidator(file) {
			
			@Override
			public void reportProblem(String msg, Location loc, int violation, boolean isError) {
				
				Assert.assertEquals(loc.charStart, 20);
			}
		};
		
		jtop.parse();
		
	}
	
	@Test
	public void testFile8() throws Exception {

		File file = new File("./bin/json/outline/files/test8.json");

		JsonTextValidator jtop = new JsonTextValidator(file) {

			@Override
			public void reportProblem(String msg, Location loc, int violation, boolean isError) {

				Assert.assertEquals(loc.charStart, 18);
			}
		};

		jtop.parse();

	}
	
	@Test
	public void testFile9() throws Exception {
		
		File file = new File("./bin/json/outline/files/test9.json");
		
		JsonTextValidator jtop = new JsonTextValidator(file) {
			
			@Override
			public void reportProblem(String msg, Location loc, int violation, boolean isError) {
				
				Assert.fail();
			}
		};
		
		jtop.parse();

	}
}
