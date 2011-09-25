package br.zero.controlefinanceiro.comum;

import br.zero.controlefinanceiro.customdao.ControleFinanceiroDAO;


public class FormaPagamentoDAO extends ControleFinanceiroDAO<FormaPagamento> {
	
    @Override
    protected Class<FormaPagamento> getEntityClass() {
        return FormaPagamento.class;
    }
}
