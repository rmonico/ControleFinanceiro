package br.zero.controlefinanceiro.model;

public interface ExtratoParser {

	public void parse(String line);

	public ExtratoLine getLine();

	// TODO usar o mesmo mecanismo de encontrar a conta para saber se a linha é uma transferência ou não (e eliminar este método)
	public boolean isTransferLine();

	Exception getThrewException();
}
