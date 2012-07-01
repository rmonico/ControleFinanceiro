package br.zero.controlefinanceiro;

import br.zero.commandlineparser.ParserException;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoParsers;
import br.zero.controlefinanceiro.commandlineparser.CommandLineLoader;
import br.zero.controlefinanceiro.commandlineparser.CommandLineSwitches;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Main main = new Main();

		main.run(args);
	}

	private void run(String[] args) throws Exception {
		CommandLineSwitches switches = parseCommandLine(args);

		ControleFinanceiroController.setup();
		
		Object result = ControleFinanceiroController.run(switches);
		
		
	}

	private CommandLineSwitches parseCommandLine(String[] args) throws ParserException {
		CommandLineLoader loader = new CommandLineLoader();

		loader.setArgs(args);

		loader.load();

		return loader.getSwitches();
	}

}
