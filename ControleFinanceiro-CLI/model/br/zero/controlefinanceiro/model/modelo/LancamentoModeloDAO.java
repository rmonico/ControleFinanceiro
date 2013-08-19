package br.zero.controlefinanceiro.model.modelo;

import java.util.List;

import javax.persistence.Query;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = LancamentoModelo.class)
public class LancamentoModeloDAO extends CustomDAO<LancamentoModelo> {

	public List<LancamentoModelo> listarPorModelo(String nomeModelo) {
		Query q = getEntityManager().createQuery("select lm from LancamentoModelo lm where lm.modelo.nome = :nomeModelo order by lm.diaVencimento");
		q.setParameter("nomeModelo", nomeModelo);

		@SuppressWarnings("unchecked")
		List<LancamentoModelo> resultList = (List<LancamentoModelo>) q.getResultList();
		
		return resultList;
	}

}
