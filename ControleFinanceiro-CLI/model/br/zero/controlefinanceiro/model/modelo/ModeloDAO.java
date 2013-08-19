package br.zero.controlefinanceiro.model.modelo;

import java.util.List;

import javax.persistence.Query;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Modelo.class)
public class ModeloDAO extends CustomDAO<Modelo> {

	public Modelo getByNome(String nomeModelo) {
		Query q = getEntityManager().createQuery("select m from Modelo m where m.nome = :nome");
		q.setParameter("nome", nomeModelo);

		@SuppressWarnings("unchecked")
		List<Modelo> results = q.getResultList();

		if (results.size() > 0) {
			return results.get(0);
		} else {
			return null;
		}
		
	}

}
