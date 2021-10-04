package a.entity.gus.b.entitysrc2.database.cx.initdb;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, G {
	public String creationDate() {return "20210901";}

	public static final String STRUCT_LAST_UPDATE = "2021-10-04 21:36:00"; //yyyy-MM-dd HH:mm:ss
	public static final boolean ALWAYS_RESET = false;
	
	
	private Service initEntity;
	private Service initEntityService;
	private Service initEntityResource;
	private Service initEntityLink;
	private Service initEntityCompileErr;
	private Service initFK;

	public EntityImpl() throws Exception
	{
		initEntity = Outside.service(this,"gus.b.entitysrc2.database.cx.initdb.entity");
		initEntityService = Outside.service(this,"gus.b.entitysrc2.database.cx.initdb.entity_service");
		initEntityResource = Outside.service(this,"gus.b.entitysrc2.database.cx.initdb.entity_resource");
		initEntityLink = Outside.service(this,"gus.b.entitysrc2.database.cx.initdb.entity_link");
		initEntityCompileErr = Outside.service(this,"gus.b.entitysrc2.database.cx.initdb.entity_compile_err");
		initFK = Outside.service(this,"gus.b.entitysrc2.database.cx.initdb.fk");
	}

	public Object g() throws Exception
	{
		if(ALWAYS_RESET) return null;
		return STRUCT_LAST_UPDATE;
	}
	
	public void p(Object obj) throws Exception
	{
		initEntity.p(obj);
		initEntityService.p(obj);
		initEntityResource.p(obj);
		initEntityLink.p(obj);
		initEntityCompileErr.p(obj);
		initFK.p(obj);
	}
}
