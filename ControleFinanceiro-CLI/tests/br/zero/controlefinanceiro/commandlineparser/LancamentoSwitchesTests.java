package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class LancamentoSwitchesTests extends CustomParserTests {
	
	private void doLancamentoLoad(String[] args, Command command) throws ParserException {
		doLoad(args);
		
		assertEquals("Comando", command, switches.getCommand());
	}

	@Test
	public void doListTest() throws ParserException {
		doLancamentoLoad(new String[] { "lanc-ls" }, Command.LANCAMENTO_LIST);

		assertNotNull(switches.getLancamentoListSwitches());
		
		LancamentoListSwitches listSwitches = switches.getLancamentoListSwitches(); 
			
		assertNull("Where", listSwitches.getWhere());
	}

	@Test
	public void doListTest2() throws ParserException {
		doLancamentoLoad(new String[] { "lanc-ls", "where" }, Command.LANCAMENTO_LIST);

		assertNotNull(switches.getLancamentoListSwitches());
		
		LancamentoListSwitches listSwitches = switches.getLancamentoListSwitches(); 
			
		assertEquals("Where", "where", listSwitches.getWhere());
	}

	@Test
	public void doBalanceTest() throws ParserException {
		doLancamentoLoad(new String[] { "lanc-balance", "conta" }, Command.LANCAMENTO_BALANCE);


		assertNotNull("switches", switches.getLancamentoBalanceSwitches());
		
		LancamentoBalanceSwitches balanceSwitches = switches.getLancamentoBalanceSwitches();
			
		assertEquals("Nome da conta", "conta", balanceSwitches.getConta());
		assertNull("Where", balanceSwitches.getWhere());
	}

	@Test
	public void doBalanceTest2() throws ParserException {
		doLancamentoLoad(new String[] { "lanc-balance", "conta", "where" }, Command.LANCAMENTO_BALANCE);

		assertNotNull("switches", switches.getLancamentoBalanceSwitches());
		
		LancamentoBalanceSwitches balanceSwitches = switches.getLancamentoBalanceSwitches();
		
		assertEquals("Nome da conta", "conta", balanceSwitches.getConta());
		assertEquals("Where", "where", balanceSwitches.getWhere());
	}

	private LancamentoAddSwitches baseAddTests(String[] args) throws ParserException {
		doLancamentoLoad(args, Command.LANCAMENTO_ADD);

		assertNotNull("switches", switches.getLancamentoAddSwitches());
		
		LancamentoAddSwitches addSwitches = switches.getLancamentoAddSwitches(); 
			
		assertEquals("Origem", "origem", addSwitches.getContaOrigem());
		assertEquals("Destino", "destino", addSwitches.getContaDestino());
		assertEquals("Valor", 2.55, addSwitches.getValor(), 0.0);

		return addSwitches;
	}

	@Test
	public void doAddTest() throws ParserException {
		LancamentoAddSwitches addSwitches = baseAddTests(new String[] { "lanc-add", "origem", "destino", "2.55" });

		assertNull("Observacao", addSwitches.getObservacao());
	}

	@Test
	public void doAddTests2() throws ParserException {
		LancamentoAddSwitches addSwitches = baseAddTests(new String[] { "lanc-add", "origem", "destino", "2.55", "observacao" });

		assertEquals("Observacao", "observacao", addSwitches.getObservacao());
	}

	private LancamentoAddFullSwitches baseAddFullTests(String[] args) throws ParserException {
		doLancamentoLoad(args, Command.LANCAMENTO_ADDFULL);
		
		assertNotNull("switches", switches.getLancamentoAddFullSwitches());
		
		LancamentoAddFullSwitches addFullSwitches = switches.getLancamentoAddFullSwitches();

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
		LancamentoAddFullSwitches addFullSwitches = baseAddFullTests(new String[] { "lanc-addfull", "105", "18/nov/2011", "origem", "destino", "3.99" });

		assertNull("Observacao", addFullSwitches.getObservacao());
	}

	@Test
	public void doAddFullTest2() throws ParserException {
		LancamentoAddFullSwitches addFullSwitches = baseAddFullTests(new String[] { "lanc-addfull", "105", "18/nov/2011", "origem", "destino", "3.99", "observacao" });

		assertEquals("Observacao", "observacao", addFullSwitches.getObservacao());
	}
	
	@Test
	public void doRemoveTest() throws ParserException {
		doLancamentoLoad(new String[] {"lanc-rm", "45"}, Command.LANCAMENTO_REMOVE);

		assertNotNull("Switches", switches.getLancamentoRemoveSwitches());
		
		LancamentoRemoveSwitches removeSwitches = switches.getLancamentoRemoveSwitches();
		
		assertEquals("ID", (Integer) 45, removeSwitches.getId());
	}
}