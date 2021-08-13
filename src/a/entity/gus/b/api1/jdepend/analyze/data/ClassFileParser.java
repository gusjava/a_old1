package a.entity.gus.b.api1.jdepend.analyze.data;

import java.io.*;
import java.util.*;


	/*
	 * magic			4 This identifies the .class file format. It should be 0xCAFEBABE. If it�s anything else, you�re dealing with a format more recent than this book.
	 * minor version	2 The minor version of the compiler
	 * major version	2 The major version of the compiler
	 * constant pool	variable
	 * 
	 * The first two bytes give the number of entries in the constant pool.
	 * Then, as many bytes as are necessary to fill that many entries are read.
	 * The constant pool is a table of constant values used by this class.
	 * 
	 * access flags	 2 These bit flags tell you whether the class is public, final, abstract, an interface, and a few other things.
	 * this class	   2 This tells you which entry in the constant pool holds this class�s class info.
	 * superclass	   2 If this is zero, then this class�s only superclass is java.lang.Object. Otherwise, this is an index into the constant pool for the superclass class info.
	 * interfaces	   variable
	 * 
	 * The interface table holds two byte indices into the constant pool table, one for each interface that this class implements.
	 * The first two bytes give the number of entries in the interface table. Therefore, after reading the first two bytes,
	 * you have to read twice as many bytes as the number stored in the first two bytes.
	 * 
	 * fields		   variable
	 * 
	 * The fields table includes one field�s info structure for each field in the class.
	 * 
	 * methods		  variable
	 * 
	 * The method table contains the byte codes for each method in the class,
	 * the return type of the method, and the types of each argument to the method.
	 * 
	 * attributes	   2 The attributes of the class
	 */

public class ClassFileParser {

	public static final int JAVA_MAGIC = 0xCAFEBABE;
	public static final char CLASS_DESCRIPTOR = 'L';
	public static final int ACC_INTERFACE = 0x200;
	public static final int ACC_ABSTRACT = 0x400;
	
	public static final int CONSTANT_UTF8 = 1;
	public static final int CONSTANT_UNICODE = 2;
	public static final int CONSTANT_INTEGER = 3;
	public static final int CONSTANT_FLOAT = 4;
	public static final int CONSTANT_LONG = 5;
	public static final int CONSTANT_DOUBLE = 6;
	public static final int CONSTANT_CLASS = 7;
	public static final int CONSTANT_STRING = 8;
	public static final int CONSTANT_FIELD = 9;
	public static final int CONSTANT_METHOD = 10;
	public static final int CONSTANT_INTERFACEMETHOD = 11;
	public static final int CONSTANT_NAMEANDTYPE = 12;
    public static final int CONSTANT_METHOD_HANDLE = 15;
    public static final int CONSTANT_METHOD_TYPE = 16;
    public static final int CONSTANT_INVOKEDYNAMIC = 18;
	
	
	
	
	
	
	
	private String className;
	private String superClassName;
	private String interfaceNames[];
	private boolean isAbstract;
	private JavaClass jClass;
	private Constant[] constantPool;
	private FieldOrMethodInfo[] fields;
	private FieldOrMethodInfo[] methods;
	private AttributeInfo[] attributes;
	
	
	
	private DataInputStream in;
	
	
	private byte _byte() throws IOException
	{return in.readByte();}
	
	private int _unsigned() throws IOException
	{return in.readUnsignedShort();}

	private int _int() throws IOException
	{return in.readInt();}
	
	private float _float() throws IOException
	{return in.readFloat();}
	
	private long _long() throws IOException
	{return in.readLong();}
	
	private double _double() throws IOException
	{return in.readDouble();}
	
	private String _UTF() throws IOException
	{return in.readUTF();}
	
	
	
	
	
	
	public JavaClass parse(InputStream is) throws Exception
	{
		in = new DataInputStream(is);
		
		jClass = new JavaClass("Unknown");

		//parse java magic
		int magic = _int();
		if(magic!=JAVA_MAGIC) throw new Exception("Invalid magic number");
		
		// parse minor version
		jClass.minorVersion = _unsigned();
		
		// parse major version
		jClass.majorVersion = _unsigned();
		
		// parse constant pool
		parseConstantPool();
		
		parseAccessFlags();
		parseClassName();
		parseSuperClassName();
		parseInterfaces();
		parseFields();
		parseMethods();
		parseAttributes();
		addClassConstantReferences();

		in.close();
		return jClass;
	}

	
	
	
  
	
	
	
	
