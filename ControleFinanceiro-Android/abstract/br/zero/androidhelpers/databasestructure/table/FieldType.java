package br.zero.androidhelpers.databasestructure.table;

public interface FieldType {
	
	public static FieldType INTEGER = new ConcreteFieldType("integer") {};
	public static FieldType TEXT = new ConcreteFieldType("text") {};
	public static FieldType BOOLEAN = new ConcreteFieldType("boolean") {};
	
}
