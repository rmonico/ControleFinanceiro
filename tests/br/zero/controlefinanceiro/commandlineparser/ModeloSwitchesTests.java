package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class ModeloSwitchesTests extends CustomParserTests {

	private void doModeloLoad(String[] args, Command command) throws ParserException {
		doLoad(args);

		assertEquals("Command", command, switches.getCommand());
	}

	@Test
	public void doListTest() throws ParserException {
		doModeloLoad(new String[] { "modelo-ls" }, Command.MODELO_LIST);
	}

	@Test
	public void doAddTest() throws ParserException {
		doModeloLoad(new String[] { "modelo-add", "novo modelo" }, Command.MODELO_ADD);

		assertNotNull("switches", switches.getModeloAddSwitches());
		
		ModeloAddSwitches addSwitches = switches.getModeloAddSwitches();
			
		assertEquals("Nome do modelo", "novo modelo", addSwitches.getNome());
		assertNull("Observacao do modelo", addSwitches.getObservacao());
	}

	@Test
	public void doAddTest2() throws ParserException {
		doModeloLoad(new String[] { "modelo-add", "novo modelo", "observacao" }, Command.MODELO_ADD);

		assertNotNull("switches", switches.getModeloAddSwitches());
		
		ModeloAddSwitches addSwitches = switches.getModeloAddSwitches();
			
		assertEquals("Nome do modelo", "novo modelo", addSwitches.getNome());
		assertEquals("Observacao do modelo", "observacao", addSwitches.getObservacao());
	}

	@Test
	public void doSimulateTest() throws ParserException {
		doModeloLoad(new String[] { "modelo-simulate", "nome modelo", "01/Nov/2011" }, Command.MODELO_SIMULATE);

		assertNotNull("Switches", switches.getModeloSimulateSwitches());

		ModeloSimulateSwitches simulateSwitches = switches.getModeloSimulateSwitches();
		
		ModeloSimulate ms = simulateSwitches.getModelo().get(0);

		assertEquals("Nome do modelo", "nome modelo", ms.getNomeModelo());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

		assertEquals("Data base", "01/Nov/2011", sdf.format(ms.getDataBase().getTime()));
	}

	@Test
	public void doSimulateTest2() throws ParserException {
		doModeloLoad(new String[] { "modelo-simulate", "nome modelo", "01/Nov/2011", "nome modelo 2", "01/Dec/2011" }, Command.MODELO_SIMULATE);

		assertNotNull("Switches", switches.getModeloSimulateSwitches());

		
		ModeloSimulateSwitches simulateSwitches = switches.getModeloSimulateSwitches();

		ModeloSimulate ms = simulateSwitches.getModelo().get(0);

		assertEquals("Nome do modelo", "nome modelo", ms.getNomeModelo());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

		assertEquals("Data base", "01/Nov/2011", sdf.format(ms.getDataBase().getTime()));

		
		ms = simulateSwitches.getModelo().get(1);
		
		assertEquals("Nome do modelo", "nome modelo 2", ms.getNomeModelo());

		assertEquals("Data base", "01/Dec/2011", sdf.format(ms.getDataBase().getTime()));
	}

	@Test
	public void doRemoveTest() throws ParserException {
		doModeloLoad(new String[] { "modelo-rm", "nome modelo" }, Command.MODELO_REMOVE);

		assertNotNull("Switches", switches.getModeloRemoveSwitches());

		ModeloRemoveSwitches removeSwitches = switches.getModeloRemoveSwitches();

		assertEquals("Nome do modelo", "nome modelo", removeSwitches.getNomeModelo());
	}

	@Test
	public void doCloneTest() throws ParserException {
		doModeloLoad(new String[] { "modelo-clone", "modelo base", "modelo novo" }, Command.MODELO_CLONE);

		assertNotNull("Switches", switches.getModeloCloneSwitches());

		ModeloCloneSwitches removeSwitches = switches.getModeloCloneSwitches();

		assertEquals("Modelo base", "modelo base", removeSwitches.getModeloBase());
		assertEquals("Modelo novo", "modelo novo", removeSwitches.getModeloNovo());
	}

	@Test
	public void doAnalyseTest() throws ParserException {
		doModeloLoad(new String[] { "modelo-analyse", "nome modelo", "nov/2011" }, Command.MODELO_ANALYSE);

		assertNotNull("Switches", switches.getModeloAnalyseSwitches());

		ModeloAnalyseSwitches analyseSwitches = switches.getModeloAnalyseSwitches();
			
		assertEquals("Nome modelo", "nome modelo", analyseSwitches.getNomeModelo());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		assertEquals("Data base", "01/Nov/2011", sdf.format(analyseSwitches.getDataBase().getTime()));
		assertNull("Lancamento Range", analyseSwitches.getLancamentoRange());
	}

	@Test
	public void doAnalyseTest2() throws ParserException {
		doModeloLoad(new String[] { "modelo-analyse", "nome modelo", "nov/2011", "--lancamento-range", "05/nov/2011-25/nov/2011"}, Command.MODELO_ANALYSE);

		assertNotNull("Switches", switches.getModeloAnalyseSwitches());

		ModeloAnalyseSwitches analyseSwitches = switches.getModeloAnalyseSwitches();
			
		assertEquals("Nome modelo", "nome modelo", analyseSwitches.getNomeModelo());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		assertEquals("Data base", "01/Nov/2011", sdf.format(analyseSwitches.getDataBase().getTime()));
		
		assertNotNull("Lancamento Range", analyseSwitches.getLancamentoRange());
		assertEquals("Lancamento Range - inicio", "05/Nov/2011", sdf.format(analyseSwitches.getLancamentoRange().getStart().getTime()));
		assertEquals("Lancamento Range - fim", "25/Nov/2011", sdf.format(analyseSwitches.getLancamentoRange().getEnd().getTime()));
	}
}
