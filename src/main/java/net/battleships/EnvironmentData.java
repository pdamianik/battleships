package net.battleships;

import java.io.File;

public class EnvironmentData {
	/**
	 * Contains a constant value of the OSType Enum to identify
	 * the os the program is running on
	 * @see OSType
	 */
	public static final OSType OS = getOSType();
	private static String OSData = System.getProperty("os.name").toUpperCase();
	/**
	 * Contains the home folder of the user who is running the program
	 */
	public static final String USER_HOME_FOLDER = System.getProperty("user.home");
	/**
	 * Defines if the current program is running as a jar
	 */
	public static final boolean IS_RUNNING_AS_JAR = EnvironmentData.class.getResource("").getProtocol().equals("jar");
	/**
	 * The folder/jar the programs structures root is located in
	 */
	public static final File RUNNING_PROGRAM_FOLDER = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	/**
	 * The folder where java was started from
	 */
	public static final File RUNNING_FOLDER = new File(".");

	/**
	 * Tries to detect the OS type of the system this program is running on
	 * @see net.battleships.OSType
	 * @return the OS type
	 */
	public static OSType getOSType() {
		if (OSData == null) OSData = System.getProperty("os.name").toUpperCase();
		if (OSData.contains("WIN"))
			return OSType.WINDOWS;
		else if (OSData.contains("MAC"))
			return OSType.MAC;
		else if (OSData.contains("NIX") || OSData.contains("NUX") || OSData.contains("AIX"))
			return OSType.LINUX;
		return OSType.OTHER;
	}

	/**
	 * Getter-method for the OSData String
	 * @return the OSData String
	 */

	public String getOSData() {
		return OSData;
	}
}
