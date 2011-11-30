package br.zero.controlefinanceiro.model.modelo;

import java.util.List;

import javax.persistence.Query;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOSetup;

@DAOSetup(persistenceUnitName = "ControleFinanceiro", entityClass = LancamentoModelo.class)
public class LancamentoModeloDAO extends CustomDAO<LancamentoModelo> {

	public List<LancamentoModelo> listarPorModelo(Modelo modelo) {
		Query q = getEntityManager().createQuery("select lm from LancamentoModelo lm where lm.modelo = :modelo");
		q.setParameter("modelo", modelo);

		@SuppressWarnings("unchecked")
		List<LancamentoModelo> resultList = (List<LancamentoModelo>) q.getResultList();
		
		return resultList;
	}

}
