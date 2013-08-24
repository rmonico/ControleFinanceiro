package br.zero.androidhelpers.database;

import java.util.List;

public interface DatabaseRawObjectStructure {
	
	void getObjectCreationSQLs(List<String> sqls);
	
	void getUpgradeSQL(List<String> sqls, int oldVersion, int newVersion);
}
