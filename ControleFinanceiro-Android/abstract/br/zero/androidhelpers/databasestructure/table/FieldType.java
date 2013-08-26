package br.zero.androidhelpers.databasestructure.table;

public interface FieldType<T> {
	
	public static FieldType<Integer> INTEGER = new IntegerFieldType();
	public static FieldType<String> TEXT = new StringFieldType();
	public static FieldType<Boolean> BOOLEAN = new BooleanFieldType();
	
	Class<? extends T> getJavaCorrespondingType();

	String getSQLiteTypeName();

	String formatToSQL(T value);
	
}
