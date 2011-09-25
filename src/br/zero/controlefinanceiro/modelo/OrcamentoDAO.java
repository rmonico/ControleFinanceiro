package br.zero.controlefinanceiro.modelo;

import br.zero.library.dao.CustomDAO;

public class OrcamentoDAO extends CustomDAO<Orcamento> {

    @Override
    protected Class<Orcamento> getEntityClass() {
        return Orcamento.class;
    }

}
