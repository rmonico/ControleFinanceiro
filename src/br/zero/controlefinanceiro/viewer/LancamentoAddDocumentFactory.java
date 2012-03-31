package br.zero.controlefinanceiro.viewer;

public class LancamentoAddDocumentFactory implements DocumentFactory {

	@Override
	public LancamentoAddDocument createDocument() {
		return new LancamentoAddDocument();
	}

}
