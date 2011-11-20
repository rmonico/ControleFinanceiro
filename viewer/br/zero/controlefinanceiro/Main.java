package br.zero.controlefinanceiro;

import br.zero.commandlineparser.ParserException;
import br.zero.controlefinanceiro.actions.BackupAction;
import br.zero.controlefinanceiro.actions.ContaAddAction;
import br.zero.controlefinanceiro.actions.ContaListAction;
import br.zero.controlefinanceiro.actions.ContaRemoveAction;
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
import br.zero.controlefinanceiro.actions.ModeloCloneAction;
import br.zero.controlefinanceiro.actions.ModeloListAction;
import br.zero.controlefinanceiro.actions.ModeloRemoveAction;
import br.zero.controlefinanceiro.commandlineparser.CommandLineLoader;
import br.zero.controlefinanceiro.commandlineparser.CommandLineSwitches;
import br.zero.controlefinanceiro.commandlineparser.ContaCommand;
import br.zero.controlefinanceiro.commandlineparser.Entity;
import br.zero.controlefinanceiro.commandlineparser.LancamentoCommand;
import br.zero.controlefinanceiro.commandlineparser.LancamentoModeloCommand;
import br.zero.controlefinanceiro.commandlineparser.ModeloCommand;
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


		controller.registerAction(BackupAction.class, switches.getBackupSwitches(), Entity.BACKUP);
		
		controller.registerAction(ContaListAction.class, switches.getContaSwitches().getListSwitches(), Entity.CONTA, ContaCommand.LIST);
		controller.registerAction(ContaAddAction.class, switches.getContaSwitches().getAddSwitches(), Entity.CONTA, ContaCommand.ADD);
		controller.registerAction(ContaRemoveAction.class, switches.getContaSwitches().getRemoveSwitches(), Entity.CONTA, ContaCommand.REMOVE);

		controller.registerAction(LancamentoListAction.class, switches.getLancamentoSwitches().getListSwitches(), Entity.LANCAMENTO, LancamentoCommand.LIST);
		controller.registerAction(LancamentoBalanceAction.class, switches.getLancamentoSwitches().getBalanceSwitches(), Entity.LANCAMENTO, LancamentoCommand.BALANCE);
		controller.registerAction(LancamentoAddAction.class, switches.getLancamentoSwitches().getAddSwitches(), Entity.LANCAMENTO, LancamentoCommand.ADD);
		controller.registerAction(LancamentoAddFullAction.class, switches.getLancamentoSwitches().getAddFullSwitches(), Entity.LANCAMENTO, LancamentoCommand.ADD_FULL);
		controller.registerAction(LancamentoRemoveAction.class, switches.getLancamentoSwitches().getRemoveSwitches(), Entity.LANCAMENTO, LancamentoCommand.REMOVE);

		controller.registerAction(ModeloListAction.class, switches.getModeloSwitches().getListSwitches(), Entity.MODELO, ModeloCommand.LIST);
		controller.registerAction(ModeloAddAction.class, switches.getModeloSwitches().getAddSwitches(), Entity.MODELO, ModeloCommand.ADD);
		controller.registerAction(ModeloSimulateAction.class, switches.getModeloSwitches().getSimulateSwitches(), Entity.MODELO, ModeloCommand.SIMULATE);
		controller.registerAction(ModeloRemoveAction.class, switches.getModeloSwitches().getRemoveSwitches(), Entity.MODELO, ModeloCommand.REMOVE);
		controller.registerAction(ModeloCloneAction.class, switches.getModeloSwitches().getCloneSwitches(), Entity.MODELO, ModeloCommand.CLONE);
		
		controller.registerAction(LancamentoModeloListAction.class, switches.getLancamentoModeloSwitches().getListSwitches(), Entity.LANCAMENTO_MODELO, LancamentoModeloCommand.LIST);
		controller.registerAction(LancamentoModeloAddAction.class, switches.getLancamentoModeloSwitches().getAddSwitches(), Entity.LANCAMENTO_MODELO, LancamentoModeloCommand.ADD);
		controller.registerAction(LancamentoModeloRemoveAction.class, switches.getLancamentoModeloSwitches().getRemoveSwitches(), Entity.LANCAMENTO_MODELO, LancamentoModeloCommand.REMOVE);
		
		controller.registerAction(HelpAction.class, switches.getHelpSwitches(), Entity.HELP);

		
		controller.selectAction(switches.getEntity(), switches.getEntityCommand());

		if (!(controller.isActionFound())) {
			System.out.println("Ação não encontrada...");

			return;
		}

		controller.runSelectedAction();
	}

}
