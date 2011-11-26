package br.zero.controlefinanceiro.model.modelo;

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
@Table(name = "lancamento", schema = "controlefinanceiro_modelo")
@EntitySetup(findAllQuery = "select lm from LancamentoModelo lm order by lm.modelo.nome, lm.diaVencimento", findByIdQuery = "select lm from LancamentoModelo lm where lm.id=:id", idFieldName = "id")
public class LancamentoModelo {

	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLEFINANCEIRO_MODELO.LANCAMENTO_ID_SEQ")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ModeloID")
	private Modelo modelo;

	private Integer diaVencimento;

	@ManyToOne
	@JoinColumn(name = "ContaOrigemID")
	private Conta contaOrigem;

	@ManyToOne
	@JoinColumn(name = "ContaDestinoID")
	private Conta contaDestino;

	private Double valor;

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

	public Integer getDiaVencimento() {
		return this.diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
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

	public void setContaDestino(Conta value) {
		contaDestino = value;
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		String modeloNome = (modelo != null) ? modelo.getNome() : "[null]";
		String diaVencimento = (this.diaVencimento != null) ? (this.diaVencimento.toString()) : "[null]";
		String contaOrigemNome = (contaOrigem != null) ? contaOrigem.getNome() : "[null]";
		String contaDestinoNome = (contaDestino != null) ? contaDestino.getNome() : "[null]";
		String valor = (this.valor != null) ? this.valor.toString() : "[null]";
		String observacao = (this.observacao != null) ? this.observacao : "[null]";

		return modeloNome + ", " + diaVencimento + ", " + contaOrigemNome + " -> " + contaDestinoNome + ", " + valor + ", " + observacao;
	}

}