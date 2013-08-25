package br.zero.androidhelpers.databasestructure.raw;

import br.zero.fin.Application;
import br.zero.fin.database.ControleFinanceiroDatabaseStructure;

public class ControleFinanceiroDBHelper extends AbstractDBHelper {

	public ControleFinanceiroDBHelper() {
		super(Application.getAppContext(), ControleFinanceiroDatabaseStructure.get);
	}

}
