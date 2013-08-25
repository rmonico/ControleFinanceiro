package br.zero.fin;

import android.database.sqlite.SQLiteOpenHelper;
import br.zero.android.helpers.appdefaults.HelperFactory;
import br.zero.androidhelpers.databasestructure.raw.ControleFinanceiroDBHelper;

public class ControleFinanceiroHelperFactory implements HelperFactory {

	public SQLiteOpenHelper createHelper() {
		return new ControleFinanceiroDBHelper();
	}

}
