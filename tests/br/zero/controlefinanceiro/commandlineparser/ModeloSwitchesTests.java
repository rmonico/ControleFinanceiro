package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class ModeloSwitchesTests extends CustomParserTests {

	private ModeloSwitches modeloSwitches;

	private void doModeloLoad(String[] args, ModeloCommand modeloCommand) throws ParserException {
		doLoad(args);

		assertEquals("Entidade", Entity.MODELO, switches.getEntity());

		modeloSwitches = switches.getModeloSwitches();

		assertEquals("Comando do modelo", modeloCommand, modeloSwitches.getCommand());
	}

	@Test
	public void doListTest() throws ParserException {
		doModeloLoad(new String[] { "modelo", "ls" }, ModeloCommand.LIST);
	}

	private ModeloAddSwitches doModeloAddLoad(String[] args) throws ParserException {
		doModeloLoad(args, ModeloCommand.ADD);

		assertNotNull("Switches de add", modeloSwitches.getAddSwitches());
		assertNull("Switches de simulate", modeloSwitches.getSimulateSwitches());
		assertNull("Switches de remove", modeloSwitches.getRemoveSwitches());
		assertNull("Switches de clone", modeloSwitches.getCloneSwitches());

		return modeloSwitches.getAddSwitches();
	}

	@Test
	public void doAddTest() throws ParserException {
		ModeloAddSwitches addSwitches = doModeloAddLoad(new String[] { "modelo", "add", "novo modelo" });

		assertEquals("Nome do modelo", "novo modelo", addSwitches.getNome());
		assertNull("Observacao do modelo", addSwitches.getObservacao());
	}

	@Test
	public void doAddTest2() throws ParserException {
		ModeloAddSwitches addSwitches = doModeloAddLoad(new String[] { "modelo", "add", "novo modelo", "observacao" });

		assertEquals("Nome do modelo", "novo modelo", addSwitches.getNome());
		assertEquals("Observacao do modelo", "observacao", addSwitches.getObservacao());
	}
	
	@Test
	public void doSimulateTest() throws ParserException {
		doModeloLoad(new String[] { "modelo", "simulate", "nome modelo" }, ModeloCommand.SIMULATE);

		assertNull("Switches de add", modeloSwitches.getAddSwitches());
		assertNotNull("Switches de simulate", modeloSwitches.getSimulateSwitches());
		assertNull("Switches de remove", modeloSwitches.getRemoveSwitches());
		assertNull("Switches de clone", modeloSwitches.getCloneSwitches());


		ModeloSimulateSwitches simulateSwitches = modeloSwitches.getSimulateSwitches();

		assertEquals("Nome do modelo", "nome modelo", simulateSwitches.getNomeModelo());
	}
	
	@Test
	public void doRemoveTest() throws ParserException {
		doModeloLoad(new String[] { "modelo", "rm", "nome modelo" }, ModeloCommand.REMOVE);

		assertNull("Switches de add", modeloSwitches.getAddSwitches());
		assertNull("Switches de simulate", modeloSwitches.getSimulateSwitches());
		assertNotNull("Switches de remove", modeloSwitches.getRemoveSwitches());
		assertNull("Switches de clone", modeloSwitches.getCloneSwitches());


		ModeloRemoveSwitches removeSwitches = modeloSwitches.getRemoveSwitches();

		assertEquals("Nome do modelo", "nome modelo", removeSwitches.getNomeModelo());
	}
	
	@Test
	public void doCloneTest() throws ParserException {
		doModeloLoad(new String[] { "modelo", "clone", "modelo base", "modelo novo"}, ModeloCommand.CLONE);

		assertNull("Switches de add", modeloSwitches.getAddSwitches());
		assertNull("Switches de simulate", modeloSwitches.getSimulateSwitches());
		assertNull("Switches de remove", modeloSwitches.getRemoveSwitches());
		assertNotNull("Switches de clone", modeloSwitches.getCloneSwitches());


		ModeloCloneSwitches removeSwitches = modeloSwitches.getCloneSwitches();

		assertEquals("Modelo base", "modelo base", removeSwitches.getModeloBase());
		assertEquals("Modelo novo", "modelo novo", removeSwitches.getModeloNovo());
	}
}
