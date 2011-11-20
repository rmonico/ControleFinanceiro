package br.zero.controlefinanceiro.model;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Conta.class)
public class ContaDAO extends CustomDAO<Conta> {

	public Conta getByNome(String nomeConta) {
		throw new RuntimeException("Not implemented yet...");
	}

}
