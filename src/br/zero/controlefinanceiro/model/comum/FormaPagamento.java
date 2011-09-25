package br.zero.controlefinanceiro.model.comum;

import br.zero.customdao.EntitySetup;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the formapagamento database table.
 * 
 */
@Entity
@Table(schema = "controlefinanceiro_comum")
@EntitySetup(findAllQuery = "select f from FormaPagamento f order by f.nome", findByIdQuery = "select f from FormaPagamento f where f.id=:id", idFieldName = "id")
public class FormaPagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO_COMUM.FORMAPAGAMENTO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO_COMUM.FORMAPAGAMENTO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLEFINANCEIRO_COMUM.FORMAPAGAMENTO_ID_SEQ")
	private Integer id;
	private String nome;

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