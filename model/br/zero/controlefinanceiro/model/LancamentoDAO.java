package br.zero.controlefinanceiro.model;

import java.util.List;

import javax.persistence.Query;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Lancamento.class)
public class LancamentoDAO extends CustomDAO<Lancamento> {

	public List<Lancamento> listarPorConta(Conta conta) {
		StringBuilder listarPorContaQuery = new StringBuilder();
		
		listarPorContaQuery.append("select\n");
		listarPorContaQuery.append("  lancamento\n");
		listarPorContaQuery.append("\n");
		listarPorContaQuery.append("from\n");
		listarPorContaQuery.append("  Lancamento lancamento\n");
		listarPorContaQuery.append("\n");
		listarPorContaQuery.append("where\n");
		listarPorContaQuery.append("  lancamento.contaOrigem = :conta\n");
		listarPorContaQuery.append("  or lancamento.contaDestino = :conta\n");
		
		
		Query q = getEntityManager().createQuery(listarPorContaQuery.toString());
		
		q.setParameter("conta", conta);

		@SuppressWarnings("unchecked")
		List<Lancamento> results = q.getResultList();
		
		return results;
	}

}
