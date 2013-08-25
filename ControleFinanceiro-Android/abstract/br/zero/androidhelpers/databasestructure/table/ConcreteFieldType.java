package br.zero.androidhelpers.databasestructure.table;

class ConcreteFieldType implements FieldType {
	private String sqliteTypeName;
	private Class<?> javaCorrespondingType;

	ConcreteFieldType(String sqliteTypeName, Class<?> javaCorrespondingType) {
		this.sqliteTypeName = sqliteTypeName;
		this.javaCorrespondingType = javaCorrespondingType;
	}
	
	public String getSQLiteTypeName() {
		return sqliteTypeName;
	}
	
	public Class<?> getJavaCorrespondingType() {
		return javaCorrespondingType;
	}
	
}