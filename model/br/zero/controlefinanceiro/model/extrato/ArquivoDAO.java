package br.zero.controlefinanceiro.model.extrato;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Arquivo.class)
public class ArquivoDAO extends CustomDAO<Arquivo> {

}
