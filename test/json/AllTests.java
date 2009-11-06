package json;

import json.outline.JsonTextOutlineParserTest;
import json.validation.JsonTextValidationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
JsonTextOutlineParserTest.class,
json.text.JsonReconcilingStrategyTest.class,
json.model.JsonReconcilingStrategyTest.class,
JsonTextValidationTest.class})
public class AllTests {

//	public static Test suite() {
//		TestSuite suite = new TestSuite("Test for json");
//		//$JUnit-BEGIN$
//
//		//$JUnit-END$
//		return suite;
//	}

}
