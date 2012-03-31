package br.zero.controlefinanceiro.viewer;

import java.io.PrintStream;

public class LancamentoAddDocument implements Document {

	@Override
	public void render(PrintStream stream) {
		stream.println("lanc-add");
	}

}
