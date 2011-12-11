package br.zero.controlefinanceiro.model;

import java.util.List;

import javax.persistence.Query;

import br.zero.controlefinanceiro.model.extrato.ReferenciaExtrato;
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

}
