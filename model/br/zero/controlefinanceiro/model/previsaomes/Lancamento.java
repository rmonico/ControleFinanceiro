package br.zero.controlefinanceiro.model.previsaomes;

import java.util.Calendar;

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

@Entity
@Table(schema = "controlefinanceiro_previsaomes")
@EntitySetup(findAllQuery = "select l from Lancamento l order by data", findByIdQuery = "select l from Lancamento l where l.id=:id", idFieldName = "id")
public class Lancamento {

	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO_PREVISAOMES.LANCAMENTO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO_PREVISAOMES.LANCAMENTO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLEFINANCEIRO_PREVISAOMES.LANCAMENTO_ID_SEQ")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "OrcamentoID")
	private Orcamento orcamento;
	
	private Calendar data;
	
	@ManyToOne
	@JoinColumn(name = "FormaPagamentoID")
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(name = "ContaOrigemID")
	private Conta contaOrigem;
	
	@ManyToOne
	@JoinColumn(name = "ContaDestinoID")
	private Conta contaDestino;
	
	private Double valor;
	
	private String observacao;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
