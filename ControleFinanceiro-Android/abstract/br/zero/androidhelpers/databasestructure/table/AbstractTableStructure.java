package br.zero.androidhelpers.databasestructure.table;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTableStructure<T> implements TableStructure<T> {
	
	private Field[] fields;
	public final Field _id;
	private Class<T> beanClass;
	
	public AbstractTableStructure(Class<T> beanClass) throws DatabaseStructureException {
		super();
		
		this.beanClass = beanClass;
		_id = Field.new_IdField(beanClass);
	}

	public int[] getPKFieldsIndexes() {
		return new int[] { 0 };
	}
	
	public abstract String getName();

	public Field[] getFields() {
		return fields == null ? fields = createFields() : fields;
	}
	
	public String[] getFieldNames() {
		List<String> fieldNames = new ArrayList<String>();
		
		for (Field field : getFields()) {
			fieldNames.add(field.getName());
		}
		
		return fieldNames.toArray(new String[] {});
	}

	protected abstract Field[] createFields();
	
	public Class<T> getBeanClass() {
		return beanClass;
	}

}
