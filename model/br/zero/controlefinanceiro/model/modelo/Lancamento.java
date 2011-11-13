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

import br.zero.controlefinanceiro.model.comum.Conta;
import br.zero.controlefinanceiro.model.comum.FormaPagamento;
import br.zero.customdao.EntitySetup;

/**
 * The persistent class for the lancamento database table.
 * 
 */
@Entity
@Table(schema = "controlefinanceiro_modelo")
@EntitySetup(findAllQuery = "select l from Lancamento l order by l.diavencimento", findByIdQuery = "select l from Lancamento l where l.id=:id", idFieldName = "id")
public class Lancamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ContaDestinoID")
	private Conta contaDestino;

	@ManyToOne
	@JoinColumn(name = "ContaOrigemID")
	private Conta contaOrigem;

	private Integer diavencimento;

	@ManyToOne
	@JoinColumn(name = "FormaPagamentoID")
	private FormaPagamento formaPagamento;

	private String observacao;

	private double valor;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestinoID(Conta value) {
		contaDestino = value;
	}

	public Conta getContaOrigem() {
		return this.contaOrigem;
	}

	public void setContaOrigem(Conta value) {
		this.contaOrigem = value;
	}

	public Integer getDiavencimento() {
		return this.diavencimento;
	}

	public void setDiavencimento(Integer diavencimento) {
		this.diavencimento = diavencimento;
	}

	public FormaPagamento getFormaPagamento() {
		return this.formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento value) {
		this.formaPagamento = value;
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