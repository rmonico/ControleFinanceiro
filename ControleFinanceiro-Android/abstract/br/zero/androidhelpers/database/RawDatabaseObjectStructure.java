package br.zero.androidhelpers.database;

import java.util.List;

public interface RawDatabaseObjectStructure {
	
	void populateObjectCreationSQLs(List<String> sqls);
	
	void populateUpgradeSQL(List<String> sqls, int oldVersion, int newVersion);
}
