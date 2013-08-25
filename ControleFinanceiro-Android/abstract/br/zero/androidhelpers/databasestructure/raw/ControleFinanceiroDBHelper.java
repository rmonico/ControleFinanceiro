package br.zero.androidhelpers.databasestructure.raw;

import br.zero.android.helpers.appdefaults.AppDefaults;
import br.zero.fin.database.ControleFinanceiroDatabaseStructure;

public class ControleFinanceiroDBHelper extends AbstractDBHelper {

	public ControleFinanceiroDBHelper() {
		super(AppDefaults.get.getAppContext(), ControleFinanceiroDatabaseStructure.get);
	}

}
