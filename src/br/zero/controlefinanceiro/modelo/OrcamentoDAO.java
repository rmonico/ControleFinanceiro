package br.zero.controlefinanceiro.modelo;

import br.zero.controlefinanceiro.customdao.ControleFinanceiroDAO;

public class OrcamentoDAO extends ControleFinanceiroDAO<Orcamento> {

    @Override
    protected Class<Orcamento> getEntityClass() {
        return Orcamento.class;
    }

}
