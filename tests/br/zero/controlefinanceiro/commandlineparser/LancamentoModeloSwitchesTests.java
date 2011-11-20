package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

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

	@Test
	public void doListTest2() throws ParserException {
		LancamentoModeloListSwitches listSwitches = doLancamentoModeloListLoad(new String[] { "lancmodelo", "ls", "modelo" });

		assertEquals("Nome do modelo", "modelo", listSwitches.getModelo());
	}

	private LancamentoModeloAddSwitches doLancamentoModeloAddLoad(String[] args) throws ParserException {
		doLancamentoModeloLoad(args, LancamentoModeloCommand.ADD);

		assertNull("Switches de lista", lancamentoModeloSwitches.getListSwitches());
		assertNotNull("Switches de add", lancamentoModeloSwitches.getAddSwitches());
		assertNull("Switches de remove", lancamentoModeloSwitches.getRemoveSwitches());

		return lancamentoModeloSwitches.getAddSwitches();
	}

	private LancamentoModeloAddSwitches baseAddTests(String[] args) throws ParserException {
		LancamentoModeloAddSwitches addSwitches = doLancamentoModeloAddLoad(args);

		assertEquals("Nome", "modelo", addSwitches.getModelo());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		assertEquals("Data", "19/Nov/2011", sdf.format(addSwitches.getData().getTime()));
		assertEquals("Conta origem", "conta origem", addSwitches.getContaOrigem());
		assertEquals("Conta destino", "conta destino", addSwitches.getContaDestino());
		assertEquals("Valor", 1.99, addSwitches.getValor(), 0);

		return addSwitches;
	}

	@Test
	public void doAddTest() throws ParserException {
		LancamentoModeloAddSwitches addSwitches = baseAddTests(new String[] { "lancmodelo", "add", "modelo", "19/nov/2011", "conta origem", "conta destino", "1.99" });

		assertNull("Observacao", addSwitches.getObservacao());
	}

	@Test
	public void doAddTest2() throws ParserException {
		LancamentoModeloAddSwitches addSwitches = baseAddTests(new String[] { "lancmodelo", "add", "modelo", "19/nov/2011", "conta origem", "conta destino", "1.99", "observacao" });

		assertEquals("Observacao", "observacao", addSwitches.getObservacao());
	}
	
	@Test
	public void doRemoveTest() throws ParserException {
		doLancamentoModeloLoad(new String[] {"lancmodelo", "rm", "54"}, LancamentoModeloCommand.REMOVE);
		
		assertNull("Switches de lista", lancamentoModeloSwitches.getListSwitches());
		assertNull("Switches de add", lancamentoModeloSwitches.getAddSwitches());
		assertNotNull("Switches de remove", lancamentoModeloSwitches.getRemoveSwitches());

		
		LancamentoModeloRemoveSwitches removeSwitches = lancamentoModeloSwitches.getRemoveSwitches();
		
		assertEquals("ID", 54, removeSwitches.getId());
	}
}
