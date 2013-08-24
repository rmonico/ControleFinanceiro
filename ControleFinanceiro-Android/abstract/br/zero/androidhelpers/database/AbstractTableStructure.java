package br.zero.androidhelpers.database;

public abstract class AbstractTableStructure implements TableStructure {
	private FieldStructure[] fields;

	public abstract String getName();

	public FieldStructure[] getFields() {
		return fields != null ? fields : (fields = createFields());
	}

	protected abstract FieldStructure[] createFields();

}
