package br.zero.androidhelpers.database;

public interface FieldModifier {
	public static final FieldModifier PRIMARY_KEY = new FieldModifier() {};
	
	public static final FieldModifier AUTO_INCREMENT = new FieldModifier() {};
}
