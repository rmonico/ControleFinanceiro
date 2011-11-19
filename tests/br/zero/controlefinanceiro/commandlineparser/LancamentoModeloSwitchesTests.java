package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.zero.switchesparser.ParserException;

public class LancamentoModeloSwitchesTests extends CustomParserTests {
	
	private LancamentoModeloSwitches lancamentoModeloSwitches;

	private void doLancamentoModeloLoad(String[] args, LancamentoModeloCommand lancamentoModeloCommand) throws ParserException {
		doLoad(args);

		assertEquals("Entidade", Entity.LANCAMENTO_MODELO, switches.getEntity());

		lancamentoModeloSwitches = switches.getLancamentoModeloSwitches();

		assertEquals("Comando do lancamento modelo", lancamentoModeloCommand, lancamentoModeloSwitches.getCommand());
	}

	private LancamentoModeloListSwitches doLancamentoModeloListLoad(String[] args) throws ParserException {
		doLancamentoModeloLoad(args, LancamentoModeloCommand.LIST);

		assertNotNull("Switches de lista", lancamentoModeloSwitches.getListSwitches());
		assertNull("Switches de add", lancamentoModeloSwitches.getAddSwitches());
		assertNull("Switches de remove", lancamentoModeloSwitches.getRemoveSwitches());

		return lancamentoModeloSwitches.getListSwitches();
	}
	
	@Test
	public void doListTest() throws ParserException {
		LancamentoModeloListSwitches listSwitches = doLancamentoModeloListLoad(new String[] { "lancmodelo", "ls" });

		assertNull("Nome do modelo", listSwitches.getModelo());
	}
}