	private void parseConstantPool() throws Exception
	{
		int nb = _unsigned();
		constantPool = new Constant[nb];

		for (int i=1;i<nb;i++)
		{
			constantPool[i] = new Constant();
			// 8-byte constants use two constant pool entries
			byte tag = constantPool[i].tag;
			if(tag==CONSTANT_DOUBLE || tag==CONSTANT_LONG) i++;
		}
	}
	
	
	
	
	
	

	private void parseAccessFlags() throws IOException
	{
		int accessFlags = in.readUnsignedShort();
		boolean isAbstract = ((accessFlags & ACC_ABSTRACT) != 0);
		boolean isInterface = ((accessFlags & ACC_INTERFACE) != 0);

		this.isAbstract = isAbstract || isInterface;
		jClass.isAbstract(this.isAbstract);
	}

	
	
	
	
	private void parseClassName() throws IOException
	{
		int entryIndex = _unsigned();
		className = getClassConstantName(entryIndex);
		jClass.setName(className);
		jClass.setPackageName(getPackageName(className));
	}
	
	
	
	

	private void parseSuperClassName() throws IOException
	{
		int entryIndex = _unsigned();
		superClassName = getClassConstantName(entryIndex);
		addImport(getPackageName(superClassName));
	}
	
	
	
	
	

	private void parseInterfaces() throws IOException
	{
		int interfacesCount = _unsigned();
		interfaceNames = new String[interfacesCount];
		for (int i=0;i<interfacesCount;i++)
		{
			int entryIndex = _unsigned();
			interfaceNames[i] = getClassConstantName(entryIndex);
			addImport(getPackageName(interfaceNames[i]));
		}
	}

	
	
	
	
	
	
	private void parseFields() throws IOException
	{
		int nb = _unsigned();
		fields = new FieldOrMethodInfo[nb];
		
		for(int i=0;i<nb;i++)
		{
			fields[i] = parseFieldOrMethodInfo();
			String descriptor = toUTF8(fields[i].getDescriptorIndex());
			String[] types = descriptorToTypes(descriptor);
			for(int t=0;t<types.length;t++)
			addImport(getPackageName(types[t]));
		}
	}
	
	
	
	
	
	
	

	private void parseMethods() throws IOException
	{
		int nb = _unsigned();
		methods = new FieldOrMethodInfo[nb];
		for (int i=0;i<nb;i++)
		{
			methods[i] = parseFieldOrMethodInfo();
			String descriptor = toUTF8(methods[i].getDescriptorIndex());
			String[] types = descriptorToTypes(descriptor);
			for (int t=0;t<types.length;t++)
			if(types[t].length()>0) addImport(getPackageName(types[t]));
		}
	}
	
	
	
	
	
	private class Constant
	{
		public byte tag;
		public int nameIndex = -1;
		public int typeIndex = -1;
		public Object value = null;
		
		public Constant() throws Exception
		{
			tag = _byte();
			switch (tag)
			{
			case (CONSTANT_CLASS):
			case (CONSTANT_STRING):			 init(_unsigned());break;
			case (CONSTANT_FIELD):
			case (CONSTANT_METHOD):
			case (CONSTANT_INTERFACEMETHOD):
			case (CONSTANT_NAMEANDTYPE):		init(_unsigned(),_unsigned());break;
			case (CONSTANT_INTEGER):			init(new Integer(_int()));break;
			case (CONSTANT_FLOAT):			    init(new Float(_float()));break;
			case (CONSTANT_LONG):			    init(new Long(_long()));break;
			case (CONSTANT_DOUBLE):			    init(new Double(_double()));break;
			case (CONSTANT_UTF8):			    init(_UTF());break;
			case (CONSTANT_METHOD_HANDLE):      init(_byte(),_unsigned());break;
			case (CONSTANT_METHOD_TYPE):        init(_unsigned());break;
			case (CONSTANT_INVOKEDYNAMIC):      init(_unsigned(),_unsigned());break;

			default: throw new Exception("Unknown constant: "+tag);
			}
		}


		
		private void init(int _nameIndex)
		{
			nameIndex = _nameIndex;
		}

