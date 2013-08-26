package br.zero.androidhelpers.databasestructure.table;


public class ForeignFieldType<T> implements FieldType<T> {
	
	private TableStructure<T> structure;

	public ForeignFieldType(TableStructure<T> structure) {
		this.structure = structure;
	}

	public Class<? extends T> getJavaCorrespondingType() {
		return structure.getBeanClass();
	}

	public String getSQLiteTypeName() {
		return "integer";
	}

	public String formatToSQL(T value) {
		return null;
	}

}
