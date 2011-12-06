package br.zero.controlefinanceiro.model.extrato;

import java.util.List;

import javax.persistence.Query;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Extrato.class)
public class ExtratoDAO extends CustomDAO<Extrato> {

	public List<Extrato> listarPorConta(String nomeConta) {
		StringBuilder listarPorContaQuery = new StringBuilder();

		listarPorContaQuery.append("select\n");
		listarPorContaQuery.append("  e\n");
		listarPorContaQuery.append("\n");
		listarPorContaQuery.append("from\n");
		listarPorContaQuery.append("  Extrato e\n");
		listarPorContaQuery.append("\n");
		listarPorContaQuery.append("where\n");
		listarPorContaQuery.append("  e.banco.nome = :nomeconta\n");

		Query q = getEntityManager().createQuery(listarPorContaQuery.toString());

		q.setParameter("nomeconta", nomeConta);

		@SuppressWarnings("unchecked")
		List<Extrato> results = q.getResultList();

		return results;
	}

}
