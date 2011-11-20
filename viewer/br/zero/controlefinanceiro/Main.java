package br.zero.controlefinanceiro;

import br.zero.controlefinanceiro.action.LancamentoListAction;
import br.zero.controlefinanceiro.commandlineparser.CommandLineLoader;
import br.zero.controlefinanceiro.commandlineparser.CommandLineSwitches;
import br.zero.controlefinanceiro.commandlineparser.Entity;
import br.zero.controlefinanceiro.commandlineparser.LancamentoCommand;
import br.zero.switchesparser.ParserException;
import br.zero.tinycontroller.TinyController;
import br.zero.tinycontroller.TinyControllerException;

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

		setupAndRunController(switches);
	}

	private CommandLineSwitches parseCommandLine(String[] args) throws ParserException {
		CommandLineLoader loader = new CommandLineLoader();

		loader.setArgs(args);

		loader.load();

		return loader.getSwitches();
	}

	private void setupAndRunController(CommandLineSwitches switches) throws TinyControllerException {
		TinyController controller = new TinyController();

		controller.registerAction(LancamentoListAction.class, switches.getLancamentoSwitches().getListSwitches(), Entity.LANCAMENTO, LancamentoCommand.LIST);

		controller.selectAction(switches.getEntity(), switches.getEntityCommand());

		if (!(controller.isActionFound())) {
			System.out.println("Ação não encontrada...");

			return;
		}

		controller.runSelectedAction();
	}

}
