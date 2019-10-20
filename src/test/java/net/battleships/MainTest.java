/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package net.battleships;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.Assert.*;

public class MainTest {
    @Test public void testAppHasAGreeting() {
        Main classUnderTest = new Main();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }

    @Test
    public void testZipFileFunctionality() throws IOException {
        String extractedData = "";
        ZipFile zipFile = null;

        try {
            zipFile = new ZipFile("testResource/test.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }

		assert zipFile != null;
		Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            InputStream stream = zipFile.getInputStream(entry);
            extractedData += new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }

        String savedData = Files.readString(Paths.get("testResource/test.zip.data"));

        if (extractedData.equals(savedData)) {
            System.out.println("Extracted zipfile correctly.");
        } else {
            throw new IOException("Couldn't extract zipfile correctly!");
        }
    }
}
