package br.zero.controlefinanceiro;


import br.zero.controlefinanceiro.commandlineparser.CommandLineLoader;
import br.zero.controlefinanceiro.commandlineparser.CommandLineSwitches;
import br.zero.switchesparser.ParserException;
import br.zero.tinycontroller.Action;
import br.zero.tinycontroller.ActionException;

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
		
		runController(switches);
	}

	private CommandLineSwitches parseCommandLine(String[] args) throws ParserException {
		CommandLineLoader loader = new CommandLineLoader();
		
		loader.setArgs(args);
		
		loader.load();
		
		return loader.getSwitches();
	}
	
	private void runController(CommandLineSwitches switches) throws ActionException {
		Controller mainController = new Controller();
		
		mainController.setSwitches(switches);
		
		Action action = mainController.getAction();
		
		if (action != null) {
			action.run();
		} else {
			System.out.println("Ação não encontrada...");
		}
	}

}
