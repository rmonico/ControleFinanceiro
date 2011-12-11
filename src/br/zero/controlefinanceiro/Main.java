package br.zero.controlefinanceiro;

import br.zero.commandlineparser.ParserException;
import br.zero.controlefinanceiro.actions.BackupAction;
import br.zero.controlefinanceiro.actions.ContaAddAction;
import br.zero.controlefinanceiro.actions.ContaListAction;
import br.zero.controlefinanceiro.actions.ContaRemoveAction;
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
import br.zero.controlefinanceiro.commandlineparser.CommandLineLoader;
import br.zero.controlefinanceiro.commandlineparser.CommandLineSwitches;
import br.zero.controlefinanceiro.commandlineparser.ContaCommand;
import br.zero.controlefinanceiro.commandlineparser.Command;
import br.zero.controlefinanceiro.commandlineparser.ExtratoCommand;
import br.zero.controlefinanceiro.commandlineparser.LancamentoCommand;
import br.zero.controlefinanceiro.commandlineparser.LancamentoModeloCommand;
import br.zero.controlefinanceiro.commandlineparser.ModeloCommand;
import br.zero.controlefinanceiro.utils.ExtratoParsers;
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

		registerParsers();

		setupAndRunController(switches);
	}

	private void registerParsers() {
		ExtratoParsers.registerParsers();
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

		controller.registerAction(ContaListAction.class, switches.getContaSubSwitches(ContaCommand.LIST), Command.CONTA, ContaCommand.LIST);
		controller.registerAction(ContaAddAction.class, switches.getContaSubSwitches(ContaCommand.ADD), Command.CONTA, ContaCommand.ADD);
		controller.registerAction(ContaRemoveAction.class, switches.getContaSubSwitches(ContaCommand.REMOVE), Command.CONTA, ContaCommand.REMOVE);

		controller.registerAction(LancamentoListAction.class, switches.getLancamentoSubSwitches(LancamentoCommand.LIST), Command.LANCAMENTO, LancamentoCommand.LIST);
		controller.registerAction(LancamentoBalanceAction.class, switches.getLancamentoSubSwitches(LancamentoCommand.BALANCE), Command.LANCAMENTO, LancamentoCommand.BALANCE);
		controller.registerAction(LancamentoAddAction.class, switches.getLancamentoSubSwitches(LancamentoCommand.ADD), Command.LANCAMENTO, LancamentoCommand.ADD);
		controller.registerAction(LancamentoAddFullAction.class, switches.getLancamentoSubSwitches(LancamentoCommand.ADD_FULL), Command.LANCAMENTO, LancamentoCommand.ADD_FULL);
		controller.registerAction(LancamentoRemoveAction.class, switches.getLancamentoSubSwitches(LancamentoCommand.REMOVE), Command.LANCAMENTO, LancamentoCommand.REMOVE);

		controller.registerAction(ModeloListAction.class, switches.getModeloSubSwitches(ModeloCommand.LIST), Command.MODELO, ModeloCommand.LIST);
		controller.registerAction(ModeloAddAction.class, switches.getModeloSubSwitches(ModeloCommand.ADD), Command.MODELO, ModeloCommand.ADD);
		controller.registerAction(ModeloSimulateAction.class, switches.getModeloSubSwitches(ModeloCommand.SIMULATE), Command.MODELO, ModeloCommand.SIMULATE);
		controller.registerAction(ModeloRemoveAction.class, switches.getModeloSubSwitches(ModeloCommand.REMOVE), Command.MODELO, ModeloCommand.REMOVE);
		controller.registerAction(ModeloCloneAction.class, switches.getModeloSubSwitches(ModeloCommand.CLONE), Command.MODELO, ModeloCommand.CLONE);
		controller.registerAction(ModeloAnalyseAction.class, switches.getModeloSubSwitches(ModeloCommand.ANALYSE), Command.MODELO, ModeloCommand.ANALYSE);

		controller.registerAction(LancamentoModeloListAction.class, switches.getLancamentoModeloSubSwitches(LancamentoModeloCommand.LIST), Command.LANCAMENTO_MODELO, LancamentoModeloCommand.LIST);
		controller.registerAction(LancamentoModeloAddAction.class, switches.getLancamentoModeloSubSwitches(LancamentoModeloCommand.ADD), Command.LANCAMENTO_MODELO, LancamentoModeloCommand.ADD);
		controller.registerAction(LancamentoModeloRemoveAction.class, switches.getLancamentoModeloSubSwitches(LancamentoModeloCommand.REMOVE), Command.LANCAMENTO_MODELO, LancamentoModeloCommand.REMOVE);

		controller.registerAction(ExtratoListAction.class, switches.getExtratoSubSwitches(ExtratoCommand.LIST), Command.EXTRATO, ExtratoCommand.LIST);
		controller.registerAction(ExtratoImportAction.class, switches.getExtratoSubSwitches(ExtratoCommand.IMPORT), Command.EXTRATO, ExtratoCommand.IMPORT);
		controller.registerAction(ExtratoAnalyseAction.class, switches.getExtratoSubSwitches(ExtratoCommand.ANALYSE), Command.EXTRATO, ExtratoCommand.ANALYSE);

		controller.registerAction(HelpAction.class, switches.getHelpSwitches(), Command.HELP);

		controller.selectAction(switches.getEntity(), switches.getEntityCommand());

		if (!(controller.isActionFound())) {
			System.out.println("Ação não encontrada...");

			return;
		}

		controller.runSelectedAction();
	}

}
