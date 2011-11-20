package br.zero.controlefinanceiro;

import br.zero.controlefinanceiro.action.ContaListAction;
import br.zero.controlefinanceiro.action.ContaRemoveAction;
import br.zero.controlefinanceiro.action.LancamentoAddAction;
import br.zero.controlefinanceiro.action.LancamentoAddFullAction;
import br.zero.controlefinanceiro.action.LancamentoBalanceAction;
import br.zero.controlefinanceiro.action.LancamentoListAction;
import br.zero.controlefinanceiro.action.LancamentoRemoveAction;
import br.zero.controlefinanceiro.action.ModeloAddAction;
import br.zero.controlefinanceiro.actions.Action;
import br.zero.controlefinanceiro.actions.BackupAction;
import br.zero.controlefinanceiro.actions.ContaAddAction;
import br.zero.controlefinanceiro.commandlineparser.CommandLineSwitches;
import br.zero.controlefinanceiro.commandlineparser.ContaSwitches;
import br.zero.controlefinanceiro.commandlineparser.LancamentoSwitches;
import br.zero.controlefinanceiro.commandlineparser.ModeloSwitches;

public class Controller {

	private CommandLineSwitches switches;

	public void setSwitches(CommandLineSwitches value) {
		switches = value;
	}

	private class ActionData {

		private Action action;
		private Object switches;

		public Action getAction() {
			return action;
		}

		public void setAction(Action action) {
			this.action = action;
		}

		public Object getSwitches() {
			return switches;
		}

		public void setSwitches(Object switches) {
			this.switches = switches;
		}

	}

	public Action getAction() {

		ActionData data = new ActionData();

		switch (switches.getEntity()) {
		case BACKUP: {

			data.setAction(new BackupAction());
			data.setSwitches(switches.getBackupSwitches());

			break;
		}

		case CONTA: {

			ContaSwitches contaSwitches = switches.getContaSwitches();

			switch (contaSwitches.getCommand()) {
			case ADD: {
				data.setAction(new ContaAddAction());
				data.setSwitches(contaSwitches.getAddSwitches());

				break;
			}
			case LIST: {
				data.setAction(new ContaListAction());
				data.setSwitches(contaSwitches.getListSwitches());

				break;
			}

			case REMOVE: {
				data.setAction(new ContaRemoveAction());
				data.setSwitches(contaSwitches.getRemoveSwitches());

				break;
			}
			}

			break;
		}

		case LANCAMENTO: {
			LancamentoSwitches lancamentoSwitches = switches.getLancamentoSwitches();

			switch (lancamentoSwitches.getCommand()) {
			case ADD: {
				data.setAction(new LancamentoAddAction());
				data.setSwitches(lancamentoSwitches.getAddSwitches());

				break;
			}

			case ADD_FULL: {
				data.setAction(new LancamentoAddFullAction());
				data.setSwitches(lancamentoSwitches.getAddFullSwitches());

				break;
			}
			case BALANCE: {
				data.setAction(new LancamentoBalanceAction());
				data.setSwitches(lancamentoSwitches.getBalanceSwitches());

				break;
			}

			case LIST: {
				data.setAction(new LancamentoListAction());
				data.setSwitches(lancamentoSwitches.getListSwitches());

				break;
			}
			case REMOVE: {
				data.setAction(new LancamentoRemoveAction());
				data.setSwitches(lancamentoSwitches.getRemoveSwitches());

				break;
			}
			}

			break;
		}

		case MODELO: {
			ModeloSwitches modeloSwitches = switches.getModeloSwitches();

			switch (modeloSwitches.getCommand()) {
			case ADD: {
				data.setAction(new ModeloAddAction());
				data.setSwitches(modeloSwitches.getAddSwitches());

				break;
			}

			case CLONE: {
				data.setAction(new ModeloCloneAction());
				data.setSwitches(modeloSwitches.getCloneSwitches());

				break;
			}
			}
			break;
		}

		case LANCAMENTO_MODELO: {

			break;
		}

		case HELP: {

			break;
		}

		default: {
			System.out.println("Comando não implementado ainda...");
		}
		}

		Action action = data.getAction();
		Object switches = data.getSwitches();

		if ((action != null) && (switches != null)) {
			action.setSwitches(switches);
		}

		return action;
	}
}
