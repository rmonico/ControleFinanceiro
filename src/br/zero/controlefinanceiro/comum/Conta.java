package br.zero.controlefinanceiro.comum;

import br.zero.library.dao.DAOSetup;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the conta database table.
 * 
 */
@Entity
@Table(schema = "controlefinanceiro_comum")
@NamedQueries({
    @NamedQuery(name = "findAllContas",
    query = "select c from Conta c order by c.nome"),
    @NamedQuery(name = "findContaById",
    query = "select c from Conta c order by c.nome")})
@DAOSetup(findAllQueryName = "findAllContas", findByIdQueryName = "findContaById", idFieldName = "id")
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "CONTA_ID_GENERATOR", sequenceName = "controlefinanceiro_comum.CONTA_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTA_ID_GENERATOR")
    private Integer id;
    private String nome;

    public Conta() {
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