package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class LancamentoSwitchesTests extends CustomParserTests {

	private LancamentoSwitches lancamentoSwitches;

	private void doLancamentoLoad(String[] args, LancamentoCommand lancamentoCommand) throws ParserException {
		doLoad(args);

		assertEquals("Entidade", Command.LANCAMENTO, switches.getEntity());

		lancamentoSwitches = switches.getLancamentoSwitches();

		assertEquals("Comando do lancamento", lancamentoCommand, lancamentoSwitches.getCommand());
	}

	private LancamentoListSwitches doLancamentoListLoad(String[] args) throws ParserException {
		doLancamentoLoad(args, LancamentoCommand.LIST);

		assertNotNull("Switches de lista", lancamentoSwitches.getListSwitches());
		assertNull("Switches de balance", lancamentoSwitches.getBalanceSwitches());
		assertNull("Switches de add", lancamentoSwitches.getAddSwitches());
		assertNull("Switches de addfull", lancamentoSwitches.getAddFullSwitches());
		assertNull("Switches de remove", lancamentoSwitches.getRemoveSwitches());

		return lancamentoSwitches.getListSwitches();
	}

	@Test
	public void doListTest() throws ParserException {
		LancamentoListSwitches listSwitches = doLancamentoListLoad(new String[] { "lanc", "ls" });

		assertNull("Where", listSwitches.getWhere());
	}

	@Test
	public void doListTest2() throws ParserException {
		LancamentoListSwitches listSwitches = doLancamentoListLoad(new String[] { "lanc", "ls", "where" });

		assertEquals("Where", "where", listSwitches.getWhere());
	}

	private LancamentoBalanceSwitches doLancamentoBalanceLoad(String[] args) throws ParserException {
		doLancamentoLoad(args, LancamentoCommand.BALANCE);

		assertNull("Switches de lista", lancamentoSwitches.getListSwitches());
		assertNotNull("Switches de balance", lancamentoSwitches.getBalanceSwitches());
		assertNull("Switches de add", lancamentoSwitches.getAddSwitches());
		assertNull("Switches de addfull", lancamentoSwitches.getAddFullSwitches());
		assertNull("Switches de remove", lancamentoSwitches.getRemoveSwitches());

		return lancamentoSwitches.getBalanceSwitches();
	}

	@Test
	public void doBalanceTest() throws ParserException {
		LancamentoBalanceSwitches balanceSwitches = doLancamentoBalanceLoad(new String[] { "lanc", "balance", "conta" });

		assertEquals("Nome da conta", "conta", balanceSwitches.getConta());
		assertNull("Where", balanceSwitches.getWhere());
	}

	@Test
	public void doBalanceTest2() throws ParserException {
		LancamentoBalanceSwitches balanceSwitches = doLancamentoBalanceLoad(new String[] { "lanc", "balance", "conta", "where" });

		assertEquals("Nome da conta", "conta", balanceSwitches.getConta());
		assertEquals("Where", "where", balanceSwitches.getWhere());
	}

	private LancamentoAddSwitches doLancamentoAddLoad(String[] args) throws ParserException {
		doLancamentoLoad(args, LancamentoCommand.ADD);

		assertNull("Switches de lista", lancamentoSwitches.getListSwitches());
		assertNull("Switches de balance", lancamentoSwitches.getBalanceSwitches());
		assertNotNull("Switches de add", lancamentoSwitches.getAddSwitches());
		assertNull("Switches de addfull", lancamentoSwitches.getAddFullSwitches());
		assertNull("Switches de remove", lancamentoSwitches.getRemoveSwitches());

		return lancamentoSwitches.getAddSwitches();
	}

	private LancamentoAddSwitches baseAddTests(String[] args) throws ParserException {
		LancamentoAddSwitches addSwitches = doLancamentoAddLoad(args);

		assertEquals("Origem", "origem", addSwitches.getContaOrigem());
		assertEquals("Destino", "destino", addSwitches.getContaDestino());
		assertEquals("Valor", 2.55, addSwitches.getValor(), 0.0);

		return addSwitches;
	}

	@Test
	public void doAddTest() throws ParserException {
		LancamentoAddSwitches addSwitches = baseAddTests(new String[] { "lanc", "add", "origem", "destino", "2.55" });

		assertNull("Observacao", addSwitches.getObservacao());
	}

	@Test
	public void doAddTests2() throws ParserException {
		LancamentoAddSwitches addSwitches = baseAddTests(new String[] { "lanc", "add", "origem", "destino", "2.55", "observacao" });

		assertEquals("Observacao", "observacao", addSwitches.getObservacao());
	}

	private LancamentoAddFullSwitches doLancamentoAddFullLoad(String[] args) throws ParserException {
		doLancamentoLoad(args, LancamentoCommand.ADD_FULL);

		assertNull("Switches de lista", lancamentoSwitches.getListSwitches());
		assertNull("Switches de balance", lancamentoSwitches.getBalanceSwitches());
		assertNull("Switches de add", lancamentoSwitches.getAddSwitches());
		assertNotNull("Switches de addfull", lancamentoSwitches.getAddFullSwitches());
		assertNull("Switches de remove", lancamentoSwitches.getRemoveSwitches());

		return lancamentoSwitches.getAddFullSwitches();
	}

	private LancamentoAddFullSwitches baseAddFullTests(String[] args) throws ParserException {
		LancamentoAddFullSwitches addFullSwitches = doLancamentoAddFullLoad(args);

		assertEquals("Modelo", (Integer) 105, addFullSwitches.getLancamentoModeloID());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		assertEquals("Data", "18/Nov/2011", sdf.format(addFullSwitches.getData().getTime()));
		assertEquals("Origem", "origem", addFullSwitches.getContaOrigem());
		assertEquals("Origem", "origem", addFullSwitches.getContaOrigem());
		assertEquals("Destino", "destino", addFullSwitches.getContaDestino());
		assertEquals("Valor", 3.99, addFullSwitches.getValor(), 0.0);

		return addFullSwitches;
	}

	@Test
	public void doAddFullTest() throws ParserException {
		LancamentoAddFullSwitches addFullSwitches = baseAddFullTests(new String[] { "lanc", "addfull", "105", "18/nov/2011", "origem", "destino", "3.99" });

		assertNull("Observacao", addFullSwitches.getObservacao());
	}

	@Test
	public void doAddFullTest2() throws ParserException {
		LancamentoAddFullSwitches addFullSwitches = baseAddFullTests(new String[] { "lanc", "addfull", "105", "18/nov/2011", "origem", "destino", "3.99", "observacao" });

		assertEquals("Observacao", "observacao", addFullSwitches.getObservacao());
	}
	
	@Test
	public void doRemoveTest() throws ParserException {
		doLancamentoLoad(new String[] {"lanc", "rm", "45"}, LancamentoCommand.REMOVE);

		assertNull("Switches de lista", lancamentoSwitches.getListSwitches());
		assertNull("Switches de balance", lancamentoSwitches.getBalanceSwitches());
		assertNull("Switches de add", lancamentoSwitches.getAddSwitches());
		assertNull("Switches de addfull", lancamentoSwitches.getAddFullSwitches());
		assertNotNull("Switches de remove", lancamentoSwitches.getRemoveSwitches());

		LancamentoRemoveSwitches removeSwitches = lancamentoSwitches.getRemoveSwitches();
		
		assertEquals("ID", (Integer) 45, removeSwitches.getId());
	}
}