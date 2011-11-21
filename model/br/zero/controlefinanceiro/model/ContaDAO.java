package br.zero.controlefinanceiro.model;

import java.util.List;

import javax.persistence.Query;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Conta.class)
public class ContaDAO extends CustomDAO<Conta> {

	public Conta getByNome(String nomeConta) {
		Query q = getEntityManager().createQuery("select c from Conta c where c.nome = :nome");
		q.setParameter("nome", nomeConta);

		@SuppressWarnings("unchecked")
		List<Conta> results = q.getResultList();

		if (results.size() > 0) {
			return results.get(0);
		} else {
			return null;
		}
	}

}
