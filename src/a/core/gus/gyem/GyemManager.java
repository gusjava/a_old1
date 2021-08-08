package a.core.gus.gyem;

import a.framework.Entity;
import a.framework.Manager;
import a.framework.Service;

public class GyemManager extends GyemSystem implements Manager {

	public Service service(Entity entity, String id) throws Exception {
		return (Service) moduleT(M035_T_MANAGER_SERVICE).t(new Object[] {entity,id});
	}

	public Object resource(Entity entity, String id) throws Exception {
		return moduleT(M036_T_MANAGER_RESOURCE).t(new Object[] {entity,id});
	}

	public void err(Entity entity, String id, Exception e) {
		try {moduleP(M037_P_MANAGER_ERR).p(new Object[] {entity,id,e});}
		catch (Exception e1) {fatalManagerErr(e1);}
	}
}
