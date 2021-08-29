package a.entity.gus.a.io.transfer;

import a.framework.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.ByteBuffer;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210829";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[])obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

		InputStream is = (InputStream) o[0];
		OutputStream os = (OutputStream) o[1];
		
		transfer(is,os);
	}
	
	private void transfer(InputStream is, OutputStream os) throws Exception
	{
		ReadableByteChannel rbc = null;
		WritableByteChannel wbc = null;
		
		try
		{
			rbc = Channels.newChannel(is);
			wbc = Channels.newChannel(os);
		
			ByteBuffer buffer = ByteBuffer.allocateDirect(32 * 1024);

			while(rbc.read(buffer) != -1 || buffer.position() > 0)
			{
				buffer.flip();
				wbc.write(buffer);
				buffer.compact();
			}
		}
		finally
		{
			if(rbc!=null) rbc.close();
			if(wbc!=null) wbc.close();
		}
	}
}
