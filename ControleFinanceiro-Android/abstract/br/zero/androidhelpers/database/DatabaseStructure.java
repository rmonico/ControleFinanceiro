package br.zero.androidhelpers.database;

import java.util.List;

public interface DatabaseStructure {
	
	String getName();
	
	Integer getVersion();
	
	void populateRawObjectStructures(List<DatabaseRawObjectStructure> objects);
	
}
