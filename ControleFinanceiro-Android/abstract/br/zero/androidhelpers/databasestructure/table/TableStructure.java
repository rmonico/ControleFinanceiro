package br.zero.androidhelpers.databasestructure.table;

public interface TableStructure<T> {
	String getName();
	
	int[] getPKFieldsIndexes();

	Field[] getFields();
	
	Class<? extends T> getBeanClass();
}
