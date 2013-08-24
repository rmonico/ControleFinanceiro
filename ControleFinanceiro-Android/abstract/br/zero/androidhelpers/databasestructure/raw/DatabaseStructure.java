package br.zero.androidhelpers.databasestructure.raw;

import java.util.List;

public interface DatabaseStructure {
	
	String getName();
	
	Integer getVersion();
	
	void populateRawObjectStructures(List<RawDatabaseObjectStructure> objects);
	
}
