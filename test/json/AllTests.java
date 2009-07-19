package json;

import json.outline.JsonTextOutlineParserTest;
import json.text.JsonReconcilingStrategyTest;
import json.validation.JsonTextValidationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
JsonTextOutlineParserTest.class,
JsonReconcilingStrategyTest.class,
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
