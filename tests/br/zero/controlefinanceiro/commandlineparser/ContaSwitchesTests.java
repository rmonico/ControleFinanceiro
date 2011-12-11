package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class ContaSwitchesTests extends CustomParserTests {

	private void doContaLoad(String[] args) throws ParserException {
		doLoad(args);

		assertEquals("Comando", switches.getEntity(), Command.CONTA_LIST);
	}
	
	@Test
	public void doContaListTest() throws ParserException {
		doContaLoad(new String[] { "conta-ls" });

		assertNotNull("switches", switches.getContaListSwitches());

		ContaListSwitches contaListSwitches = switches.getContaListSwitches();

		assertNull("filtro de nome", contaListSwitches.getFiltroNome());
	}

	@Test
	public void doContaListTest2() throws ParserException {
		doContaLoad(new String[] { "conta-ls", "nome para filtrar" });

		assertNotNull("switches", switches.getContaListSwitches());

		ContaListSwitches contaListSwitches = switches.getContaListSwitches();
			
		assertEquals("filtro de nome", "nome para filtrar", contaListSwitches.getFiltroNome());
	}

	@Test
	public void doContaAddTest() throws ParserException {
		doContaLoad(new String[] { "conta-add", "nova conta" });
		
		assertNotNull("switches", switches.getContaAddSwitches());

		ContaAddSwitches contaAddSwitches = switches.getContaAddSwitches();
			
		assertEquals("nome da nova conta", "nova conta", contaAddSwitches.getNome());
		assertNull("conta sem observacao", contaAddSwitches.getObservacao());
	}

	@Test
	public void doContaAddTest2() throws ParserException {
		doContaLoad(new String[] { "conta-add", "nova conta", "-obs", "Observação da nova conta" });

		assertNotNull("switches", switches.getContaAddSwitches());

		ContaAddSwitches contaAddSwitches = switches.getContaAddSwitches();
			
		assertEquals("Nome", "nova conta", contaAddSwitches.getNome());
		assertEquals("Observacao", "Observação da nova conta", contaAddSwitches.getObservacao());
	}

	@Test
	public void doContaAddTest3() throws ParserException {
		doContaLoad(new String[] { "conta-add", "nova conta", "--contabilizar" });

		assertNotNull(switches.getContaAddSwitches());

		ContaAddSwitches contaAddSwitches = switches.getContaAddSwitches();
			
		assertEquals("Nome", "nova conta", contaAddSwitches.getNome());
		assertTrue("Contabilizar", contaAddSwitches.getContabilizavel());
	}

	@Test
	public void doContaRemoveTest() throws ParserException {
		doContaLoad(new String[] { "conta-rm", "nome conta" });

		assertNotNull("switches", switches.getContaRemoveSwitches());

		ContaRemoveSwitches contaRemoveSwitches = switches.getContaRemoveSwitches(); 
			
		assertEquals("Nome da conta a ser removida", "nome conta", contaRemoveSwitches.getContaNome());
	}
}
