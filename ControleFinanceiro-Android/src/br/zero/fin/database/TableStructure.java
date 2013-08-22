package br.zero.fin.database;

import java.util.List;

public interface TableStructure {

	public String getDatabaseCreationSQL();
	
	public String getUpgradeSQL(int oldVersion, int newVersion);
	
	public void getPopulationSQLs(List<String> list);
}
