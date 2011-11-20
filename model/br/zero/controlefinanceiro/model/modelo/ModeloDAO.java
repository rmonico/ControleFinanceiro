package br.zero.controlefinanceiro.model.modelo;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Modelo.class)
public class ModeloDAO extends CustomDAO<Modelo> {

}
