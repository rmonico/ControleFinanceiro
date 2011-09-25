package br.zero.controlefinanceiro.comum;

import br.zero.dbutils.CustomDAO;

public class FormaPagamentoDAO extends CustomDAO<FormaPagamento> {
	
    @Override
    protected Class<FormaPagamento> getEntityClass() {
        return FormaPagamento.class;
    }
}
