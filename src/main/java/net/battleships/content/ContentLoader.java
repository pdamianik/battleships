package net.battleships.content;

import net.battleships.OSType;

import java.io.File;
import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.function.Consumer;

/**
 * Manages content files (like .ships or .weapon files)
 * before they are loaded
 * @author Philip Damianik
 * @version 2020-01-30
 */

public class ContentLoader {
	private static final String OS = System.getProperty("os.name").toUpperCase();
	private static final String USER_HOME_FOLDER = System.getProperty("user.home");
	private static final String DEFAULT_RESOURCE_SUBFOLDER_PATH = "data";
	private static final File RUNNING_FOLDER = new File(".");
	private File localFolder;
	private File resourceFolder;
	private ContentWatcher resourceFolderWatchService;
	private List<Loadable> resourceFiles = new ArrayList<>();

	/**
	 * Identifies the OS and then locates the resource folder
	 * for this game (in local/application/folder/data; containing
	 * .ship and .weapon files).
	 * This folder will be created if it doesn't exist. After the folder
	 * searching is done, the folder will be indexed and a watcher
	 * will be placed, to be able to instantly detect new files/changes
	 * to the files
	 * <br><br>
	 * the os type will be used to locate the local application data folder:
	 * <pre>
	 * Windows:       %AppData%\battleships
	 * Mac:           ~/Library/Application Support/battleships
	 * Linux:         ~/.local/battleships
	 * Other/Unknown: ./battleships</pre>
	 * The default relative path for the local application data folder
	 * is: <code>path/to/the/local/application/folder/data</code>
	 * @throws AccessDeniedException will be thrown when the resource
	 * folder couldn't be created
	 */
	public ContentLoader() throws AccessDeniedException {
		this(DEFAULT_RESOURCE_SUBFOLDER_PATH, getOSType());
	}

	/**
	 * Locates the resource folder
	 * for this game (in local/application/folder/data; containing
	 * .ship and .weapon files).
	 * This folder will be created if it doesn't exist. After the folder
	 * searching is done, the folder will be indexed and a watcher
	 * will be placed, to be able to instantly detect new files/changes
	 * to the files
	 * <br><br>
	 * The default relative path for the local application data folder
	 * is <code>path/to/the/local/application/folder/data</code>
	 * @param osType the os type (will be used to locate the local application data folder:
	 * <pre>
	 * Windows:       %AppData%\battleships
	 * Mac:           ~/Library/Application Support/battleships
	 * Linux:         ~/.local/battleships
	 * Other/Unknown: ./battleships
	 * )</pre>
	 * @see net.battleships.OSType
	 * @throws AccessDeniedException will be thrown when the resource
	 * folder couldn't be created
	 */
	public ContentLoader(OSType osType) throws AccessDeniedException {
		this(DEFAULT_RESOURCE_SUBFOLDER_PATH, osType);
	}

	/**
	 * Identifies the OS and then locates the resource folder
	 * for this game (in local/application/folder/data; containing
	 * .ship and .weapon files).
	 * This folder will be created if it doesn't exist. After the folder
	 * searching is done, the folder will be indexed and a watcher
	 * will be placed, to be able to instantly detect new files/changes
	 * to the files
	 * <br><br>
	 * The default relative path for the local application data folder
	 * is <code>path/to/the/local/application/folder/data</code>
	 * @param path the path relative to the local application data folder
	 *             where the resource folder is located
	 * @see net.battleships.OSType
	 * @throws AccessDeniedException will be thrown when the resource
	 * folder couldn't be created
	 */
	public ContentLoader(String path) throws AccessDeniedException {
		this(path, getOSType());
	}

