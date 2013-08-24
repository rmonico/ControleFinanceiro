package br.zero.androidhelpers.database;

import java.util.List;

public interface DatabaseRawObjectStructure {
	
	void populateObjectCreationSQLs(List<String> sqls);
	
	void populateUpgradeSQL(List<String> sqls, int oldVersion, int newVersion);
}
