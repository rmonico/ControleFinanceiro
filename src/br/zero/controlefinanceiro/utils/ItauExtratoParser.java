package br.zero.controlefinanceiro.utils;

import java.util.Arrays;
import java.util.List;

import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;

public class ItauExtratoParser implements ExtratoLineParser {

	private AbstractExtratoLine extratoLine;
	private static List<String> saldoReferenciaList = createSaldoReferenciaList();

	@Override
	public void parse(String line) throws ExtratoLineParserException {
		String[] fields = line.split("\t");

		if (fields.length < 4) {
			throw new ExtratoLineParserException("\"" + line + "\" não é uma linha válida do extrato do itau!");
		}

		String referencia = fields[3];
		
		if (saldoReferenciaList.contains(referencia)) {
			extratoLine = parseBalanceLine(fields);
		} else {
			extratoLine = parseTransactionLine(fields);
		}
		
		extratoLine.setOriginal(line);
	}

	private static List<String> createSaldoReferenciaList() {
		saldoReferenciaList = Arrays.asList(new String[] {"SALDO INICIAL", "SALDO ANTERIOR", "S A L D O", "SALDO FINAL" });
		
		return saldoReferenciaList;
	}

	private AbstractExtratoLine parseBalanceLine(String[] fields) {
		ConcreteExtratoBalanceLine ebl = new ConcreteExtratoBalanceLine();

		return ebl;
	}

	private AbstractExtratoLine parseTransactionLine(String[] fields) {
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

}
