package br.zero.controlefinanceiro.customdao;

import br.zero.customdao.CustomDAO;

public abstract class ControleFinanceiroDAO<T> extends CustomDAO<T> {

	@Override
	protected String getPersistenceUnitName() {
		return "ControleFinanceiro";
	}

}
