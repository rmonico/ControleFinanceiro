package br.zero.androidhelpers.databasestructure.table;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTableStructure implements TableStructure {
	private Field[] fields;

	public abstract String getName();

	public Field[] getFields() {
		return fields != null ? fields : (fields = createFields());
	}
	
	public String[] getFieldNames() {
		List<String> fieldNames = new ArrayList<String>();
		
		for (Field field : fields) {
			fieldNames.add(field.getName());
		}
		
		return fieldNames.toArray(new String[] {});
	}

	protected abstract Field[] createFields();

}
