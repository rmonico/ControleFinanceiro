package br.zero.controlefinanceiro.modelo;

import br.zero.controlefinanceiro.customdao.ControleFinanceiroDAO;

public class LancamentoDAO extends ControleFinanceiroDAO<Lancamento> {

	@Override
	protected Class<Lancamento> getEntityClass() {
		return Lancamento.class;
	}

}
