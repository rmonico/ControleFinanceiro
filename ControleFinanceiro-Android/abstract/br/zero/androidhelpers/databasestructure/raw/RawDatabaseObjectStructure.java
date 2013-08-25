package br.zero.androidhelpers.databasestructure.raw;

import java.util.List;

public interface RawDatabaseObjectStructure {

	List<String> getObjectCreationSQLs();

	List<String> getUpgradeSQL(int oldVersion, int newVersion);
}
