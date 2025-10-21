package edu.txts.sps131025.util;
package com.example.demo.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtil {

	public static void copyFile(Path source, Path target) throws IOException {
		Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
	}

	public static boolean deleteFile(String path) {
		File file = new File(path);
		return file.exists() && file.delete();
	}
}

