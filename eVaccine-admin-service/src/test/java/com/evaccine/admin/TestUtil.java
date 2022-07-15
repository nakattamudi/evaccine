package com.evaccine.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.util.ResourceUtils;

public class TestUtil {

	public static String readFile(final String path) throws IOException {

		var resouceFile = ResourceUtils.getFile(path);
		return Files.readString(Path.of(resouceFile.getAbsolutePath()));

	}

}
