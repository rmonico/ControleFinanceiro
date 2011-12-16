package br.zero.controlefinanceiro.utils;

import java.util.Arrays;
import java.util.List;

import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;

public class ItauExtratoParser implements ExtratoLineParser {

	private AbstractExtratoLine extratoLine;
	private static List<String> saldoReferenciaList = createSaldoReferenciaList();
	private static List<String> ignorarReferenciaList = createIgnorarReferenciaList();

	@Override
	public void parse(String line) throws ExtratoLineParserException {
		String[] fields = line.split("\t");

		if (fields.length < 4) {
			throwInvalidLineException(line);
		}

		String referencia = fields[3];

		if (saldoReferenciaList.contains(referencia)) {
			extratoLine = parseBalanceLine();
		} else if (ignorarReferenciaList.contains(referencia)) {
			extratoLine = parseIgnoredLine();
		} else {
			extratoLine = parseTransactionLine(fields, line);
		}

		extratoLine.setOriginal(line);
	}

	private void throwInvalidLineException(String line) throws ExtratoLineParserException {
		throw new ExtratoLineParserException("\"" + line + "\" não é uma linha válida do extrato do itau!");
	}

	private static List<String> createIgnorarReferenciaList() {
		List<String> list = Arrays.asList(new String[] { "RES APLIC AUT MAIS", "APL APLIC AUT MAIS" });

		return list;
	}

	private static List<String> createSaldoReferenciaList() {
		List<String> list = Arrays.asList(new String[] { "SALDO INICIAL", "SALDO ANTERIOR", "S A L D O", "SDO CTA/APL AUTOMATICAS", "SALDO FINAL", "SALDO FINAL DISPONIVEL", "(-) SALDO A LIBERAR" });

		return list;
	}

	private AbstractExtratoLine parseBalanceLine() {
		ConcreteExtratoBalanceLine ebl = new ConcreteExtratoBalanceLine();

		return ebl;
	}

	private AbstractExtratoLine parseIgnoredLine() {
		return new UnknownExtratoLine();
	}

	private AbstractExtratoLine parseTransactionLine(String[] fields, String line) throws ExtratoLineParserException {
		if (fields.length < 6) {
			throwInvalidLineException(line);
		}

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

		Double valor = parseValor(fields[5]);

		etl.setValor(valor);

		return etl;
	}

	private Double parseValor(String str) {
		StringBuilder sb = new StringBuilder();
		
		for (char ch : str.toCharArray()) {
			switch (ch) {
			case '.': {
				continue;
			}
			case ',': {
				sb.append('.');
				break;
			}
			case '-' : {
				sb.insert(0, '-');
				break;
			}
			default: {
				sb.append(ch);
			}
			}
		}
		
		Double valor = Double.parseDouble(sb.toString());
		
		return valor;
	}

	@Override
	public ExtratoLine getLine() {
		return extratoLine;
	}

}
