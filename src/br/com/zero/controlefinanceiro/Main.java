package br.com.zero.controlefinanceiro;

import org.zero.commandlineparser.CommandLineParser;
import org.zero.commandlineparser.CommandLineParserException;
import org.zero.commandlineparser.parsers.EnumParser;

import br.com.zero.controlefinanceiro.commandlineparser.Command;
import br.com.zero.controlefinanceiro.commandlineparser.Switches;
import br.com.zero.controlefinanceiro.modelo.Lancamento;
import br.com.zero.controlefinanceiro.modelo.LancamentoDAO;

public class Main {

	private String[] args;
	private Switches switches;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Main main = new Main();

		main.setArgs(args);

		main.run();
	}

	private void setArgs(String[] args) {
		this.args = args;
	}

	private void run() throws Exception {
		parseCommandLine();

		switch (switches.getCommand()) {
		case LIST: {
			listarLancamentos();
			
			break;
		}
		default: {
			System.out.println("Comando não implementado ainda...");
		}
		}
	}

	private void listarLancamentos() {
		LancamentoDAO dao = new LancamentoDAO();
		
		for (Lancamento lancamento : dao.listarTodos()) {
			System.out.println(lancamento);
		}
		
	}

	private void parseCommandLine() throws CommandLineParserException {
		CommandLineParser parser = new CommandLineParser();

		parser.setCommandLine(args);

		switches = new Switches();

		parser.setSwitchesObject(switches);
		
		parser.addParser("EnumParser", new EnumParser(Command.class));

		parser.parse();
	}

}
