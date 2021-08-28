package a.entity.gus.b.entityclass2.listing.main;

import java.io.File;
import java.util.List;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20210828";}

	private Service appLocation;
	private Service buildListing;
	
	private List listing;

	public EntityImpl() throws Exception {
		appLocation = Outside.service(this,"gus.a.app.location");
		buildListing = Outside.service(this,"gus.b.entityclass2.listing");
	}
	
	
	public Object g() throws Exception {
		if(listing==null) init();
		return listing;
	}
	
	private void init() throws Exception {
		File location = (File) appLocation.g();
		listing = (List) buildListing.t(location);
	}
}
