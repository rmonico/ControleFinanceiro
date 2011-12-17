package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.Test;

import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;
import br.zero.controlefinanceiro.model.ExtratoTransactionLine;
import br.zero.controlefinanceiro.utils.ExtratoLineParserException;
import br.zero.controlefinanceiro.utils.ExtratoParsers;

public class ExtratoParsersTests {

	private ExtratoLineParser parser;

	private ExtratoLineParser getItauParser() {
		return ExtratoParsers.ITAU_EXTRATO_PARSER;
	}

	@Test
	public void doItauExtratoParserTransactionLineTest() throws ExtratoLineParserException {
		parser = getItauParser();

		parser.parse("01/09			IOF		1,85-		352,31-	");

		ExtratoLine el = parser.getLine();

		assertNotNull("linha do extrato não-nula", el);

		assertTrue("Implementa ExtratoTransactionLine", el instanceof ExtratoTransactionLine);

		ExtratoTransactionLine line = (ExtratoTransactionLine) el;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		assertEquals("data", "01/Sep/2011", sdf.format(line.getData().getTime()));

		assertEquals("referência", "IOF", line.getReferencia());
	}

	@Test
	public void doItauExtratoParserInvalidLineTests2() {
		// parser = getItauParser();
		//
		// parser.parse("16/11\t\t\tSDO CTA/APL AUTOMATICAS\t\t\t\t1.258,52\t");
		//
		// assertFalse("linha válida", parser.isTransferLine());
	}

}
