package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class ExtratoSwitchesTests extends CustomParserTests {

	private void doExtratoLoad(String[] args, Command command) throws ParserException {
		doLoad(args);

		assertEquals("Comando", command, switches.getCommand());
	}

	@Test
	public void doListTest() throws ParserException {
		doExtratoLoad(new String[] { "extrato-ls", "conta" }, Command.EXTRATO_LIST);

		assertNotNull(switches.getExtratoListSwitches());

		ExtratoListSwitches listSwitches = switches.getExtratoListSwitches();

		assertEquals("Nome da conta", "conta", listSwitches.getNomeConta());
	}

	@Test
	public void doImportTest() throws ParserException {
		doExtratoLoad(new String[] { "extrato-import", "conta", "nome arquivo" }, Command.EXTRATO_IMPORT);

		assertNotNull(switches.getExtratoImportSwitches());

		ExtratoImportSwitches importSwitches = switches.getExtratoImportSwitches();

		assertEquals("Nome da conta", "conta", importSwitches.getNomeConta());
		assertEquals("Nome do arquivo", "nome arquivo", importSwitches.getNomeArquivo());
	}

	@Test
	public void doAnalyseTest() throws ParserException {
		doExtratoLoad(new String[] { "extrato-analyse", "conta" }, Command.EXTRATO_ANALYSE);

		assertNotNull(switches.getExtratoAnalyseSwitches());

		ExtratoAnalyseSwitches analyseSwitches = switches.getExtratoAnalyseSwitches();

		assertEquals("Nome do banco", "conta", analyseSwitches.getNomeBanco());
		assertFalse("Flag realize", analyseSwitches.getRealize());
		assertNotNull("Referências manuais - null", analyseSwitches.getManualRefList());
		assertEquals("Referências manuais - size", 0, analyseSwitches.getManualRefList().size());
	}

	@Test
	public void doAnalyseTest2() throws ParserException {
		doExtratoLoad(new String[] { "extrato-analyse", "conta", "--realize" }, Command.EXTRATO_ANALYSE);

		assertNotNull(switches.getExtratoAnalyseSwitches());

		ExtratoAnalyseSwitches analyseSwitches = switches.getExtratoAnalyseSwitches();

		assertEquals("Nome do banco", "conta", analyseSwitches.getNomeBanco());
		assertTrue("Flag realize", analyseSwitches.getRealize());
		assertNotNull("Referências manuais - null", analyseSwitches.getManualRefList());
		assertEquals("Referências manuais - size", 0, analyseSwitches.getManualRefList().size());
	}

	@Test
	public void doAnalyseTest3() throws ParserException {
		doExtratoLoad(new String[] { "extrato-analyse", "conta", "--refs", "conta1", "regex1", "conta2", "regex2"}, Command.EXTRATO_ANALYSE);

		assertNotNull(switches.getExtratoAnalyseSwitches());

		ExtratoAnalyseSwitches analyseSwitches = switches.getExtratoAnalyseSwitches();

		assertEquals("Nome do banco", "conta", analyseSwitches.getNomeBanco());
		assertFalse("Flag realize", analyseSwitches.getRealize());
		assertNotNull("Referências manuais - null", analyseSwitches.getManualRefList());
		assertEquals("Referências manuais - size", 2, analyseSwitches.getManualRefList().size());
		assertEquals("Referência 0 - regex", "regex1", analyseSwitches.getManualRefList().get(0).getRegex());
		assertEquals("Referência 0 - conta", "conta1", analyseSwitches.getManualRefList().get(0).getNomeConta());

		assertEquals("Referência 1 - regex", "regex2", analyseSwitches.getManualRefList().get(1).getRegex());
		assertEquals("Referência 1 - regex", "conta2", analyseSwitches.getManualRefList().get(1).getNomeConta());
	}
}
