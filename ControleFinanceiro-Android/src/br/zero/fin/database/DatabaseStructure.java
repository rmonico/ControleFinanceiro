package br.zero.fin.database;

public interface DatabaseStructure {

	public String getName();
	
	public Integer getVersion();
	
	public String getDatabaseCreationSQL();
	
	public String getUpgradeSQL(int oldVersion, int newVersion);
}
