package br.zero.controlefinanceiro.model.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.zero.customdao.EntitySetup;

/**
 * The persistent class for the orcamento database table.
 * 
 */
@Entity
@Table(name="modelo_modelo")
@EntitySetup(findAllQuery = "select o from Modelo o order by o.nome", findByIdQuery = "select o from Modelo o where o.id=:id", idFieldName = "id")
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;

	private String observacao;

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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		String nomeModelo = (nome != null) ? nome : "[null]";
		String observacaoModelo = (observacao != null) ? observacao : "[null]";

		return nomeModelo + ", " + observacaoModelo;
	}

}