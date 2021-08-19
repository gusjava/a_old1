package a.entity.gus.b.entitysrc1.listing.main;

import java.io.File;

import a.framework.*;

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
		if(rootDir==null) return null;
		return dirToListing.t(rootDir);
	}
}
