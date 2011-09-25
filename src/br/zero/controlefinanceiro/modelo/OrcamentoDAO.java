package br.zero.controlefinanceiro.modelo;

import br.zero.dbutils.CustomDAO;

public class OrcamentoDAO extends CustomDAO<Orcamento> {

    @Override
    protected Class<Orcamento> getEntityClass() {
        return Orcamento.class;
    }

}
