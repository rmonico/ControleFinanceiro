package br.zero.androidhelpers.databasestructure.table;

public interface FieldType {
	
	public static FieldType INTEGER = new ConcreteFieldType("int") {};
	public static FieldType TEXT = new ConcreteFieldType("text") {};
	public static FieldType BOOLEAN = new ConcreteFieldType("boolean") {};
	
}
