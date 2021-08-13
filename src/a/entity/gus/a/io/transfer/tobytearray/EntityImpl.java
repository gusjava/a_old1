package a.entity.gus.a.io.transfer.tobytearray;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20210813";}


	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		int nRead;
		byte[] data = new byte[16384];

		try
		{
			while((nRead = is.read(data,0,data.length)) != -1)
			bos.write(data,0,nRead);
			bos.flush();
		}
		finally
		{is.close();}
		return bos.toByteArray();
	}
}