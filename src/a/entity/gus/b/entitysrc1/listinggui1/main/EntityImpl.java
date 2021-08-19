package a.entity.gus.b.entitysrc1.listinggui1.main;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, I {
	public String creationDate() {return "20210818";}


	private Service listingGui;
	private Service listingProvider;
	
	public EntityImpl() throws Exception {
		listingGui = Outside.service(this,"*gus.b.entitysrc1.listinggui1");
		listingProvider = Outside.service(this,"gus.b.entitysrc1.listing.main");
		
		listingGui.p(listingProvider);
		listingGui.e();
	}
	
	
	public Object g() throws Exception
	{return listingGui.g();}
	
	
	public Object i() throws Exception
	{return listingGui.i();}
}