		private void init(Object _value)
		{
			value = _value;
		}

		private void init(int _nameIndex, int _typeIndex)
		{
			nameIndex = _nameIndex;
			typeIndex = _typeIndex;
		}

		
		

		public String toString()
		{
			StringBuffer s = new StringBuffer();
			s.append("tag: "+tag);
			if(nameIndex>-1) s.append(" nameIndex: "+nameIndex);
			if(typeIndex>-1) s.append(" typeIndex: "+typeIndex);
			if(value!=null)  s.append(" value: "+value);
			return s.toString();
		}
	}
	
	
	
	
	
	
	
	
	
	private FieldOrMethodInfo parseFieldOrMethodInfo() throws IOException
	{
		FieldOrMethodInfo result = new FieldOrMethodInfo(
				in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());

		int attributesCount = in.readUnsignedShort();
		for(int a=0;a<attributesCount;a++) parseAttribute();
		return result;
	}
	
	
	
	
	
	

	private void parseAttributes() throws IOException
	{
		int attributesCount = in.readUnsignedShort();
		attributes = new AttributeInfo[attributesCount];

		for(int i=0;i<attributesCount;i++)
		{
			attributes[i] = parseAttribute();
			// Section 4.7.7 of VM Spec - Class File Format
			if(attributes[i].getName()!=null)
				if(attributes[i].getName().equals("SourceFile"))
				{
					byte[] b = attributes[i].getValue();
					int b0 = b[0] < 0 ? b[0] + 256 : b[0];
					int b1 = b[1] < 0 ? b[1] + 256 : b[1];
					int pe = b0 * 256 + b1;

					String descriptor = toUTF8(pe);
					jClass.setSourceFile(descriptor);
				}
		}
	}
	
	
	
	
	
	
	
	

	private AttributeInfo parseAttribute() throws IOException
	{
		AttributeInfo result = new AttributeInfo();
		int nameIndex = in.readUnsignedShort();
		
		if(nameIndex!=-1)
		result.setName(toUTF8(nameIndex));

		int attributeLength = in.readInt();
		byte[] value = new byte[attributeLength];
		for(int b=0;b<attributeLength;b++)
		value[b] = in.readByte();
		
		result.setValue(value);
		return result;
	}

	
	
	
	
	
	
	
	
	
	private Constant getConstantPoolEntry(int entryIndex) throws IOException
	{
		if(entryIndex<0 || entryIndex>=constantPool.length)
		   throw new IOException("Illegal constant pool index : " + entryIndex);
		return constantPool[entryIndex];
	}
	
	
	
	
	

	private void addClassConstantReferences() throws IOException
	{
		for(int j=1;j<constantPool.length;j++)
		{
			if(constantPool[j].tag==CONSTANT_CLASS)
			{
				String name = toUTF8(constantPool[j].nameIndex);
				addImport(getPackageName(name));
			}
			if(constantPool[j].tag==CONSTANT_DOUBLE || constantPool[j].tag==CONSTANT_LONG)
			j++;
		}
	}

	
	
	
	
	
	
	
	
	private String getClassConstantName(int entryIndex) throws IOException
	{
		Constant entry = getConstantPoolEntry(entryIndex);
		if (entry == null)return "";
		return slashesToDots(toUTF8(entry.nameIndex));
	}
	
	
	
	
	
	
	
	

	private String toUTF8(int entryIndex) throws IOException
	{
		Constant entry = getConstantPoolEntry(entryIndex);
		if(entry.tag==CONSTANT_UTF8) return (String)entry.value;
		throw new IOException("Constant pool entry is not a UTF8 type: "+entryIndex);
	}
	
	
	
	
	
	
	

