package br.zero.controlefinanceiro.modelo;

import br.zero.customdao.CustomDAO;

public class LancamentoDAO extends CustomDAO<Lancamento> {

	@Override
	protected Class<Lancamento> getEntityClass() {
		return Lancamento.class;
	}

}
