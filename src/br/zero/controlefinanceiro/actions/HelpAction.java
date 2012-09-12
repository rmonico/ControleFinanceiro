package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.Command;
import br.zero.controlefinanceiro.commandlineparser.HelpSwitches;
import br.zero.tinycontroller.Action;
import br.zero.tinycontroller.TinyControllerException;

public class HelpAction implements Action<HelpSwitches, Object> {

	@Override
	public Object run(HelpSwitches switches) throws TinyControllerException {
		Command entity = switches.getEntity();
		
		out("Help: " + entity.toString());
		
		return null;
	}

	private void out(String value) {
		System.out.println(value);
	}

}
