package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.Test;

import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLancamentoBalance;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLancamentoTransaction;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLancamentoUnknown;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLineParser;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoParsers;
import br.zero.controlefinanceiro.abstractextratoparser.ParsedExtratoLancamento;
import br.zero.controlefinanceiro.utils.ExtratoLineParserException;

public class ExtratoParsersTests {

	private ExtratoLineParser parser;

	private ExtratoLineParser getItauParser() {
		return ExtratoParsers.ITAU_EXTRATO_PARSER;
	}

	@Test
	public void doItauExtratoTransactionLineParserTest() throws ExtratoLineParserException {
		parser = getItauParser();

		ParsedExtratoLancamento el = parser.parse("01/09			IOF		1,85-		352,31-	");

		assertNotNull("linha do extrato não-nula", el);

		assertTrue("interface implementada", el instanceof ExtratoLancamentoTransaction);

		ExtratoLancamentoTransaction line = (ExtratoLancamentoTransaction) el;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		assertEquals("data", "01/Sep/2011", sdf.format(line.getData().getTime()));

		assertEquals("referência", "IOF", line.getReferencia());

		assertEquals("valor", -1.85, line.getValor(), 0.0);
	}

	@Test
	public void doItauExtratoBalanceLineParserTest() throws ExtratoLineParserException {
		parser = getItauParser();

		ParsedExtratoLancamento el = parser.parse("01/09			SALDO INICIAL				350,46-	");

		assertNotNull("linha do extrato não-nula", el);

		assertTrue("interface implementada", el instanceof ExtratoLancamentoBalance);
	}

	@Test
	public void doItauExtratoUnknownLineParserTest() throws ExtratoLineParserException {
		parser = getItauParser();

		ParsedExtratoLancamento el = parser.parse("29/09			APL APLIC AUT MAIS		107,94-		150,00	");

		assertNotNull("linha do extrato não-nula", el);

		assertTrue("interface implementada", el instanceof ExtratoLancamentoUnknown);
	}
}