	/**
	 * Locates the resource folder
	 * for this game (in [localFolderLocation]/data; containing
	 * .ship and .weapon files).
	 * This folder will be created if it doesn't exist. After the folder
	 * searching is done, the folder will be indexed and a watcher
	 * will be placed, to be able to instantly detect new files/changes
	 * to the files
	 * @param path the path relative to the local application data folder
	 *             where the resource folder is located
	 * @param osType the os type (will be used to locate the local application data folder:
	 * <pre>
	 * Windows:       %AppData%\battleships
	 * Mac:           ~/Library/Application Support/battleships
	 * Linux:         ~/.local/battleships
	 * Other/Unknown: ./battleships
	 * )</pre>
	 * @see net.battleships.OSType
	 * @throws AccessDeniedException will be thrown when the resource
	 * folder couldn't be created
	 */
	public ContentLoader(String path, OSType osType) throws AccessDeniedException {
		// Get OS Type / OS specific user applications data folder
		if (osType == OSType.WINDOWS)
			// Windows -> Paths\separated\by\backslashes\, local folder: %AppData%
			this.localFolder = new File(System.getenv("AppData") + "\\battleships");
		else if (osType == OSType.MAC)
			// MAC OS -> Paths/separated/by/slashes/, local folder: ~/Library/Application Support/
			this.localFolder = new File(USER_HOME_FOLDER + "/Library/Application Support/battleships");
		else if (osType == OSType.LINUX)
			// Linux -> Paths/separated/by/slashes, local folder: ~/.local/share/
			this.localFolder = new File(USER_HOME_FOLDER + "/.local/share/battleships");
		else
			// Unknown -> Paths/\\separated/\\by/\\unknown/\\separators/\\, local folder: ./
			this.localFolder = new File(RUNNING_FOLDER.getAbsolutePath() + File.separator + "battleships");

		// Get resource folder inside of the local folder
		this.resourceFolder = new File(this.localFolder.getAbsolutePath() + File.separator + path);

		// Folder checking
		// local folder
		// check if it exists
		if (!this.localFolder.exists())
			// if it doesn't exist, try creating it
			if (!this.localFolder.mkdir()) throw new AccessDeniedException("The local folder for data couldn't be created");
		// if it exists, check if it's a directory
		else if (!this.localFolder.isDirectory())
			throw new IllegalArgumentException("The path passed as an argument isn't a directory");

		// resource folder
		// check if it exists
		if (!this.resourceFolder.exists())
			// if it doesn't exist, try creating it
			if (!this.resourceFolder.mkdir()) throw new AccessDeniedException("The local folder for data couldn't be created");
		// if it exists, check if it's a directory
		else if (!this.resourceFolder.isDirectory())
			throw new IllegalArgumentException("The path passed as an argument isn't a directory");

		// index folder
		for (File file : Objects.requireNonNull(this.resourceFolder.listFiles((dir, name) -> name.endsWith(".ship") || name.endsWith(".weapon"))))
			if (file.getName().endsWith(".ship"))
				this.resourceFiles.add(new ShipLoadable(file, this));
			else
				this.resourceFiles.add(new WeaponLoadable(file, this));
	}

	/**
	 * Tries to detect the OS type of the system this program is running on
	 * @see net.battleships.OSType
	 * @return the OS type
	 */
	public static OSType getOSType() {
		if (OS.contains("WIN"))
			return OSType.WINDOWS;
		else if (OS.contains("MAC"))
			return OSType.MAC;
		else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"))
			return OSType.LINUX;
		return OSType.OTHER;
	}

	/**
	 * Lists all .weapon and .ship files that have been indexed
	 * from the resource folder
	 * @return an ArrayList containing the loadables from the
	 * resource folder
	 */
	public List<Loadable> listAvailable() {
		return new ArrayList<>(this.resourceFiles);
	}

	/**
	 * Loads all the indexed resource files into factories
	 * @return an array containing all factories
	 */
	public Factory[] loadAll() {
		List<Factory> resultingFactories = new ArrayList<>();
		this.resourceFiles.forEach((Loadable loadable) -> resultingFactories.add(loadable.load()));
		this.resourceFiles.clear();
		return (Factory[]) resultingFactories.toArray();
	}

	/**
	 * Loads exactly one item by it's index
	 * @param index the index of the item to load
	 * @return the Factory of the loaded resource
	 */
	public Factory load(int index) {
		return this.resourceFiles.get(index).load();
	}

	/**
	 * Loads the resources passed as an collection
	 * @param loadablesToLoad the loadables
	 * @return the loaded Factories from the passed Loadables
	 */
	public static Factory[] load(Collection<Loadable> loadablesToLoad) {
		ArrayList<Factory> loadedItems = new ArrayList<>();
		loadablesToLoad.forEach((Loadable toLoad) -> loadedItems.add(toLoad.load()));
		return (Factory[]) loadedItems.toArray();
	}

	/**
	 * Loads the passed Loadable
	 * @param toLoad the Loadable to load
	 * @return the resulting factory from the loaded
	 * Loadable
	 */
	public static Factory load(Loadable toLoad) {
		return toLoad.load();
	}

	/**
	 * @return the count of indexed resource files
	 */
	public int size() {
		return this.resourceFiles.size();
	}

	/**
	 * @return true, if some files could be indexed, false
	 * if no files could be found in the local resource
	 * folder
	 */
	public boolean isEmpty() {
		return this.resourceFiles.isEmpty();
	}

	/**
	 * @return an iterator for the indexed local resource
	 * files
	 */
	public Iterator<Loadable> iterator() {
		return this.resourceFiles.iterator();
	}

	/**
	 * @return the array representation of the indexed
	 * local resource files
	 */
	public Loadable[] toArray() {
		return (Loadable[]) this.resourceFiles.toArray();
	}

	/**
	 * Get a Loadable resource file at a specific index
	 * @param index the index of the Loadable of interest
	 * @return the Loadable at the index
	 */
	public Loadable get(int index) {
		return this.resourceFiles.get(index);
	}

	/**
	 * Performs an action on every indexed file
	 * @param consumer the action to perform
	 */
	public void forEach(Consumer<? super Loadable> consumer) {
		this.resourceFiles.forEach(consumer);
	}
}
