package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.Command;
import br.zero.controlefinanceiro.commandlineparser.HelpSwitches;
import br.zero.tinycontroller.Action;
import br.zero.tinycontroller.TinyControllerException;

public class HelpAction implements Action {

	@Override
	public void run(Object param) throws TinyControllerException {
		HelpSwitches switches = (HelpSwitches) param;
		
		Command entity = switches.getEntity();
		
		out("Help: " + entity.toString());
	}

	private void out(String value) {
		System.out.println(value);
	}

}
