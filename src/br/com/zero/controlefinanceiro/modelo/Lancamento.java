package br.com.zero.controlefinanceiro.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the lancamento database table.
 * 
 */
@Entity
@Table(schema="controlefinanceiro_modelo")
public class Lancamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LANCAMENTO_ID_GENERATOR", sequenceName="CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LANCAMENTO_ID_GENERATOR")
	private Integer id;

	private Integer contadestinoid;

	private Integer contaorigemid;

	private Integer diavencimento;

	private Integer formapagamentoid;

	private String observacao;

	private double valor;

    public Lancamento() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContadestinoid() {
		return this.contadestinoid;
	}

	public void setContadestinoid(Integer contadestinoid) {
		this.contadestinoid = contadestinoid;
	}

	public Integer getContaorigemid() {
		return this.contaorigemid;
	}

	public void setContaorigemid(Integer contaorigemid) {
		this.contaorigemid = contaorigemid;
	}

	public Integer getDiavencimento() {
		return this.diavencimento;
	}

	public void setDiavencimento(Integer diavencimento) {
		this.diavencimento = diavencimento;
	}

	public Integer getFormapagamentoid() {
		return this.formapagamentoid;
	}

	public void setFormapagamentoid(Integer formapagamentoid) {
		this.formapagamentoid = formapagamentoid;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}