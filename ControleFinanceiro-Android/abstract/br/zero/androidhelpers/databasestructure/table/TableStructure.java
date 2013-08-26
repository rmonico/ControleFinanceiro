package br.zero.androidhelpers.databasestructure.table;

public interface TableStructure {
	String getName();
	
	int[] getPKFieldsIndexes();

	Field[] getFields();
}
