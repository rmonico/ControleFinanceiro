package br.zero.controlefinanceiro.model.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.customdao.EntitySetup;

/**
 * The persistent class for the lancamento database table.
 * 
 */
@Entity
@Table(schema = "controlefinanceiro_modelo")
@EntitySetup(findAllQuery = "select l from LancamentoModelo l order by l.diavencimento", findByIdQuery = "select l from LancamentoModelo l where l.id=:id", idFieldName = "id")
public class LancamentoModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ModeloID")
	private Modelo modelo;
	
	private Integer diavencimento;

	@ManyToOne
	@JoinColumn(name = "ContaOrigemID")
	private Conta contaOrigem;

	@ManyToOne
	@JoinColumn(name = "ContaDestinoID")
	private Conta contaDestino;

	private double valor;

	private String observacao;

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Modelo getModelo() {
		return modelo;
	}
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public Integer getDiavencimento() {
		return this.diavencimento;
	}

	public void setDiavencimento(Integer diavencimento) {
		this.diavencimento = diavencimento;
	}

	public Conta getContaOrigem() {
		return this.contaOrigem;
	}

	public void setContaOrigem(Conta value) {
		this.contaOrigem = value;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestinoID(Conta value) {
		contaDestino = value;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}