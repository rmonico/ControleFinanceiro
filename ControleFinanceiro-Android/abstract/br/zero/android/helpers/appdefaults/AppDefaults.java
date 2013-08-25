package br.zero.android.helpers.appdefaults;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDefaults implements HelperFactory {
	
	public static final AppDefaults get = new AppDefaults();
	private Context appContext;
	private HelperFactory helperFactory;
	
	public void setAppContext(Context appContext) {
		this.appContext = appContext;
	}
	
	public Context getAppContext() {
		return appContext;
	}

	public void setHelperFactory(HelperFactory value) {
		helperFactory = value;
	}
	
	public SQLiteOpenHelper createHelper() {
		return helperFactory.createHelper();
	}

}
