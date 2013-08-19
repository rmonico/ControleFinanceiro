package br.zero.controlefinanceiro.actions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLineParser;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoParsers;
import br.zero.controlefinanceiro.commandlineparser.ExtratoImportSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.extrato.Arquivo;
import br.zero.controlefinanceiro.model.extrato.ArquivoDAO;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamentoDAO;
import br.zero.controlefinanceiro.utils.ExtratoLineParserException;
import br.zero.tinycontroller.Action;

public class ExtratoImportAction implements Action<ExtratoImportSwitches, Object> {

	public class ExtratoImportException extends Exception {

		public ExtratoImportException(String message) {
			super(message);
		}

		public ExtratoImportException(Exception e) {
			super(e);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = -3059453211748572183L;

	}

	@Override
	public Object run(ExtratoImportSwitches switches) throws Exception {
		checkParamValid(switches);

		Conta conta = getConta(switches.getNomeConta());

		BufferedReader file = getFile(switches.getNomeArquivo());

		try {
			doImport(file, conta);

		} finally {
			file.close();
		}
		
		return null;
	}

	private void doImport(BufferedReader file, Conta conta) throws IOException, ExtratoImportException {
		String line;
		
		ExtratoLineParser ep = ExtratoParsers.getParser(conta);
		
		if (ep == null) {
			throw new ExtratoImportException("Conta \"" + conta.getNome() + "\" não possui um parser de extrato registrado.");
		}
		
		Arquivo arquivo = new Arquivo();

		List<ExtratoLancamento> lancamentoList = new ArrayList<ExtratoLancamento>();
		
		StringBuilder conteudo = new StringBuilder();
		
		while ((line = file.readLine()) != null) {
			conteudo.append(line + "\n");
			
			try {
				ep.parse(line);
			} catch (ExtratoLineParserException e) {
				System.out.println("[ FAIL ] \"" + line + "\" ==> " + e.getMessage());
			}
			
			ExtratoLancamento lancamento = new ExtratoLancamento();

			lancamento.setBanco(conta);
			lancamento.setOriginal(line);
			lancamento.setArquivo(arquivo);

			lancamentoList.add(lancamento);
			
			
			System.out.println("[  OK  ] \"" + line + "\"");
		}

		arquivo.setConteudo(conteudo.toString());
		
		persistEverything(arquivo, lancamentoList);
	}

	private void persistEverything(Arquivo arquivo, List<ExtratoLancamento> lancamentoList) {
		ArquivoDAO arquivoDAO = new ArquivoDAO();
		
		arquivoDAO.inserir(arquivo);

		
		ExtratoLancamentoDAO lancamentoDAO = new ExtratoLancamentoDAO();

		for (ExtratoLancamento el : lancamentoList) {
			lancamentoDAO.inserir(el);
		}
	}

	private BufferedReader getFile(String nomeArquivo) throws FileNotFoundException {
		return new BufferedReader(new FileReader(nomeArquivo));
	}

	private Conta getConta(String nomeConta) throws ExtratoImportException {
		ContaDAO dao = new ContaDAO();

		Conta conta = dao.getByNome(nomeConta);

		if (conta == null) {
			throw new ExtratoImportException("Conta não encontrada: \"" + nomeConta + "\".");
		}

		return conta;
	}

	private void checkParamValid(ExtratoImportSwitches switches) throws ExtratoImportException {
		if (switches.getNomeConta() == null) {
			throw new ExtratoImportException("Nome da conta deve ser informada.");
		}

		if (switches.getNomeArquivo() == null) {
			throw new ExtratoImportException("Nome do arquivo deve ser informado.");
		}
	}
}
