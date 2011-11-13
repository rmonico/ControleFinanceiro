package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class CommandLineSwitches {
	private MainCommand mainCommand;
	private NewTransactionCommand newTransaction;
	private ListTransactionsCommand listTransactions;
	private ShowHelpCommand showHelp;

	public MainCommand getMainCommand() {
		return mainCommand;
	}

	@CommandLineSwitch(
			parser = "MainCommandParser.parseComplexEnum", 
			complexParser = true, 
			index = 1, 
			subCommandLineProperties = {
					@SubCommandLine(value="NEW", subCommandLineClass=NewTransactionCommand.class, propertyName="setNewTransactionCommand"),
					@SubCommandLine(value="LIST", subCommandLineClass=ListTransactionsCommand.class, propertyName="setListTransactionsCommand"),
					@SubCommandLine(value="HELP", subCommandLineClass=ShowHelpCommand.class, propertyName="setShowHelpCommand")
			})
	public void setMainCommand(MainCommand value) {
		mainCommand = value;
	}

	public void setNewTransactionCommand(NewTransactionCommand value) {
		newTransaction = value;
	}
	
	public NewTransactionCommand getNewTransactionCommand() {
		return newTransaction;
	}

	public void setListTransactionsCommand(ListTransactionsCommand value) {
		listTransactions = value;
	}
	
	public ListTransactionsCommand getListTransactionsCommand() {
		return listTransactions;
	}
	
	public void setShowHelpCommand(ShowHelpCommand value) {
		showHelp = value;
	}

	public ShowHelpCommand getShowHelpCommand() {
		return showHelp;
	}
	
}
