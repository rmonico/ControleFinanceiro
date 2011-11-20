package br.zero.controlefinanceiro.model.modelo;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = LancamentoModelo.class)
public class LancamentoModeloDAO extends CustomDAO<LancamentoModelo> {

}
