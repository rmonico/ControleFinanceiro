package br.zero.androidhelpers.databasestructure.table;

public abstract class AbstractTableStructure implements TableStructure {
	private Field[] fields;

	public abstract String getName();

	public Field[] getFields() {
		return fields != null ? fields : (fields = createFields());
	}

	protected abstract Field[] createFields();

}
