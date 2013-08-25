package br.zero.fin;

import br.zero.android.helpers.appdefaults.AppDefaults;

public class Application extends android.app.Application {

	public void onCreate() {
		super.onCreate();
		
		AppDefaults defaults = AppDefaults.get;
		
		defaults.setAppContext(getApplicationContext());
		defaults.setHelperFactory(new ControleFinanceiroHelperFactory());
	}

}
