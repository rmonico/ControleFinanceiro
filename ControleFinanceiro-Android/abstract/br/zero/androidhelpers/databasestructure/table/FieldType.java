package br.zero.androidhelpers.databasestructure.table;

public interface FieldType {
	
	public static FieldType INTEGER = new ConcreteFieldType("integer", Integer.class) {};
	public static FieldType TEXT = new ConcreteFieldType("text", String.class) {};
	public static FieldType BOOLEAN = new ConcreteFieldType("boolean", Boolean.class) {};
	
	Class<?> getJavaCorrespondingType();

	String getSQLiteTypeName();
	
}
