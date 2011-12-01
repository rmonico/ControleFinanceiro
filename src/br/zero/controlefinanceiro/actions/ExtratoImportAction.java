package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.ExtratoImportSwitches;
import br.zero.tinycontroller.Action;

public class ExtratoImportAction implements Action {

	private ExtratoImportSwitches switches;

	public class ExtratoImportException extends Exception {

		public ExtratoImportException(String message) {
			super(message);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = -3059453211748572183L;

	}

	@Override
	public void run(Object param) throws Exception {
		switches = checkParamValid(param);
	}

	private ExtratoImportSwitches checkParamValid(Object param) throws ExtratoImportException {
		if (!(param instanceof ExtratoImportSwitches)) {
			throw new ExtratoImportException("Parâmetro deve ser um ExtratoImportSwitches.");
		}
		
		ExtratoImportSwitches switches = (ExtratoImportSwitches) param;
		
		if (switches.getNomeConta() ==  null) {
			throw new ExtratoImportException("Nome da conta deve ser informada.");
		}
		
		if (switches.getNomeArquivo() == null) {
			throw new ExtratoImportException("Nome do arquivo deve ser informado.");
		}
		
		return switches;
	}
}
