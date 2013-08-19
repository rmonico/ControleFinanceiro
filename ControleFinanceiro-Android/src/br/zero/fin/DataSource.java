package br.zero.fin;

import java.util.List;

import android.database.Cursor;

public interface DataSource<T> {
	String getTableName();
	
	String[] getAllColumns(); 
	
	Cursor getCursorForAll();

	public List<T> getAll();
	
}
