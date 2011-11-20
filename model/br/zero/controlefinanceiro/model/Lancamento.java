package br.zero.controlefinanceiro.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.zero.customdao.EntitySetup;

@Entity
@Table(schema = "controlefinanceiro")
//@EntitySetup(findAllQuery = "select l from Lancamento l order by data", findByIdQuery = "select l from Lancamento l where l.id=:id", idFieldName = "id")
@EntitySetup(findAllQuery = "select l from Lancamento l", findByIdQuery = "select l from Lancamento l where l.id=:id", idFieldName = "id")
public class Lancamento {

	@Id
	@SequenceGenerator(name = "CONTROLEFINANCEIRO.LANCAMENTO_ID_SEQ", sequenceName = "CONTROLEFINANCEIRO.LANCAMENTO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLEFINANCEIRO.LANCAMENTO_ID_SEQ")
	private Integer id;

	
	@ManyToOne
	@JoinColumn(name = "LancamentoModeloID")
	private br.zero.controlefinanceiro.model.modelo.LancamentoModelo lancamentoModelo;
	
	private Calendar data;
	
	private int n;
	
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

	public br.zero.controlefinanceiro.model.modelo.LancamentoModelo getLancamentoModelo() {
		return lancamentoModelo;
	}

	public void setLancamentoModelo(br.zero.controlefinanceiro.model.modelo.LancamentoModelo lancamentoModelo) {
		this.lancamentoModelo = lancamentoModelo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
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
