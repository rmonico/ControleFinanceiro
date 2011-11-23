package br.zero.controlefinanceiro.model;

import javax.persistence.Query;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Conta.class)
public class ContaDAO extends CustomDAO<Conta> {

	public Conta getByNome(String nomeConta) {
		Query q = getEntityManager().createQuery("select c from Conta c where c.nome = :nome");
		q.setParameter("nome", nomeConta);

		return (Conta) q.getSingleResult();
	}

}
