package org.coreasm.plugins.universalcontrol.test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;

import org.junit.BeforeClass;

public class AtmostInSequence extends TestAllCasm {

	@BeforeClass
	public static void onlyOnce() {
		URL url = AtmostInSequence.class.getClassLoader().getResource(".");

		try {
			testFiles = new LinkedList<File>();
			getTestFile(testFiles, new File(url.toURI()).getParentFile(), AtmostInSequence.class);
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}