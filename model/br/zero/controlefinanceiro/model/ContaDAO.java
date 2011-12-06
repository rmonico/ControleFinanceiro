package br.zero.controlefinanceiro.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.zero.controlefinanceiro.model.extrato.ReferenciaExtrato;
import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = Conta.class)
public class ContaDAO extends CustomDAO<Conta> {

	private static Map<String, ExtratoParser> extratoParsers = new HashMap<String, ExtratoParser>();
	
	public Conta getByNome(String nomeConta) {
		Query q = getEntityManager().createQuery("select c from Conta c where c.nome = :nome");
		q.setParameter("nome", nomeConta);

		return (Conta) q.getSingleResult();
	}
	
	public static void registerExtratoParser(String parserName, ExtratoParser extratoParser) {
		extratoParsers.put(parserName, extratoParser);
	}
	
	static ExtratoParser getParser(Conta conta) {
		return extratoParsers.get(conta.getNome());
	}
	
	public List<String> getReferenciaExtratoList(Conta banco, Conta conta) {
		StringBuilder listaReferenciaExtratoList = new StringBuilder();

		listaReferenciaExtratoList.append("select\n");
		listaReferenciaExtratoList.append("  re\n");
		listaReferenciaExtratoList.append("\n");
		listaReferenciaExtratoList.append("from\n");
		listaReferenciaExtratoList.append("  ReferenciaExtrato re\n");
		listaReferenciaExtratoList.append("\n");
		listaReferenciaExtratoList.append("where\n");
		listaReferenciaExtratoList.append("  banco = :banco\n");
		listaReferenciaExtratoList.append("  and conta = :conta\n");

		Query q = getEntityManager().createQuery(listaReferenciaExtratoList.toString());

		q.setParameter("banco", banco);
		q.setParameter("conta", conta);

		@SuppressWarnings("unchecked")
		List<ReferenciaExtrato> referencias = q.getResultList();
		List<String> results = new ArrayList<String>();
		
		for (ReferenciaExtrato re : referencias) {
			results.add(re.getReferencia());
		}

		return results;
	}
}
