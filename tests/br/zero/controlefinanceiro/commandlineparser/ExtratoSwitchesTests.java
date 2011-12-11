package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class ExtratoSwitchesTests extends CustomParserTests {

	private void doExtratoLoad(String[] args, Command command) throws ParserException {
		doLoad(args);

		assertEquals("Comando", command, switches.getEntity());
	}

	@Test
	public void doListTest() throws ParserException {
		doExtratoLoad(new String[] {"extrato-ls", "conta"}, Command.EXTRATO_LIST);
		
		assertNotNull(switches.getExtratoListSwitches());
		
		ExtratoListSwitches listSwitches = switches.getExtratoListSwitches(); 
			
		assertEquals("Nome da conta", "conta", listSwitches.getNomeConta());
	}
	
	@Test
	public void doImportTest() throws ParserException {
		doExtratoLoad(new String[] {"extrato-import", "conta", "nome arquivo"}, Command.EXTRATO_IMPORT);
		
		assertNotNull(switches.getExtratoImportSwitches());
		
		ExtratoImportSwitches importSwitches = switches.getExtratoImportSwitches(); 
			
		assertEquals("Nome da conta", "conta", importSwitches.getNomeConta());
		assertEquals("Nome do arquivo", "nome arquivo", importSwitches.getNomeArquivo());
	}
}
