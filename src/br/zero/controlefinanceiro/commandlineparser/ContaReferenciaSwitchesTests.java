package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class ContaReferenciaSwitchesTests extends CustomParserTests {
	
	private void doContaReferenciaLoad(String[] args, Command command) throws ParserException {
		doLoad(args);

		assertEquals("Comando", command, switches.getCommand());
	}

	@Test
	public void doListTest() throws ParserException {
		doContaReferenciaLoad(new String[] { "ctaref-ls", "conta", "banco" }, Command.CONTAREFERENCIA_LIST);

		assertNotNull("switches", switches.getContaReferenciaListSwitches());

		ContaReferenciaListSwitches listSwitches = switches.getContaReferenciaListSwitches();

		assertEquals("Nome da conta", "conta", listSwitches.getNomeConta());
		assertEquals("Nome do banco", "conta", listSwitches.getNomeBanco());
	}


}
