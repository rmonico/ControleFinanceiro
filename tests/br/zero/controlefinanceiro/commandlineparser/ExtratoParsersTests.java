package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.Test;

import br.zero.controlefinanceiro.model.ExtratoBalanceLine;
import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;
import br.zero.controlefinanceiro.model.ExtratoTransactionLine;
import br.zero.controlefinanceiro.model.UnknownExtratoLine;
import br.zero.controlefinanceiro.utils.ExtratoLineParserException;
import br.zero.controlefinanceiro.utils.ExtratoParsers;

public class ExtratoParsersTests {

	private ExtratoLineParser parser;

	private ExtratoLineParser getItauParser() {
		return ExtratoParsers.ITAU_EXTRATO_PARSER;
	}

	@Test
	public void doItauExtratoTransactionLineParserTest() throws ExtratoLineParserException {
		parser = getItauParser();

		parser.parse("01/09			IOF		1,85-		352,31-	");

		ExtratoLine el = parser.getLine();

		assertNotNull("linha do extrato não-nula", el);

		assertTrue("interface implementada", el instanceof ExtratoTransactionLine);

		ExtratoTransactionLine line = (ExtratoTransactionLine) el;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		assertEquals("data", "01/Sep/2011", sdf.format(line.getData().getTime()));

		assertEquals("referência", "IOF", line.getReferencia());

		assertEquals("valor", -1.85, line.getValor(), 0.0);
	}

	@Test
	public void doItauExtratoBalanceLineParserTest() throws ExtratoLineParserException {
		parser = getItauParser();

		parser.parse("01/09			SALDO INICIAL				350,46-	");

		ExtratoLine el = parser.getLine();

		assertNotNull("linha do extrato não-nula", el);

		assertTrue("interface implementada", el instanceof ExtratoBalanceLine);
	}

	@Test
	public void doItauExtratoUnknownLineParserTest() throws ExtratoLineParserException {
		parser = getItauParser();

		parser.parse("29/09			APL APLIC AUT MAIS		107,94-		150,00	");

		ExtratoLine el = parser.getLine();

		assertNotNull("linha do extrato não-nula", el);

		assertTrue("interface implementada", el instanceof UnknownExtratoLine);
	}
}
