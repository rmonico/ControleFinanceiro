package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;

public class SantanderExtratoParser implements ExtratoLineParser {

	private ConcreteExtratoTransactionLine extratoLine;

	@Override
	public void parse(String line) {
		String[] fields = line.split("\t");

		ConcreteExtratoTransactionLine el = new ConcreteExtratoTransactionLine();

		if (fields.length < 3) {
			return;
		}

		if ("SALDO ANTERIOR".equals(fields[2])) {
			return;
		}

		el.setReferencia("");

		extratoLine = el;
	}

	@Override
	public ExtratoLine getLine() {
		return extratoLine;
	}

}
