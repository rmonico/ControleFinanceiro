package br.zero.androidhelpers.databasestructure.table;

public interface FieldModifier {
	public static final FieldModifier PRIMARY_KEY = new ConcreteFieldModifier("primary key") {};
	
	public static final FieldModifier AUTO_INCREMENT = new ConcreteFieldModifier("autoincrement") {};
}
