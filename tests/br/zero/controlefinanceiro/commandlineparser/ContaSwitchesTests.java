package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class ContaSwitchesTests extends CustomParserTests {

	@Test
	public void doContaListTest() throws ParserException {
		doLoad(new String[] { "conta-ls" });

		assertEquals("Comando", switches.getEntity(), Command.CONTA_LIST);

		assertNotNull(switches.getContaListSwitches());

		ContaListSwitches contaListSwitches = switches.getContaListSwitches();

		assertNull("filtro de nome", contaListSwitches.getFiltroNome());
	}

	@Test
	public void doContaListTest2() throws ParserException {
		doLoad(new String[] { "conta-ls", "nome para filtrar" });

		assertEquals("Comando", switches.getEntity(), Command.CONTA_LIST);

		assertNotNull(switches.getContaListSwitches());

		ContaListSwitches contaListSwitches = switches.getContaListSwitches();
			
		assertEquals("filtro de nome", "nome para filtrar", contaListSwitches.getFiltroNome());
	}

	@Test
	public void doContaAddTest() throws ParserException {
		doLoad(new String[] { "conta-add", "nova conta" });
		
		assertEquals("Comando", switches.getEntity(), Command.CONTA_ADD);

		assertNotNull(switches.getContaAddSwitches());

		ContaAddSwitches contaAddSwitches = switches.getContaAddSwitches();
			
		assertEquals("nome da nova conta", "nova conta", contaAddSwitches.getNome());
		assertNull("conta sem observacao", contaAddSwitches.getObservacao());
	}

	@Test
	public void doContaAddTest2() throws ParserException {
		doLoad(new String[] { "conta-add", "nova conta", "-obs", "Observação da nova conta" });

		assertEquals("Comando", switches.getEntity(), Command.CONTA_ADD);

		assertNotNull(switches.getContaAddSwitches());

		ContaAddSwitches contaAddSwitches = switches.getContaAddSwitches();
			
		assertEquals("Nome", "nova conta", contaAddSwitches.getNome());
		assertEquals("Observacao", "Observação da nova conta", contaAddSwitches.getObservacao());
	}

	@Test
	public void doContaAddTest3() throws ParserException {
		doLoad(new String[] { "conta-add", "nova conta", "--contabilizar" });

		assertEquals("Comando", switches.getEntity(), Command.CONTA_ADD);

		assertNotNull(switches.getContaAddSwitches());

		ContaAddSwitches contaAddSwitches = switches.getContaAddSwitches();
			
		assertEquals("Nome", "nova conta", contaAddSwitches.getNome());
		assertTrue("Contabilizar", contaAddSwitches.getContabilizavel());
	}

	@Test
	public void doContaRemoveTest() throws ParserException {
		doLoad(new String[] { "conta-rm", "nome conta" });

		assertEquals("Comando", switches.getEntity(), Command.CONTA_REMOVE);

		assertNotNull(switches.getContaRemoveSwitches());

		ContaRemoveSwitches contaRemoveSwitches = switches.getContaRemoveSwitches(); 
			
		assertEquals("Nome da conta a ser removida", "nome conta", contaRemoveSwitches.getContaNome());
	}
}
