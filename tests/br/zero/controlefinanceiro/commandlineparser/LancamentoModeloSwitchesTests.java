package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class LancamentoModeloSwitchesTests extends CustomParserTests {

	private void doLancamentoModeloLoad(String[] args, Command command) throws ParserException {
		doLoad(args);

		assertEquals("Command", command, switches.getEntity());
	}

	@Test
	public void doListTest() throws ParserException {
		doLancamentoModeloLoad(new String[] { "lancmodelo-ls" }, Command.LANCAMENTOMODELO_LIST);

		assertNotNull(switches.getLancamentoModeloListSwitches());
		
		LancamentoModeloListSwitches listSwitches = switches.getLancamentoModeloListSwitches();
			
		assertNull("Nome do modelo", listSwitches.getModelo());
	}

	@Test
	public void doListTest2() throws ParserException {
		doLancamentoModeloLoad(new String[] { "lancmodelo-ls", "modelo" }, Command.LANCAMENTOMODELO_LIST);

		assertNotNull(switches.getLancamentoModeloListSwitches());
		
		LancamentoModeloListSwitches listSwitches = switches.getLancamentoModeloListSwitches();
			
		assertEquals("Nome do modelo", "modelo", listSwitches.getModelo());
	}

	private LancamentoModeloAddSwitches baseAddTests(String[] args) throws ParserException {
		doLancamentoModeloLoad(args, Command.LANCAMENTOMODELO_ADD);

		assertNotNull("switches", switches.getLancamentoModeloAddSwitches());
		
		LancamentoModeloAddSwitches addSwitches = switches.getLancamentoModeloAddSwitches();
			
		assertEquals("Nome", "modelo", addSwitches.getModelo());
		assertEquals("Data", (Integer) 19, addSwitches.getDiaVencimento());
		assertEquals("Conta origem", "conta origem", addSwitches.getContaOrigem());
		assertEquals("Conta destino", "conta destino", addSwitches.getContaDestino());
		assertEquals("Valor", 1.99, addSwitches.getValor(), 0);

		return addSwitches;
	}

	@Test
	public void doAddTest() throws ParserException {
		LancamentoModeloAddSwitches addSwitches = baseAddTests(new String[] { "lancmodelo-add", "modelo", "19", "conta origem", "conta destino", "1.99" });

		assertNull("Observacao", addSwitches.getObservacao());
	}

	@Test
	public void doAddTest2() throws ParserException {
		LancamentoModeloAddSwitches addSwitches = baseAddTests(new String[] { "lancmodelo-add", "modelo", "19", "conta origem", "conta destino", "1.99", "observacao" });

		assertEquals("Observacao", "observacao", addSwitches.getObservacao());
	}

	@Test
	public void doRemoveTest() throws ParserException {
		doLancamentoModeloLoad(new String[] { "lancmodelo-rm", "54" }, Command.LANCAMENTOMODELO_REMOVE);

		assertNotNull("Switches", switches.getLancamentoModeloRemoveSwitches());

		LancamentoModeloRemoveSwitches removeSwitches = switches.getLancamentoModeloRemoveSwitches();

		assertEquals("ID", (Integer) 54, removeSwitches.getId());
	}
}
