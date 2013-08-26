package br.zero.androidhelpers.databasestructure.raw;

import java.util.List;

import br.zero.androidhelpers.databasestructure.table.DatabaseStructureException;

public interface RawDatabaseObjectStructure {

	List<String> getObjectCreationSQLs() throws DatabaseStructureException;

	List<String> getUpgradeSQL(int oldVersion, int newVersion) throws DatabaseStructureException;
}
