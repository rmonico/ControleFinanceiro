package br.com.zero.controlefinanceiro.modelo;

import br.com.zero.library.dao.CustomDAO;

public class OrcamentoDAO extends CustomDAO<Orcamento> {

    @Override
    protected Class<Orcamento> getEntityClass() {
        return Orcamento.class;
    }

}
