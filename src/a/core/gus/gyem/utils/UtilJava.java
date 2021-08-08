package a.core.gus.gyem.utils;

public class UtilJava {

	public static final String ABSTRACT = "abstract";
	public static final String ASSERT = "assert";
	public static final String BOOLEAN = "boolean";
	public static final String BREAK = "break";
	public static final String BYTE = "byte";
	public static final String CASE = "case";
	public static final String CATCH = "catch";
	public static final String CHAR = "char";
	public static final String CLASS = "class";
	public static final String CONST = "const";
	public static final String CONTINUE = "continue";
	public static final String DEFAULT = "default";
	public static final String DO = "do";
	public static final String DOUBLE = "double";
	public static final String ELSE = "else";
	public static final String ENUM = "enum";
	public static final String EXTENDS = "extends";
	public static final String FINAL = "final";
	public static final String FINALLY = "finally";
	public static final String FLOAT = "float";
	public static final String FOR = "for";
	public static final String GOTO = "goto";
	public static final String IF = "if";
	public static final String IMPLEMENTS = "implements";
	public static final String IMPORT = "import";
	public static final String INSTANCEOF = "instanceof";
	public static final String INT = "int";
	public static final String INTERFACE = "interface";
	public static final String LONG = "long";
	public static final String NATIVE = "native";
	public static final String NEW = "new";
	public static final String PACKAGE = "package";
	public static final String PRIVATE = "private";
	public static final String PROTECTED = "protected";
	public static final String PUBLIC = "public";
	public static final String RETURN = "return";
	public static final String SHORT = "short";
	public static final String STATIC = "static";
	public static final String STRICTFP = "strictfp";
	public static final String SUPER = "super";
	public static final String SWITCH = "switch";
	public static final String SYNCHRONIZED = "synchronized";
	public static final String THIS = "this";
	public static final String THROW = "throw";
	public static final String THROWS = "throws";
	public static final String TRANSIENT = "transient";
	public static final String TRY = "try";
	public static final String VOID = "void";
	public static final String VOLATILE = "volatile";
	public static final String WHILE = "while";
	
	public static final String NULL = "null";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	
	public static boolean isKeyword(String word) {
		try{UtilJava.class.getField(word.toUpperCase());}
		catch(SecurityException e) {return false;}
		catch(NoSuchFieldException e) {return false;}
		return true;
	}
}
