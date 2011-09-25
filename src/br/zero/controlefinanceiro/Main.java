package br.zero.controlefinanceiro;


import br.zero.commandlineparser.CommandLineParser;
import br.zero.commandlineparser.CommandLineParserException;
import br.zero.commandlineparser.parsers.EnumParser;
import br.zero.controlefinanceiro.commandlineparser.Command;
import br.zero.controlefinanceiro.commandlineparser.Switches;
import br.zero.controlefinanceiro.model.modelo.Lancamento;
import br.zero.controlefinanceiro.model.modelo.LancamentoDAO;

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
//		System.out.println("Modelos de Lançamento:\n\n");
		LancamentoDAO dao = new LancamentoDAO();

		for (Lancamento lancamento : dao.listarTodos()) {
			StringBuilder sb = new StringBuilder();

			sb.append("#" + lancamento.getId() + " [");
			sb.append(lancamento.getContaOrigem().getNome() + " -- " + lancamento.getContaDestino().getNome() + "; ");
			sb.append("$" + lancamento.getValor() + "; ");
			sb.append("vencto " + lancamento.getDiavencimento() + "; ");
			sb.append(lancamento.getFormaPagamento().getNome());
			if (lancamento.getObservacao() != null) {
				sb.append("; (" + lancamento.getObservacao() + ")");
			}
			
			sb.append("]");
			
			System.out.println(sb.toString());
		}
		
		System.out.println("\n");
		
		System.out.println("-- Fim");

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
