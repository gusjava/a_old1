package a.framework;

public class Outside {
	private static Manager manager;

	public static void setManager(Manager manager0) {
		if (manager == null)
			manager = manager0;
	}

	public static Service service(Entity entity, String id) throws Exception {
		return manager.service(entity, id);
	}

	public static Object resource(Entity entity, String id) throws Exception {
		return manager.resource(entity, id);
	}

	public static void err(Entity entity, String id, Exception e) {
		manager.err(entity, id, e);
	}
}