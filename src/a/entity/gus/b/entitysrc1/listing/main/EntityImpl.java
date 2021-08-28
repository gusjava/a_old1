package a.entity.gus.b.entitysrc1.listing.main;

import java.io.File;
import java.util.ArrayList;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210817";}

	private Service dirToListing;
	private File rootDir;
	
	
	public EntityImpl() throws Exception
	{
		dirToListing = Outside.service(this,"gus.b.entitysrc1.listing");
		rootDir = (File) Outside.resource(this,"g#gus.b.entitysrc1.rootdir");
	}
	
	
	public Object g() throws Exception
	{
		if(rootDir==null) return new ArrayList();
		return dirToListing.t(rootDir);
	}
}
