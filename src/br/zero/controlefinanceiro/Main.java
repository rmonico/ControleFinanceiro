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

		controller.registerAction(BackupAction.class, switches.getBackupSwitches(), Command.BACKUP);

		controller.registerAction(ContaListAction.class, switches.getContaListSwitches(), Command.CONTA_LIST);
		controller.registerAction(ContaAddAction.class, switches.getContaAddSwitches(), Command.CONTA_ADD);
		controller.registerAction(ContaRemoveAction.class, switches.getContaRemoveSwitches(), Command.CONTA_REMOVE);
		controller.registerAction(ContaSetBalanceAction.class, switches.getContaSetBalanceSwitches(), Command.CONTA_SETBALANCE);

		controller.registerAction(LancamentoListAction.class, switches.getLancamentoListSwitches(), Command.LANCAMENTO_LIST);
		controller.registerAction(LancamentoBalanceAction.class, switches.getLancamentoBalanceSwitches(), Command.LANCAMENTO_BALANCE);
		controller.registerAction(LancamentoAddAction.class, switches.getLancamentoAddSwitches(), Command.LANCAMENTO_ADD);
		controller.registerAction(LancamentoAddFullAction.class, switches.getLancamentoAddFullSwitches(), Command.LANCAMENTO_ADDFULL);
		controller.registerAction(LancamentoRemoveAction.class, switches.getLancamentoRemoveSwitches(), Command.LANCAMENTO_REMOVE);

		controller.registerAction(ModeloListAction.class, null, Command.MODELO_LIST);
		controller.registerAction(ModeloAddAction.class, switches.getModeloAddSwitches(), Command.MODELO_ADD);
		controller.registerAction(ModeloSimulateAction.class, switches.getModeloSimulateSwitches(), Command.MODELO_SIMULATE);
		controller.registerAction(ModeloRemoveAction.class, switches.getModeloRemoveSwitches(), Command.MODELO_REMOVE);
		controller.registerAction(ModeloCloneAction.class, switches.getModeloCloneSwitches(), Command.MODELO_CLONE);
		controller.registerAction(ModeloAnalyseAction.class, switches.getModeloAnalyseSwitches(), Command.MODELO_ANALYSE);

		controller.registerAction(LancamentoModeloListAction.class, switches.getLancamentoModeloListSwitches(), Command.LANCAMENTOMODELO_LIST);
		controller.registerAction(LancamentoModeloAddAction.class, switches.getLancamentoModeloAddSwitches(), Command.LANCAMENTOMODELO_ADD);
		controller.registerAction(LancamentoModeloRemoveAction.class, switches.getLancamentoModeloRemoveSwitches(), Command.LANCAMENTOMODELO_REMOVE);

		controller.registerAction(ExtratoListAction.class, switches.getExtratoListSwitches(), Command.EXTRATO_LIST);
		controller.registerAction(ExtratoImportAction.class, switches.getExtratoImportSwitches(), Command.EXTRATO_IMPORT);
		controller.registerAction(ExtratoAnalyseAction.class, switches.getExtratoAnalyseSwitches(), Command.EXTRATO_ANALYSE);

		controller.registerAction(ContaReferenciaListAction.class, switches.getContaReferenciaListSwitches(), Command.CONTAREFERENCIA_LIST);
		controller.registerAction(ContaReferenciaAddAction.class, switches.getContaReferenciaAddSwitches(), Command.CONTAREFERENCIA_ADD);

		controller.registerAction(HelpAction.class, switches.getHelpSwitches(), Command.HELP);

		controller.selectAction(switches.getCommand());

		if (!(controller.isActionFound())) {
			System.out.println("Ação não encontrada...");

			return;
		}

		controller.runSelectedAction();
	}

}
