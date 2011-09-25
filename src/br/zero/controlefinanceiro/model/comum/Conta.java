package br.zero.controlefinanceiro.model.comum;

import br.zero.customdao.EntitySetup;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the conta database table.
 * 
 */
@Entity
@Table(schema = "controlefinanceiro_comum")
@EntitySetup(findAllQuery = "select c from Conta c order by c.nome", findByIdQuery = "select c from Conta c where l.id=:id", idFieldName = "id")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO_COMUM.CONTA_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO_COMUM.CONTA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLEFINANCEIRO_COMUM.CONTA_ID_SEQ")
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