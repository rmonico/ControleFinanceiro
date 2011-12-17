package br.zero.controlefinanceiro.model.extrato;

import java.util.List;

import javax.persistence.Query;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Lancamento.class)
public class ReferenciaExtratoDAO extends CustomDAO<ReferenciaExtrato> {

	public List<ReferenciaExtrato> listarPorContaEBanco(Conta conta, Conta banco) {
		StringBuilder listarPorContaEBancoQuery = new StringBuilder();

		listarPorContaEBancoQuery.append("select\n");
		listarPorContaEBancoQuery.append("  re\n");
		listarPorContaEBancoQuery.append("\n");
		listarPorContaEBancoQuery.append("from\n");
		listarPorContaEBancoQuery.append("  ReferenciaExtrato re\n");
		listarPorContaEBancoQuery.append("\n");
		listarPorContaEBancoQuery.append("where\n");
		listarPorContaEBancoQuery.append("  re.conta = :conta\n");
		listarPorContaEBancoQuery.append("  and re.banco = :banco\n");

		Query q = getEntityManager().createQuery(listarPorContaEBancoQuery.toString());

		q.setParameter("conta", conta);
		q.setParameter("banco", banco);

		@SuppressWarnings("unchecked")
		List<ReferenciaExtrato> results = q.getResultList();

		return results;
	}

	public int getNextNFor(Conta conta, Conta banco) {
		StringBuilder getNextNQuery = new StringBuilder();

		getNextNQuery.append("select\n");
		getNextNQuery.append("  max(re.n)\n");
		getNextNQuery.append("\n");
		getNextNQuery.append("from\n");
		getNextNQuery.append("  ReferenciaExtrato re\n");
		getNextNQuery.append("\n");
		getNextNQuery.append("where\n");
		getNextNQuery.append("  re.conta = :conta\n");
		getNextNQuery.append("  and re.banco = :banco\n");

		Query q = getEntityManager().createQuery(getNextNQuery.toString());

		q.setParameter("conta", conta);
		q.setParameter("banco", banco);

		Integer result = (Integer) q.getSingleResult();

		if (result == null) {
			result = 0;
		}

		result++;

		return result;
	}

}
