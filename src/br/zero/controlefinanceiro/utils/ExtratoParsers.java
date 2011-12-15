package br.zero.controlefinanceiro.utils;

import java.text.ParseException;

import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.ExtratoBalanceLine;
import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;
import br.zero.controlefinanceiro.model.ExtratoTransactionLine;

/**
 * Apenas instancia os parsers de extrato para que possam ser utilizados.
 * 
 * @author Rafael Monico
 * 
 */
public class ExtratoParsers {

	public static final ExtratoLineParser ITAU_EXTRATO_PARSER = createItauParser();
	public static final ExtratoLineParser SANTANDER_EXTRATO_PARSER = createSantanderParser();

	private static ExtratoLineParser createItauParser() {
		ExtratoLineParser itauParser = new ExtratoLineParser() {

			private ParseException threwException;
			private ExtratoLine extratoLine;

			@Override
			public void parse(String line) {
				String[] fields = line.split("\t");

				if (fields.length < 4) {
					return;
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
				return threwException;
			}

		};

		return itauParser;
	}

	private static ExtratoLineParser createSantanderParser() {
		ExtratoLineParser santanderParser = new ExtratoLineParser() {

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

			@Override
			public Exception getThrewException() {
				// TODO Auto-generated method stub
				return null;
			}

		};

		return santanderParser;
	}

	public static void registerParsers() {
		ContaDAO.registerExtratoParser("itau", ExtratoParsers.ITAU_EXTRATO_PARSER);
		ContaDAO.registerExtratoParser("santander", ExtratoParsers.SANTANDER_EXTRATO_PARSER);
	}

}
