package content;

public class Loadable<T extends Gameobject> {
	public Loadable(Class<T> loadableClass) {

	}

	public Loadable(T loadableObject) {
		this(loadableObject.getClass());
	}
}
