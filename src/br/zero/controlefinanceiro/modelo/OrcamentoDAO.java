package br.zero.controlefinanceiro.modelo;

import br.zero.customdao.CustomDAO;

public class OrcamentoDAO extends CustomDAO<Orcamento> {

    @Override
    protected Class<Orcamento> getEntityClass() {
        return Orcamento.class;
    }

}
