package br.zero.fin.database;

import java.util.List;

public interface DatabaseStructure {
	
	public static final DatabaseStructure instance = new DefaultDatabaseStructure();

	public String getName();
	
	public Integer getVersion();
	
	public List<TableStructure> getTables();
	
}
