package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.ModeloAddSwitches;
import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.controlefinanceiro.model.modelo.ModeloDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.tinycontroller.Action;

public class ModeloAddAction implements Action {

	private class ModeloAddException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public ModeloAddException(String message) {
			super("modelo add: " + message);
		}
		
	}
	
	@Override
	public void run(Object param) throws ModeloAddException {
		
		ModeloAddSwitches switches = checkParamValid(param);

		ModeloDAO modeloDAO = new ModeloDAO();
		
		Modelo modelo = new Modelo();
		
		modelo.setNome(switches.getNome());
		
		modelo.setObservacao(switches.getObservacao());
		
		modeloDAO.inserir(modelo);
		
		
		System.out.println();
		
		System.out.println("-- Modelo adicionado --");
		
		System.out.println(modelo);
	}

	private ModeloAddSwitches checkParamValid(Object param) throws ModeloAddException {
		if (!(param instanceof ModeloAddSwitches)) {
			throw new ModeloAddException("Parametro deve ser um ModeloAddSwitches.");
		}
		
		ModeloAddSwitches switches = (ModeloAddSwitches) param;
		
		if (switches.getNome() ==  null) {
			throw new ModeloAddException("Nome do modelo deve ser informado");
		}
		
		return switches;
	}

}
