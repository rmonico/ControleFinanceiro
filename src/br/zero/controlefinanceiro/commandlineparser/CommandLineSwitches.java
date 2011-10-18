package br.zero.controlefinanceiro.commandlineparser;

import java.util.Calendar;


import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.controlefinanceiro.model.comum.Conta;
import br.zero.controlefinanceiro.model.comum.FormaPagamento;

public class CommandLineSwitches {
	private MainCommand command;
	private int lancamentoID;
	private Conta conta;
	private Conta contaDestino;
	private double valor;
	private FormaPagamento formaPagamento;
	private String nota;
	private Calendar dataInicial;
	private Calendar dataFinal;

	public MainCommand getCommand() {
		return command;
	}

	@CommandLineSwitch(parser = "EnumParser.parseEnum", index = 1)
	public void setCommand(MainCommand value) {
		command = value;
	}

	@CommandLineSwitch(param = "lancamentoid", parser="integerParser.parse")
	public void setLancamentoID(int value) {
		lancamentoID = value;
	}

	public int getLancamentoID() {
		return lancamentoID;
	}

	// TODO Criar um parser específico para isso
	@CommandLineSwitch(parser = "ControleFinanceiroParsers.parseConta", index = 2)
	public void setConta(Conta value) {
		conta = value;
	}

	public Conta getConta() {
		return conta;
	}

	// TODO Problema: qual seria o índice desse parâmetro?
	@CommandLineSwitch
	public void setContaDestino(Conta value) {
		contaDestino = value;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	// TODO Mesmo problema do anterior. Qual o índice aqui?
	@CommandLineSwitch
	public void setValor(double value) {
		valor = value;
	}

	public double getValor() {
		return valor;
	}

	@CommandLineSwitch
	public void setFormaPagamento(FormaPagamento value) {
		formaPagamento = value;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	@CommandLineSwitch
	public void setNota(String value) {
		nota = value;
	}

	public String getNota() {
		return nota;
	}

	// TODO: criar um parser para calendar
	@CommandLineSwitch(parser = "", index = 3)
	public void setDataInicial(Calendar value) {
		dataInicial = value;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	@CommandLineSwitch(index = 4)
	public void setDataFinal(Calendar value) {
		dataFinal = value;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}
}
