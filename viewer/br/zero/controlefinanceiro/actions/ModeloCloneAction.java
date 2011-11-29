package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.commandlineparser.ModeloCloneSwitches;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.controlefinanceiro.model.modelo.ModeloDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.tinycontroller.Action;

public class ModeloCloneAction implements Action {

	private ModeloCloneSwitches switches;

	private class ModeloCloneException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public ModeloCloneException(String message) {
			super("modelo clone: " + message);
		}
		
	}
	
	@Override
	public void run(Object param) throws ModeloCloneException {
		
		switches = checkParamValid(param);
		
		ModeloDAO dao = new ModeloDAO();
		
		Modelo modeloBase = dao.getByNome(switches.getModeloBase());
		
		if (modeloBase == null) {
			throw new ModeloCloneException("Modelo base não encontrado: \"" + switches.getModeloBase() + "\".");
		}
		
		Modelo modeloClonado = clonarModelo(modeloBase);

		
		System.out.println();
		
		System.out.println("-- Modelo Clonado --");
		
		System.out.println(modeloBase + " -> " + modeloClonado);
	}

	private Modelo clonarModelo(Modelo modeloBase) {
		Modelo modeloClonado = new Modelo();
		
		modeloClonado.setNome(switches.getModeloNovo());
		
		String modeloClonadoObservacao;
		
		if (switches.getModeloNovoObservacao() != null) {
			modeloClonadoObservacao = switches.getModeloNovoObservacao();
		} else {
			modeloClonadoObservacao = modeloBase.getObservacao();
		}
		
		modeloClonado.setObservacao(modeloClonadoObservacao);
		
		ModeloDAO dao = new ModeloDAO();
		
		dao.inserir(modeloClonado);
		
		
		clonarLancamentos(modeloBase, modeloClonado);
		
		return modeloClonado;
	}

	private void clonarLancamentos(Modelo modeloBase, Modelo modeloClonado) {
		LancamentoModeloDAO dao = new LancamentoModeloDAO();
		
		List<LancamentoModelo> modeloBaseLancamentoList = dao.listarPorModelo(modeloBase);
		
		for (LancamentoModelo lancamentoModelo : modeloBaseLancamentoList) {
			LancamentoModelo lancamentoClonado;
			
			try {
				lancamentoClonado = lancamentoModelo.clone();
			} catch (CloneNotSupportedException e) {
				assert false : "Clonando lançamentos do modelo base" ;
				return;
			}
			
			lancamentoClonado.setId(null);
			lancamentoClonado.setModelo(modeloClonado);
			
			dao.inserir(lancamentoClonado);
		}
	}

	private ModeloCloneSwitches checkParamValid(Object param) throws ModeloCloneException {
		if (!(param instanceof ModeloCloneSwitches)) {
			throw new ModeloCloneException("Parametro deve ser um ModeloCloneSwitches.");
		}
		
		ModeloCloneSwitches switches = (ModeloCloneSwitches) param;
		
		if (switches.getModeloBase() ==  null) {
			throw new ModeloCloneException("Modelo base deve ser informado.");
		}
		
		if (switches.getModeloNovo() == null) {
			throw new ModeloCloneException("Modelo novo deve ser informado.");
		}
		
		return switches;
	}


}
