package br.zero.androidhelpers.databasestructure.raw;

import java.util.List;

public interface RawDatabaseObjectStructure {

	List<String> populateObjectCreationSQLs();

	List<String> populateUpgradeSQL(int oldVersion, int newVersion);
}
