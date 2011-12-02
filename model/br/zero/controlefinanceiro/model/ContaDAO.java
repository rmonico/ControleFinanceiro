package br.zero.controlefinanceiro.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

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
	
	{
		ContaDAO.registerExtratoParser("itau", ExtratoParsers.ITAU_EXTRATO_PARSER);
		ContaDAO.registerExtratoParser("santander", ExtratoParsers.SANTANDER_EXTRATO_PARSER);
	}
	
	public static void registerExtratoParser(String parserName, ExtratoParser extratoParser) {
		extratoParsers.put(parserName, extratoParser);
	}
	
	static ExtratoParser getParser(Conta conta) {
		return extratoParsers.get(conta.getNome());
	}
}
