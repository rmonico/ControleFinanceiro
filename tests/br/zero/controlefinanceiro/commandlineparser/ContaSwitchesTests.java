package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.zero.switchesparser.ParserException;

public class ContaSwitchesTests extends CustomParserTests {
	
	private ContaSwitches contaSwitches;

	protected void doContaLoad(String[] args, ContaCommand contaCommand) throws ParserException {
		doLoad(args);
		
		assertEquals("Entidade", Entity.CONTA, switches.getEntity());
		
		contaSwitches = switches.getContaSwitches();
		
		assertEquals("Comando da conta", contaCommand, contaSwitches.getCommand());
	}
	
	private ContaListSwitches doContaListLoad(String[] args) throws ParserException {
		doContaLoad(args, ContaCommand.LIST);
		
		assertNotNull(contaSwitches.getListSwitches());
		assertNull(contaSwitches.getAddSwitches());
		assertNull(contaSwitches.getRemoveSwitches());
		
		return contaSwitches.getListSwitches();
	}

	@Test
	public void doContaListTest() throws ParserException {
		ContaListSwitches contaListSwitches = doContaListLoad(new String[] {"conta", "ls"});
		
		assertNull("clausula where", contaListSwitches.getWhere());
	}
	
	@Test
	public void doContaListTest2() throws ParserException {
		ContaListSwitches contaListSwitches = doContaListLoad(new String[] {"conta", "ls", "where_clause"});
		
		assertEquals("clausula where", "where_clause", contaListSwitches.getWhere());
	}
	
	private ContaAddSwitches doContaAddLoad(String[] args) throws ParserException {
		doContaLoad(args, ContaCommand.ADD);
		
		assertNull(contaSwitches.getListSwitches());
		assertNotNull(contaSwitches.getAddSwitches());
		assertNull(contaSwitches.getRemoveSwitches());

		return contaSwitches.getAddSwitches();
	}
	
	@Test
	public void doContaAddTest() throws ParserException {
		ContaAddSwitches contaAddSwitches = doContaAddLoad(new String[] {"conta", "add", "nova conta"});
		
		assertEquals("nome da nova conta", "nova conta", contaAddSwitches.getNome());
		assertNull("conta sem observacao", contaAddSwitches.getObservacao());
	}

	@Test
	public void doContaAddTest2() throws ParserException {
		ContaAddSwitches contaAddSwitches = doContaAddLoad(new String[] {"conta", "add", "nova conta", "Observação da nova conta"});
		
		assertEquals("Nome", "nova conta", contaAddSwitches.getNome());
		assertEquals("Observacao", "Observação da nova conta", contaAddSwitches.getObservacao());
	}
	
	private ContaRemoveSwitches doContaRemoveLoad(String[] args) throws ParserException {
		doContaLoad(args, ContaCommand.REMOVE);
		
		assertNull(contaSwitches.getListSwitches());
		assertNull(contaSwitches.getAddSwitches());
		assertNotNull(contaSwitches.getRemoveSwitches());

		return contaSwitches.getRemoveSwitches();
	}
	
	@Test
	public void doContaRemoveTest() throws ParserException {
		ContaRemoveSwitches contaRemoveSwitches = doContaRemoveLoad(new String[] {"conta", "rm", "nome conta"});
		
		assertEquals("Nome da conta a ser removida", "nome conta", contaRemoveSwitches.getContaNome());
	}
}
