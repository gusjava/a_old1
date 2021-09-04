package a.entity.gus.b.changedetect1.check;

import java.util.HashMap;
import java.util.Map;

import a.framework.Entity;
import a.framework.F;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, F, P {
	public String creationDate() {return "20210904";}

	private Service buildCRC;
	private Map map;

	public EntityImpl() throws Exception {
		buildCRC = Outside.service(this,"gus.a.checksum.crc32");
		map = new HashMap();
	}
	
	
	public void p(Object obj) throws Exception {
		f(obj);
	}
	
	
	public boolean f(Object obj) throws Exception {
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String key = o[0];
		String value = o[1];
		
		String crc = (String) buildCRC.t(value);
		
		if(!map.containsKey(key)) {
			map.put(key,crc);
			return true;
		}
		
		String crc0 = (String) map.get(key);
		if(!crc0.equals(crc)) {
			map.put(key,crc);
			return true;
		}
		
		return false;
	}
}
