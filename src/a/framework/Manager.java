package a.framework;

public interface Manager {
    public Service service(Entity entity, String id) throws Exception; 
    public Object resource(Entity entity, String id) throws Exception;
    public void err(Entity entity, String id, Exception e);
}