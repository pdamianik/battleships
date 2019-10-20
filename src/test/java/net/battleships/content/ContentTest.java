package net.battleships.content;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.Assert.assertEquals;

public class ContentTest {
	@Test
	public void testZipFileFunctionality() throws IOException {
		StringBuilder extractedData = new StringBuilder();
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile("src/test/resources/test.zip");
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert zipFile != null;
		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while(entries.hasMoreElements()){
			ZipEntry entry = entries.nextElement();
			InputStream stream = zipFile.getInputStream(entry);
			extractedData.append(new String(stream.readAllBytes(), StandardCharsets.UTF_8));
		}

		String savedData = Files.readString(Paths.get("src/test/resources/test.zip.data"));

		assertEquals("Extracted zip data and saved data should be the same", savedData, extractedData.toString());
	}
}
