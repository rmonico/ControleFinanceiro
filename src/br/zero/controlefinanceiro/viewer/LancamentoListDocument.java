package br.zero.controlefinanceiro.viewer;

import java.io.PrintStream;

public class LancamentoListDocument implements Document {

	@Override
	public void render(PrintStream stream) {
		stream.println("lanc-list");
	}

}
