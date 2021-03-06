package br.zero.fin.model;

import java.util.Calendar;

public class Lancamento {
	private int id;
//	private br.zero.fin.model.modelo.Lancamento modelo;
	private int modelo;
	private Calendar date;
	private Conta origem;
	private Conta destino;
	private double valor;
	private String observacao;
	private int n;
//	private br.zero.fin.model.extrato.Lancamento extrato;
	private int extrato;
	
	public int getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

//	public br.zero.fin.model.modelo.Lancamento getModelo() {
//		return modelo;
//	}
//
//	public void setModelo(br.zero.fin.model.modelo.Lancamento modelo) {
//		this.modelo = modelo;
//	}
//
	
	public int getModelo() {
		return modelo;
	}
	
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
	
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Conta getOrigem() {
		return origem;
	}

	public void setOrigem(Conta origem) {
		this.origem = origem;
	}

	public Conta getDestino() {
		return destino;
	}

	public void setDestino(Conta destino) {
		this.destino = destino;
	}

	public double getValor() {
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

	public int getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public int getExtrato() {
		return extrato;
	}
	
	public void setExtrato(Integer extrato) {
		this.extrato = extrato;
	}
	
//	public br.zero.fin.model.extrato.Lancamento getExtrato() {
//		return extrato;
//	}
//
//	public void setExtrato(br.zero.fin.model.extrato.Lancamento extrato) {
//		this.extrato = extrato;
//	}

}
