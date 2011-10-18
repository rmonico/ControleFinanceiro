package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class CommandLineSwitches {
	private MainCommand mainCommand;

	public MainCommand getCommand() {
		return mainCommand;
	}

	@CommandLineSwitch(parser = "EnumParser.parseEnum", index = 1)
	public void setMainCommand(MainCommand value) {
		mainCommand = value;
	}
	
	public NewTransactionSubCommandLine getNewTransaction() {
		// TODO Auto-generated method stub
		return null;
	}
}
