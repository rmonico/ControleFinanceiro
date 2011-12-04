package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import br.zero.controlefinanceiro.model.ExtratoParser;
import br.zero.controlefinanceiro.utils.ExtratoParsers;

public class ExtratoParsersTests {

	private ExtratoParser parser;
	@Before
	public void setup() {
		parser = ExtratoParsers.ITAU_EXTRATO_PARSER;
	}
	
	@Test
	public void doItauExtratoParserTests() {
		parser.parse("14/11\t\t\tSALDO ANTERIOR\t\t\t\t2.395,90\t");

		assertFalse("linha válida", parser.isTransferLine());
	}

	@Test
	public void doItauExtratoParserTests2() {
		parser.parse("16/11\t\t\tCXE 000323 SAQUE\t7619\t550,00\t-\t\t");

		assertTrue("linha válida", parser.isTransferLine());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		assertEquals("Data", "16/Nov/2011", sdf.format(parser.getData().getTime()));
	}
}
