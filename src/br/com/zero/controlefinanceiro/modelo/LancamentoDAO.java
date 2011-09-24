package br.com.zero.controlefinanceiro.modelo;

import br.com.zero.library.dao.CustomDAO;

public class LancamentoDAO extends CustomDAO<Lancamento> {

	@Override
	protected Class<Lancamento> getEntityClass() {
		return Lancamento.class;
	}

}
