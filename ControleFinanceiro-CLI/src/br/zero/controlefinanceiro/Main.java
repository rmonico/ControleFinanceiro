package br.zero.controlefinanceiro;

import br.zero.commandlineparser.ParserException;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoParsers;
import br.zero.controlefinanceiro.actions.BackupAction;
import br.zero.controlefinanceiro.actions.ContaAddAction;
import br.zero.controlefinanceiro.actions.ContaListAction;
import br.zero.controlefinanceiro.actions.ContaReferenciaAddAction;
import br.zero.controlefinanceiro.actions.ContaReferenciaListAction;
import br.zero.controlefinanceiro.actions.ContaRemoveAction;
import br.zero.controlefinanceiro.actions.ContaSetBalanceAction;
import br.zero.controlefinanceiro.actions.ExtratoAnalyseAction;
import br.zero.controlefinanceiro.actions.ExtratoImportAction;
import br.zero.controlefinanceiro.actions.ExtratoListAction;
import br.zero.controlefinanceiro.actions.HelpAction;
import br.zero.controlefinanceiro.actions.LancamentoAddAction;
import br.zero.controlefinanceiro.actions.LancamentoAddFullAction;
import br.zero.controlefinanceiro.actions.LancamentoBalanceAction;
import br.zero.controlefinanceiro.actions.LancamentoListAction;
import br.zero.controlefinanceiro.actions.LancamentoModeloAddAction;
import br.zero.controlefinanceiro.actions.LancamentoModeloListAction;
import br.zero.controlefinanceiro.actions.LancamentoModeloRemoveAction;
import br.zero.controlefinanceiro.actions.LancamentoRemoveAction;
import br.zero.controlefinanceiro.actions.ModeloAddAction;
import br.zero.controlefinanceiro.actions.ModeloAnalyseAction;
import br.zero.controlefinanceiro.actions.ModeloCloneAction;
import br.zero.controlefinanceiro.actions.ModeloListAction;
import br.zero.controlefinanceiro.actions.ModeloRemoveAction;
import br.zero.controlefinanceiro.actions.ModeloSimulateAction;
import br.zero.controlefinanceiro.commandlineparser.Command;
import br.zero.controlefinanceiro.commandlineparser.CommandLineLoader;
import br.zero.controlefinanceiro.commandlineparser.CommandLineSwitches;
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

		ExtratoParsers.registerParsers();

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

		controller.registerAction(BackupAction.class, Command.BACKUP);

		controller.registerAction(ContaListAction.class, Command.CONTA_LIST);
		controller.registerAction(ContaAddAction.class, Command.CONTA_ADD);
		controller.registerAction(ContaRemoveAction.class, Command.CONTA_REMOVE);
		controller.registerAction(ContaSetBalanceAction.class, Command.CONTA_SETBALANCE);

		controller.registerAction(LancamentoListAction.class, Command.LANCAMENTO_LIST);
		controller.registerAction(LancamentoBalanceAction.class, Command.LANCAMENTO_BALANCE);
		controller.registerAction(LancamentoAddAction.class, Command.LANCAMENTO_ADD);
		controller.registerAction(LancamentoAddFullAction.class, Command.LANCAMENTO_ADDFULL);
		controller.registerAction(LancamentoRemoveAction.class, Command.LANCAMENTO_REMOVE);

		controller.registerAction(ModeloListAction.class, Command.MODELO_LIST);
		controller.registerAction(ModeloAddAction.class, Command.MODELO_ADD);
		controller.registerAction(ModeloSimulateAction.class, Command.MODELO_SIMULATE);
		controller.registerAction(ModeloRemoveAction.class, Command.MODELO_REMOVE);
		controller.registerAction(ModeloCloneAction.class, Command.MODELO_CLONE);
		controller.registerAction(ModeloAnalyseAction.class, Command.MODELO_ANALYSE);

		controller.registerAction(LancamentoModeloListAction.class, Command.LANCAMENTOMODELO_LIST);
		controller.registerAction(LancamentoModeloAddAction.class, Command.LANCAMENTOMODELO_ADD);
		controller.registerAction(LancamentoModeloRemoveAction.class, Command.LANCAMENTOMODELO_REMOVE);

		controller.registerAction(ExtratoListAction.class, Command.EXTRATO_LIST);
		controller.registerAction(ExtratoImportAction.class, Command.EXTRATO_IMPORT);
		controller.registerAction(ExtratoAnalyseAction.class, Command.EXTRATO_ANALYSE);

		controller.registerAction(ContaReferenciaListAction.class, Command.CONTAREFERENCIA_LIST);
		controller.registerAction(ContaReferenciaAddAction.class, Command.CONTAREFERENCIA_ADD);

		controller.registerAction(HelpAction.class, Command.HELP);

		if (!(controller.selectAction(switches.getCommand()))) {
			System.out.println("Ação não encontrada...");

			return;
		}

		controller.runSelectedAction(switches.getSubCommandSwitches());
	}

}
