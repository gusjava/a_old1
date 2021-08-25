package a.entity.gus.b.entitysrc1.importer1.gus06.engine2;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210814";}

	
	private Service readFile;

	public EntityImpl() throws Exception {
		readFile = Outside.service(this,"gus.a.file.string.read");
	}
	

	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String today() {return sdf.format(new Date());}
	
	
	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		
		File inputRoot = (File) o[0];
		String inputName = (String) o[1];
		File outputRoot = (File) o[2];
		String outputName = (String) o[3];
		
		if(!inputName.startsWith("gus.")) inputName = "gus."+inputName;
		if(!outputName.startsWith("gus.")) outputName = "gus."+outputName;
		
		System.out.println("Importing gus06 entity "+inputName);
		System.out.println("Into "+outputName);
		
		String inputPackage = "gus06.entity."+inputName;
		String outputPackage = "a.entity."+outputName;
		
		File inputDir = new File(inputRoot, inputPackage.replace(".",File.separator));
		File outputDir = new File(outputRoot, outputPackage.replace(".",File.separator));
		
		File inputFile = new File(inputDir,"EntityImpl.java");
		File outputFile = new File(outputDir,"EntityImpl.java");
		
		if(!inputFile.isFile()) throw new Exception("Invalid input file: "+inputFile);
		if(outputFile.exists()) throw new Exception("Output file already found: "+outputFile);
		
		String inputSrc = (String) readFile.t(inputFile);
		
		outputFile.getParentFile().mkdirs();
		PrintStream p = new PrintStream(outputFile);
		
		inputSrc = inputSrc.replace(inputPackage, outputPackage);
		inputSrc = inputSrc.replace("gus06.framework", "a.framework");
		inputSrc = inputSrc.replaceAll("(?s)public String creationDate\\(\\)[^\"]+\"[^\"]+\"", "public String creationDate() {return \""+today()+"\"");
		
		p.print(inputSrc);
		p.close();
	}
}
