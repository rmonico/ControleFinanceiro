package br.zero.controlefinanceiro.comum;

import br.zero.customdao.CustomDAO;
import br.zero.customdao.DAOInfo;

@DAOInfo(persistenceUnitName = "ControleFinanceiro", entityClass = Conta.class)
public class ContaDAO extends CustomDAO<Conta> {

}
