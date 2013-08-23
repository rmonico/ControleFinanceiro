package br.zero.fin;

import android.content.Context;

public class Application extends android.app.Application {

	private static Context context;

	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
	}

	public static Context getAppContext() {
		return Application.context;
	}
}
