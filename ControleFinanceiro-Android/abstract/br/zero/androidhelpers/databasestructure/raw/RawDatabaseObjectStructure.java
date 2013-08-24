package br.zero.androidhelpers.databasestructure.raw;

import java.util.List;

public interface RawDatabaseObjectStructure {
	
	// TODO Fazer devolver uma lista de strings
	void populateObjectCreationSQLs(List<String> sqls);
	
	void populateUpgradeSQL(List<String> sqls, int oldVersion, int newVersion);
}
