package br.zero.controlefinanceiro.model;

public interface ExtratoParser {

	public void parse(String line);

	public ExtratoLine getLine();

	public boolean isTransferLine();

	Exception getThrewException();
}
