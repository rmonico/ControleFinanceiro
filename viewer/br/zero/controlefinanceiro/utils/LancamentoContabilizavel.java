package br.zero.controlefinanceiro.utils;

import java.util.Calendar;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;

public class LancamentoContabilizavel implements Contabilizavel {

	private Double saldoOrigem;
	private Double saldoDestino;
	private Lancamento lancamento;
	
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}

	public Integer getId() {
		return lancamento.getId();
	}

	public LancamentoModelo getLancamentoModelo() {
		return lancamento.getLancamentoModelo();
	}

	public Calendar getData() {
		return lancamento.getData();
	}

	public int getN() {
		return lancamento.getN();
	}

	public Conta getContaOrigem() {
		return lancamento.getContaOrigem();
	}

	public Conta getContaDestino() {
		return lancamento.getContaDestino();
	}

	public Double getValor() {
		return lancamento.getValor();
	}

	public String getObservacao() {
		return lancamento.getObservacao();
	}

	public Double getSaldoOrigem() {
		return saldoOrigem;
	}

	public void setSaldoOrigem(Double saldoOrigem) {
		this.saldoOrigem = saldoOrigem;
	}

	public Double getSaldoDestino() {
		return saldoDestino;
	}

	public void setSaldoDestino(Double saldoDestino) {
		this.saldoDestino = saldoDestino;
	}

}
