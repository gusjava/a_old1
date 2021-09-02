package a.entity.gus.b.entitysrc1.generator1.testing;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210901";}

	private Service generator;

	public EntityImpl() throws Exception {
		generator = Outside.service(this, "gus.b.entitysrc1.generator1");
	}
	
	
	public void p(Object obj) throws Exception {
		int nb = Integer.parseInt(""+obj);
		
		for(int i=0;i<nb;i++) 
		generator.p(generateLine(i));
	}
	
	
	private String generateLine(int i) throws Exception {
		StringBuffer b = new StringBuffer("generated.t");
		b.append(formatNumber(i));
		
		String features = features();
		if(features.length()>0) {
			b.append(" "+features);
		}
		return b.toString();
	}
	
	
	private String formatNumber(int n) {
		String s = ""+n;
		while(s.length()<5) s = "0"+s;
		return s;
	}
	
	
	
	private String features() {
		StringBuffer b = new StringBuffer();
		
		if(chance()) b.append("b");
		if(chance()) b.append("e");
		if(chance()) b.append("f");
		if(chance()) b.append("g");
		if(chance()) b.append("h");
		if(chance()) b.append("i");
		if(chance()) b.append("p");
		if(chance()) b.append("r");
		if(chance()) b.append("s");
		if(chance()) b.append("t");
		if(chance()) b.append("v");
		
		return b.toString();
	}
	
	private boolean chance()
	{return (int) (Math.random()*2) == 0;}
}
