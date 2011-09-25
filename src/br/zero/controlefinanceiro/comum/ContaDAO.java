package br.zero.controlefinanceiro.comum;

import br.zero.controlefinanceiro.customdao.ControleFinanceiroDAO;


public class ContaDAO extends ControleFinanceiroDAO<Conta> {

    /**
     * Não poderia colocar no CustomDAO T.class, pois essa informação é resolvida em tempo de compilação.
     * @return 
     */
    @Override
    protected Class<Conta> getEntityClass() {
        return Conta.class;
    }
}
