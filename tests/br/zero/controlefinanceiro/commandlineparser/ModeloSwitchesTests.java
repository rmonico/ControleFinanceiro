package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.zero.switchesparser.ParserException;

public class ModeloSwitchesTests extends CustomParserTests {

	private ModeloSwitches modeloSwitches;
	
	private void doModeloLoad(String[] args, ModeloCommand modeloCommand) throws ParserException {
		doLoad(args);

		assertEquals("Entidade", Entity.MODELO, switches.getEntity());

		modeloSwitches = switches.getModeloSwitches();

		assertEquals("Comando do modelo", modeloCommand, modeloSwitches.getCommand());
	}
	
	private ModeloListSwitches doModeloListLoad(String[] args) throws ParserException {
		doModeloLoad(args, ModeloCommand.LIST);

		assertNotNull("Switches de lista", modeloSwitches.getListSwitches());
		assertNull("Switches de add", modeloSwitches.getAddSwitches());
		assertNull("Switches de simulate", modeloSwitches.getSimulateSwitches());
		assertNull("Switches de remove", modeloSwitches.getRemoveSwitches());
		assertNull("Switches de clone", modeloSwitches.getCloneSwitches());

		return modeloSwitches.getListSwitches();
	}

	@Test
	public void doListTest() throws ParserException {
		ModeloListSwitches listSwitches = doModeloListLoad(new String[] { "modelo", "ls" });

		assertNull("Filtro de nome", listSwitches.getFiltroNome());
	}

	@Test
	public void doListTest2() throws ParserException {
		ModeloListSwitches listSwitches = doModeloListLoad(new String[] { "modelo", "ls", "nome do modelo para filtro"});

		assertEquals("Filtro de nome", "nome do modelo para filtro", listSwitches.getFiltroNome());
	}

	
}
