package br.zero.controlefinanceiro.model.modelo;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Orcamento.class)
public class OrcamentoDAO extends CustomDAO<Orcamento> {

}
