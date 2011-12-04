package br.zero.controlefinanceiro.model;

public interface ExtratoParser {

	public void parse(String line);

	// public Calendar getData();

	public boolean isTransferLine();

	Exception getThrewException();
}
