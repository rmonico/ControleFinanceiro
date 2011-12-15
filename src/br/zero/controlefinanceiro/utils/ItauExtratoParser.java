package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.ExtratoBalanceLine;
import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;
import br.zero.controlefinanceiro.model.ExtratoTransactionLine;

public class ItauExtratoParser implements ExtratoLineParser {

	private ExtratoLine extratoLine;

	@Override
	public void parse(String line) throws ExtratoLineParserException {
		String[] fields = line.split("\t");

		if (fields.length < 4) {
			throw new ExtratoLineParserException("");
		}

		if (("SALDO ANTERIOR".equals(fields[3])) || ("S A L D O".equals(fields[3])) || ("SDO CTA/APL AUTOMATICAS".equals(fields[3]))) {
			extratoLine = parseBalanceLine(fields);
			return;
		} else {
			extratoLine = parseTransactionLine(fields);
		}
	}

	private ExtratoBalanceLine parseBalanceLine(String[] fields) {
		ConcreteExtratoBalanceLine ebl = new ConcreteExtratoBalanceLine();

		return ebl;
	}

	private ExtratoTransactionLine parseTransactionLine(String[] fields) {
		ConcreteExtratoTransactionLine etl = new ConcreteExtratoTransactionLine();

		etl.setReferencia(fields[3]);

		// String dataStr = fields[0];
		//
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		//
		// data = GregorianCalendar.getInstance();
		//
		// try {
		// data.setTime(sdf.parse(dataStr));
		// } catch (ParseException e) {
		// threwException = e;
		// isTransferLine = false;
		// }
		//
		// data.set(Calendar.YEAR,
		// GregorianCalendar.getInstance().get(Calendar.YEAR));

		return etl;
	}

	@Override
	public ExtratoLine getLine() {
		return extratoLine;
	}

	@Override
	public Exception getThrewException() {
		return null;
	}

}
