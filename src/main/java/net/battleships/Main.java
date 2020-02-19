package net.battleships;

import net.battleships.content.ContentLoader;

import java.io.File;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;

public class Main {
	public String test = "test";

	public String getGreeting() {
		return "Hello world.";
	}

	public static void main(String[] args) {
		try {
			ContentLoader contentLoader = new ContentLoader();
		} catch (AccessDeniedException e) {
			e.printStackTrace();
		}
		Main thisObject = new Main();
		System.out.println(thisObject.getGreeting());
		System.out.println(EnvironmentData.IS_RUNNING_AS_JAR);
		System.out.println(EnvironmentData.RUNNING_FOLDER.getAbsolutePath());
		System.out.println(Arrays.toString(EnvironmentData.RUNNING_FOLDER.list()));
		System.out.println(Arrays.toString(new File("./src/main/resources/data").list()));
		System.out.println("Test");
	}
}