	private void addImport(String importPackage)
	{
		if(importPackage!=null)
		jClass.addImportedPackage(new JavaPackage(importPackage));
	}
	
	
	
	

	private String slashesToDots(String s)
	{return s.replace('/', '.');}
	
	
	
	
	

	private String getPackageName(String s)
	{
		if(s.length()>0 && s.charAt(0)=='[')
		{
			String types[] = descriptorToTypes(s);
			if(types.length==0) return null; 
			s = types[0];
		}
		s = slashesToDots(s);
		int index = s.lastIndexOf(".");
		if(index>0) return s.substring(0,index);
		
		return "Default";
	}
	
	
	
	
	
	

	private String[] descriptorToTypes(String descriptor)
	{
		int typesCount = 0;
		for(int index=0;index<descriptor.length();index++)
		if(descriptor.charAt(index)==';') typesCount++;

		String types[] = new String[typesCount];

		int typeIndex = 0;
		for(int index=0;index<descriptor.length();index++)
		{
			int startIndex = descriptor.indexOf(CLASS_DESCRIPTOR,index);
			if(startIndex<0) break;
			
			index = descriptor.indexOf(';',startIndex+1);
			types[typeIndex++] = descriptor.substring(startIndex+1,index);
		}
		return types;
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

	class FieldOrMethodInfo
	{
		private int _accessFlags;
		private int _nameIndex;
		private int _descriptorIndex;

		FieldOrMethodInfo(int accessFlags, int nameIndex, int descriptorIndex)
		{
			_accessFlags = accessFlags;
			_nameIndex = nameIndex;
			_descriptorIndex = descriptorIndex;
		}

		int accessFlags()		   {return _accessFlags;}
		int getNameIndex()		  {return _nameIndex;}
		int getDescriptorIndex()	{return _descriptorIndex;}

		public String toString()
		{
			StringBuffer s = new StringBuffer("");
			try
			{
				s.append("\n	name (#" + getNameIndex() + ") = "+toUTF8(getNameIndex()));
				s.append("\n	signature (#" + getDescriptorIndex() + ") = "+toUTF8(getDescriptorIndex()));
				String[] types = descriptorToTypes(toUTF8(getDescriptorIndex()));
				for(int t=0;t<types.length;t++)
				s.append("\n		type = " + types[t]);
			}
			catch(Exception e)
			{e.printStackTrace();}
			return s.toString();
		}
	}

	
	
	
	
	
	
	
	class AttributeInfo
	{
		private String name;
		private byte[] value;

		public void setName(String name)
		{this.name = name;}

		public String getName()
		{return this.name;}

		public void setValue(byte[] value)
		{this.value = value;}

		public byte[] getValue()
		{return this.value;}
	}
	
	
	
	

	
	public String toString()
	{
		StringBuffer s = new StringBuffer();

		try
		{
			s.append("\n" + className + ":\n");
			s.append("\nConstants:\n");
			for(int i=1;i<constantPool.length;i++)
			{
				Constant entry = getConstantPoolEntry(i);
				s.append("	"+i+". "+entry.toString()+"\n");
				if (entry.tag==CONSTANT_DOUBLE || entry.tag==CONSTANT_LONG) i++;
			}

			s.append("\nClass Name: "+className+"\n");
			s.append("Super Name: "+superClassName+"\n\n");
			
			s.append(interfaceNames.length + " interfaces\n");
			for(int i=0;i<interfaceNames.length;i++)
			s.append("	" + interfaceNames[i]+"\n");
			
			s.append("\n"+fields.length+" fields\n");
			for(int i=0;i<fields.length;i++)
			s.append(fields[i].toString()+"\n");
			
			s.append("\n" + methods.length + " methods\n");
			for(int i=0;i<methods.length;i++)
			s.append(methods[i].toString()+"\n");
			
			s.append("\nDependencies:\n");
			Iterator imports = jClass.getImportedPackages().iterator();
			while(imports.hasNext())
			{
				JavaPackage jPackage = (JavaPackage) imports.next();
				s.append("	"+jPackage.getName()+"\n");
			}
		}
		catch(Exception e)
		{e.printStackTrace();}
		
		return s.toString();
	}
	
}
