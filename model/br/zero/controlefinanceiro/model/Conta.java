package br.zero.controlefinanceiro.model;

import br.zero.customdao.EntitySetup;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the conta database table.
 * 
 */
@Entity
@Table(schema = "controlefinanceiro")
@EntitySetup(findAllQuery = "select c from Conta c order by c.nome", findByIdQuery = "select c from Conta c where c.id=:id", idFieldName = "id")
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO.CONTA_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO.CONTA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLEFINANCEIRO.CONTA_ID_SEQ")
	private Integer id;
	private String nome;
	private Boolean contabilizavel;

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

	public Boolean getContabilizavel() {
		return contabilizavel;
	}

	public void setContabilizavel(Boolean contabilizavel) {
		this.contabilizavel = contabilizavel;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Override
	public String toString() {
		String idConta = (id != null) ? id.toString() : "[null]";
		String nomeConta = (nome != null) ? nome : "[null]";
		String observacaoConta = (observacao != null) ? observacao : "[null]";
		
		return "#" + idConta + "," + nomeConta + ", " + observacaoConta;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Conta)) {
			return false;
		}
		
		Conta contaObj = (Conta) obj;
		
		if (id == null) {
			return false;
		}
		
		return id.equals(contaObj.getId());
	}
}