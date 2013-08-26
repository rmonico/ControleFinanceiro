package br.zero.androidhelpers.databasestructure.table;

import java.util.Calendar;

public interface FieldType<T> {
	
	public static FieldType<Integer> INTEGER = new IntegerFieldType();
	public static FieldType<String> TEXT = new StringFieldType();
	public static FieldType<Boolean> BOOLEAN = new BooleanFieldType();
	public static FieldType<Calendar> DATE = new DateFieldType();
	public static FieldType<Double> REAL = new RealFieldType();
	
	Class<? extends T> getJavaCorrespondingType();

	String getSQLiteTypeName();

	String formatToSQL(T value);
	
}
