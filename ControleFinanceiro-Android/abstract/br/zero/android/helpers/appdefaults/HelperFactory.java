package br.zero.android.helpers.appdefaults;

import android.database.sqlite.SQLiteOpenHelper;

public interface HelperFactory {

	SQLiteOpenHelper createHelper();
	
}
