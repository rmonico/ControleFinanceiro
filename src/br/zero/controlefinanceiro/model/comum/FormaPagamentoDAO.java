package br.zero.controlefinanceiro.model.comum;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = FormaPagamento.class)
public class FormaPagamentoDAO extends CustomDAO<FormaPagamento> {

}
