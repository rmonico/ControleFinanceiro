package br.zero.controlefinanceiro.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLineParser;
import br.zero.controlefinanceiro.abstractextratoparser.ParsedExtratoLancamento;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;

public class ItauExtratoParser implements ExtratoLineParser {

	private static List<String> saldoReferenciaList = createSaldoReferenciaList();
	private static List<String> ignorarReferenciaList = createIgnorarReferenciaList();

	@Override
	public ParsedExtratoLancamento parse(String line) throws ExtratoLineParserException {
		String[] fields = line.split("\t");

		if (fields.length < 4) {
			throwInvalidLineException(line);
		}

		String referencia = fields[3];

		ParsedExtratoLancamento extratoLine;

		if (saldoReferenciaList.contains(referencia)) {
			extratoLine = parseBalanceLine(fields);
		} else if (ignorarReferenciaList.contains(referencia)) {
			extratoLine = parseIgnoredLine();
		} else {
			extratoLine = parseTransactionLine(fields);
		}

		return extratoLine;
	}

	@Override
	public ParsedExtratoLancamento parse(ExtratoLancamento line) throws ExtratoLineParserException {
		AbstractParsedExtratoLancamento extratoLine = (AbstractParsedExtratoLancamento) parse(line.getOriginal());

		extratoLine.setOrigem(line);

		return extratoLine;
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

	private AbstractParsedExtratoLancamento parseBalanceLine(String[] fields) throws ExtratoLineParserException {
		ConcreteExtratoLancamentoBalance ebl = new ConcreteExtratoLancamentoBalance();

		Calendar data;
		try {
			data = parseData(fields[0]);
		} catch (ParseException e) {
			throw new ExtratoLineParserException(e);
		}

		ebl.setData(data);

		return ebl;
	}

	private AbstractParsedExtratoLancamento parseIgnoredLine() {
		return new ConcreteExtratoLancamentoUnknown();
	}

	private AbstractParsedExtratoLancamento parseTransactionLine(String[] fields) throws ExtratoLineParserException {
		if (fields.length < 6) {
			throwInvalidLineException(fields.toString());
		}

		ConcreteExtratoLancamentoTransaction etl = new ConcreteExtratoLancamentoTransaction();

		etl.setReferencia(fields[3]);

		Calendar data;
		try {
			data = parseData(fields[0]);
		} catch (ParseException e) {
			throw new ExtratoLineParserException(e);
		}

		etl.setData(data);

		String f6 = "";
		if (fields.length > 6) {
			f6 = fields[6];
		}
		Double valor = parseValor(fields[5] + f6);

		etl.setValor(valor);

		return etl;
	}

	private Calendar parseData(String dataStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

		Calendar data = GregorianCalendar.getInstance();

		data.setTime(sdf.parse(dataStr));

		data.set(Calendar.YEAR, GregorianCalendar.getInstance().get(Calendar.YEAR));

		return data;
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
			case '-': {
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

}
