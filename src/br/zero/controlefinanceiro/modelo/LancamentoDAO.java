package br.zero.controlefinanceiro.modelo;

import br.zero.dbutils.CustomDAO;

public class LancamentoDAO extends CustomDAO<Lancamento> {

	@Override
	protected Class<Lancamento> getEntityClass() {
		return Lancamento.class;
	}

}
