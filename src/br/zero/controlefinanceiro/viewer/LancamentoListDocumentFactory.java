package br.zero.controlefinanceiro.viewer;

public class LancamentoListDocumentFactory implements DocumentFactory {

	@Override
	public LancamentoListDocument createDocument() {
		return new LancamentoListDocument();
	}

}
