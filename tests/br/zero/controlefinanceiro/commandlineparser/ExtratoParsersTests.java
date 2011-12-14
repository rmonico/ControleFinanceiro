package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;
import br.zero.controlefinanceiro.model.ExtratoTransactionLine;
import br.zero.controlefinanceiro.utils.ExtratoParsers;

public class ExtratoParsersTests {

	private ExtratoLineParser parser;
	
	private ExtratoLineParser getItauParser() {
		return ExtratoParsers.ITAU_EXTRATO_PARSER;
	}
	
	@Test
	public void doItauExtratoParserTest() {
		parser = getItauParser();
		
		parser.parse("01/09			IOF		1,85-		352,31-	");

		ExtratoLine el = parser.getLine();
		
		assertNotNull("linha do extrato não-nula", el);
		
		assertTrue("Implementa ExtratoTransactionLine", el instanceof ExtratoTransactionLine);
		
		ExtratoTransactionLine line = (ExtratoTransactionLine) el;
		
		assertEquals("linha válida", "IOF", line.getReferencia());
	}

	@Test
	public void doItauExtratoParserInvalidLineTests2() {
//		parser = getItauParser();
//		
//		parser.parse("16/11\t\t\tSDO CTA/APL AUTOMATICAS\t\t\t\t1.258,52\t");
//
//		assertFalse("linha válida", parser.isTransferLine());
	}

	@Test
	public void doItauExtratoParserTests2() {
//		parser = getItauParser();
//
//		parser.parse("16/11\t\t\tCXE 000323 SAQUE\t7619\t550,00\t-\t\t");
//
//		assertTrue("linha válida", parser.isTransferLine());
//		TODO Depois será necessário ter o campo de data, o teste já está aí
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
//		assertEquals("Data", "16/Nov/2011", sdf.format(parser.getData().getTime()));
	}
	
	
	private ExtratoLineParser getSantanderParser() {
		return ExtratoParsers.SANTANDER_EXTRATO_PARSER;
	}

	@Test
	public void doSantanderExtratoParserInvalidLineTests() {
//		parser = getSantanderParser();
//		
//		parser.parse("16/11/2011 \t\tSALDO ANTERIOR\t0\t-1546,84\t-1546,84");
//
//		assertFalse("linha válida", parser.isTransferLine());
	}

	@Test
	public void doSantanderExtratoParserTests() {
//		parser = getSantanderParser();
//
//		parser.parse("16/11/2011 \t\tDEBITO AUTOM CONTA DE TELEFONE EMBRATEL TV SAT\t0\t-68,16\t0");
//
//		assertTrue("linha válida", parser.isTransferLine());
//		TODO Depois será necessário ter o campo de data, o teste já está aí
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
//		assertEquals("Data", "16/Nov/2011", sdf.format(parser.getData().getTime()));
	}
}
