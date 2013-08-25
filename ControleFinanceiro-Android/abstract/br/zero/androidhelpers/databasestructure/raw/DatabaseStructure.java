package br.zero.androidhelpers.databasestructure.raw;

import java.util.List;

public interface DatabaseStructure {
	
	String getName();
	
	Integer getVersion();
	
	List<RawDatabaseObjectStructure> populateRawObjectStructures();
	
}
