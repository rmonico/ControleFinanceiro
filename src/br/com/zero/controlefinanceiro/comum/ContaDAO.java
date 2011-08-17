package br.com.zero.controlefinanceiro.comum;

import br.com.zero.library.dao.CustomDAO;

public class ContaDAO extends CustomDAO<Conta> {

    /**
     * Não poderia colocar no CustomDAO T.class, pois essa informação é resolvida em tempo de compilação.
     * @return 
     */
    @Override
    protected Class<Conta> getEntityClass() {
        return Conta.class;
    }
}
