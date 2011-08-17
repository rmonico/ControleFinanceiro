package br.com.zero.controlefinanceiro.comum;

import br.com.zero.library.dao.CustomDAO;

public class FormaPagamentoDAO extends CustomDAO<FormaPagamento> {
	
    @Override
    protected Class<FormaPagamento> getEntityClass() {
        return FormaPagamento.class;
    }
}
