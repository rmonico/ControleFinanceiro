package br.zero.controlefinanceiro.comum;

import br.zero.library.dao.DAOSetup;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the formapagamento database table.
 * 
 */
@Entity
@Table(schema = "controlefinanceiro_comum")
@NamedQueries({
    @NamedQuery(name = "findAllFormasPagamento",
    query = "select f from FormaPagamento f order by f.nome"),
    @NamedQuery(name = "findFormaPagamentoById",
    query = "select f from FormaPagamento f where f.id=:id")})
@DAOSetup(findAllQueryName = "findAllFormasPagamento", findByIdQueryName = "findFormaPagamentoById", idFieldName = "id")
public class FormaPagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "FORMAPAGAMENTO_ID_GENERATOR", sequenceName = "CONTROLEFINANCEIRO_COMUM.FORMAPAGAMENTO_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMAPAGAMENTO_ID_GENERATOR")
    private Integer id;
    private String nome;

    public FormaPagamento() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}