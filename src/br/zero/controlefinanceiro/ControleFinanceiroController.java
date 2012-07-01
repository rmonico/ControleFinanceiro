package br.zero.controlefinanceiro;

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
import br.zero.controlefinanceiro.commandlineparser.CommandLineSwitches;
import br.zero.tinycontroller.TinyController;
import br.zero.tinycontroller.TinyControllerException;

public class ControleFinanceiroController {
	
	private static TinyController controller;

	public static void setup() throws TinyControllerException {
		controller = new TinyController();

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
	}

	public static Object run(CommandLineSwitches switches) throws TinyControllerException {
		@SuppressWarnings("rawtypes")
		Class actionClass = controller.selectAction(switches.getCommand());

		if (!(controller.isActionFound())) {
			System.out.println("Ação não encontrada...");
			
			return null;
		}

		@SuppressWarnings("unchecked")
		Object actionResult = controller.runAction(switches.getSubCommandSwitches(), actionClass);
		
		return actionResult;
	}

}
