package br.zero.controlefinanceiro.modelo;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOInfo;

@DAOInfo(persistenceUnitName = "ControleFinanceiro", entityClass = Lancamento.class)
public class LancamentoDAO extends CustomDAO<Lancamento> {

}
