package org.coreasm.aspects.test.args;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.coreasm.aspects.test.TestAllCasm;

public class TestArgs6 extends TestAllCasm {

	@BeforeClass
	public static void onlyOnce() {
		URL url = TestArgs6.class.getClassLoader().getResource(".");

		try {
			testFiles = new LinkedList<File>();
			getTestFile(testFiles, new File(url.toURI()).getParentFile(), TestArgs6.class);
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}