package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.controlefinanceiro.model.comum.Conta;

public class NewTransactionCommand {
	
	private Conta contaOrigem;
	private Conta contaDestino;
	private double valor;
	private StringBuilder comments;

	public Conta getContaOrigem() {
		return contaOrigem;
	}
	
	@CommandLineSwitch(index=1, parser="ContaParser")
	public void setContaOrigem(Conta value) {
		contaOrigem = value;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}
	
	@CommandLineSwitch(index=2, parser="ContaParser")
	public void setContaDestino(Conta value) {
		contaDestino = value;
	}

	public double getValor() {
		return valor;
	}
	
	@CommandLineSwitch(index=3, parser="DoubleParser")
	public void setValor(double value) {
		valor = value;
	}

	public StringBuilder getComments() {
		return comments;
	}
	
	@CommandLineSwitch(index=4)
	public void setComments(StringBuilder value) {
		comments = value;
	}

}
