package br.zero.androidhelpers.databasestructure.table;

import java.util.List;

import br.zero.androidhelpers.databasestructure.raw.RawDatabaseObjectStructure;

public class TableToRawSQLAdapter implements RawDatabaseObjectStructure {

	private TableStructure table;
	private List<? extends Object> initialData;

	public TableToRawSQLAdapter(TableStructure table) {
		this(table, null);
	}
	
	public TableToRawSQLAdapter(TableStructure table, List<? extends Object> initialData) {
		this.initialData = initialData;
	}

	public void populateObjectCreationSQLs(List<String> sqls) {
		sqls.add("create table " + table.getName());

		if (table.getFields().length > 0) {
			putFields(sqls, table.getFields());
		}
		
		sqls.add(";");
		
		populateInitialData(sqls);
	}

	private void putFields(List<String> sqls, Field[] fields) {
		StringBuilder s = new StringBuilder("(");
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			s.append(field.getName() + " " + field.getType().toString());
			
			FieldModifier[] modifiers = field.getModifiers();
			
			if ((modifiers != null) && (modifiers.length > 0)) {
				putModifiers(s, modifiers);
			}
			
			if (i < fields.length - 1) {
				s.append(",\n");
			}
			
			sqls.add(s.toString());
		}
		s.append(")");
	}

	private void putModifiers(StringBuilder s, FieldModifier[] modifiers) {
		for (FieldModifier modifier : modifiers) {
			s.append(" ");
			s.append(modifier.toString());
		}
	}

	private void populateInitialData(List<String> sqls) {
		// TODO Auto-generated method stub
		
	}
	
	public void populateUpgradeSQL(List<String> sqls, int oldVersion,
			int newVersion) {
		// TODO Auto-generated method stub

	}

}
