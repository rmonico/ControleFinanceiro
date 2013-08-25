package br.zero.androidhelpers.databasestructure.table;

import java.util.ArrayList;
import java.util.List;

import br.zero.androidhelpers.databasestructure.raw.RawDatabaseObjectStructure;

public class TableToRawSQLAdapter implements RawDatabaseObjectStructure {

	private TableStructure table;
	private List<? extends Object> initialData;

	public TableToRawSQLAdapter(TableStructure table) {
		this(table, null);
	}
	
	public TableToRawSQLAdapter(TableStructure table, List<? extends Object> initialData) {
		this.table = table;
		this.initialData = initialData;
	}

	public List<String> getObjectCreationSQLs() {
		List<String> sqls = new ArrayList<String>();
		
		sqls.add(getCreateTableStatement());
		
		createInitialDataStatements(sqls);
		
		return sqls;
	}

	private String getCreateTableStatement() {
		StringBuilder s = new StringBuilder();
		
		s.append("create table " + table.getName());

		if (table.getFields().length > 0) {
			putFields(s, table.getFields());
		}
		
		s.append(";");
		
		return s.toString();
	}

	private void putFields(StringBuilder s, Field[] fields) {
		s.append("(");
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			s.append(field.getName() + " " + field.getType().toString());
			
			FieldModifier[] modifiers = field.getModifiers();
			
			if ((modifiers != null) && (modifiers.length > 0)) {
				putModifiers(s, modifiers);
			}
			
			if (i < fields.length - 1) {
				s.append(", ");
			}
		}
		
		s.append(")");
	}

	private void putModifiers(StringBuilder s, FieldModifier[] modifiers) {
		for (FieldModifier modifier : modifiers) {
			s.append(" ");
			s.append(modifier.toString());
		}
	}

	private void createInitialDataStatements(List<String> sqls) {
		for (Object obj : initialData) {
			
		}
		
	}

	public List<String> getUpgradeSQL(int oldVersion, int newVersion) {
		return null;
	}

}
