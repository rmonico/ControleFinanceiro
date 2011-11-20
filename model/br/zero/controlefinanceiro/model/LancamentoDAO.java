package br.zero.controlefinanceiro.model;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Lancamento.class)
public class LancamentoDAO extends CustomDAO<Lancamento> {

}
