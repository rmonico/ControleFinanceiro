package br.zero.controlefinanceiro.model;

import java.util.Calendar;
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

	public int getNextN(Calendar data) {
		StringBuilder selectMaxNQuery = new StringBuilder();

		selectMaxNQuery.append("select\n");
		selectMaxNQuery.append("  max(lancamento.n)\n");
		selectMaxNQuery.append("\n");
		selectMaxNQuery.append("from\n");
		selectMaxNQuery.append("  Lancamento lancamento\n");
		selectMaxNQuery.append("\n");
		selectMaxNQuery.append("where\n");
		selectMaxNQuery.append("  lancamento.data = :data");

		Query q = getEntityManager().createQuery(selectMaxNQuery.toString());

		q.setParameter("data", data);

		Integer nextN = (Integer) q.getSingleResult();
		
		nextN = (nextN == null) ? 0 : nextN;
		
		nextN++;

		return nextN;
	}

	public List<Lancamento> listarSemModeloPorData(Calendar dataInicio, Calendar dataFim) {
		StringBuilder listarPorContaQuery = new StringBuilder();

		listarPorContaQuery.append("select\n");
		listarPorContaQuery.append("  lancamento\n");
		listarPorContaQuery.append("\n");
		listarPorContaQuery.append("from\n");
		listarPorContaQuery.append("  Lancamento lancamento\n");
		listarPorContaQuery.append("\n");
		listarPorContaQuery.append("where\n");
		listarPorContaQuery.append("  lancamento.data >= :datainicio\n");
		listarPorContaQuery.append("  and lancamento.data <= :datafim\n");
		listarPorContaQuery.append("  and lancamento.lancamentoModelo is null\n");

		Query q = getEntityManager().createQuery(listarPorContaQuery.toString());

		q.setParameter("datainicio", dataInicio);
		q.setParameter("datafim", dataFim);

		@SuppressWarnings("unchecked")
		List<Lancamento> results = q.getResultList();

		return results;
	}

	public List<Lancamento> listarSemExtrato(Conta banco) {
		StringBuilder listarSemExtratoQuery = new StringBuilder();

		listarSemExtratoQuery.append("select\n");
		listarSemExtratoQuery.append("  l\n");
		listarSemExtratoQuery.append("\n");
		listarSemExtratoQuery.append("from\n");
		listarSemExtratoQuery.append("  Lancamento l\n");
		listarSemExtratoQuery.append("\n");
		listarSemExtratoQuery.append("where\n");
		listarSemExtratoQuery.append("  l.contaOrigem = :banco\n");
		listarSemExtratoQuery.append("  or l.contaDestino = :banco\n");

		Query q = getEntityManager().createQuery(listarSemExtratoQuery.toString());
		
		q.setParameter("banco", banco);

		@SuppressWarnings("unchecked")
		List<Lancamento> results = q.getResultList();

		return results;
	}

}
