package br.zero.androidhelpers.databasestructure.table;

abstract class ConcreteFieldType<T> implements FieldType<T> {
	private String sqliteTypeName;
	private Class<? extends T> javaCorrespondingType;

	ConcreteFieldType(String sqliteTypeName, Class<? extends T> javaCorrespondingType) {
		this.sqliteTypeName = sqliteTypeName;
		this.javaCorrespondingType = javaCorrespondingType;
	}
	
	public String getSQLiteTypeName() {
		return sqliteTypeName;
	}
	
	public Class<? extends T> getJavaCorrespondingType() {
		return javaCorrespondingType;
	}

	public abstract String formatToSQL(T value);
	
}