package br.zero.controlefinanceiro.modelo;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Orcamento.class)
public class OrcamentoDAO extends CustomDAO<Orcamento> {

}
