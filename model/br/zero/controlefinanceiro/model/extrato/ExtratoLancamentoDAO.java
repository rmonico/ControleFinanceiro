package br.zero.controlefinanceiro.model.extrato;

import java.util.List;

import javax.persistence.Query;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = ExtratoLancamento.class)
public class ExtratoLancamentoDAO extends CustomDAO<ExtratoLancamento> {

	public List<ExtratoLancamento> listarPorConta(String nomeConta) {
		StringBuilder listarPorContaQuery = new StringBuilder();

		listarPorContaQuery.append("select\n");
		listarPorContaQuery.append("  e\n");
		listarPorContaQuery.append("\n");
		listarPorContaQuery.append("from\n");
		listarPorContaQuery.append("  ExtratoLancamento e\n");
		listarPorContaQuery.append("\n");
		listarPorContaQuery.append("where\n");
		listarPorContaQuery.append("  e.banco.nome = :nomeconta\n");

		Query q = getEntityManager().createQuery(listarPorContaQuery.toString());

		q.setParameter("nomeconta", nomeConta);

		@SuppressWarnings("unchecked")
		List<ExtratoLancamento> results = q.getResultList();

		return results;
	}

}
