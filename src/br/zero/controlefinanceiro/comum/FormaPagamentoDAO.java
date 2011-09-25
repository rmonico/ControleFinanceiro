package br.zero.controlefinanceiro.comum;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOInfo;

@DAOInfo(persistenceUnitName = "ControleFinanceiro", entityClass = FormaPagamento.class)
public class FormaPagamentoDAO extends CustomDAO<FormaPagamento> {

}
