package br.zero.controlefinanceiro.model.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.zero.customdao.EntitySetup;


/**
 * The persistent class for the orcamento database table.
 * 
 */
@Entity
@Table(schema = "controlefinanceiro_modelo")
@NamedQueries({
    @NamedQuery(name = "findAllOrcamentos",
    query = "select o from Orcamento o order by o.nome"),
    @NamedQuery(name = "findOrcamentoById",
    query = "select o from Orcamento o where o.id=:id")})
@EntitySetup(findAllQueryName = "findAllOrcamentos", findByIdQueryName = "findOrcamentoById", idFieldName = "id")
public class Orcamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORCAMENTO_ID_GENERATOR", sequenceName="CONTROLEFINANCEIRO_MODELO.ORCAMENTO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORCAMENTO_ID_GENERATOR")
	private Integer id;

	private String nome;

    public Orcamento() {
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