package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.commandlineparser.ModeloCloneSwitches;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.controlefinanceiro.model.modelo.ModeloDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.tinycontroller.Action;

public class ModeloCloneAction implements Action<ModeloCloneSwitches, Object> {

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
	public Object run(ModeloCloneSwitches switches) throws ModeloCloneException {
		checkParamValid(switches);
		
		ModeloDAO dao = new ModeloDAO();
		
		Modelo modeloBase = dao.getByNome(switches.getModeloBase());
		
		if (modeloBase == null) {
			throw new ModeloCloneException("Modelo base não encontrado: \"" + switches.getModeloBase() + "\".");
		}
		
		Modelo modeloClonado = clonarModelo(modeloBase, switches);

		
		System.out.println();
		
		System.out.println("-- Modelo Clonado --");
		
		System.out.println(modeloBase + " -> " + modeloClonado);
		
		return null;
	}

	private Modelo clonarModelo(Modelo modeloBase, ModeloCloneSwitches switches) {
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
		
		
		clonarLancamentos(modeloBase.getNome(), modeloClonado);
		
		return modeloClonado;
	}

	private void clonarLancamentos(String modeloBase, Modelo modeloClonado) {
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

	private void checkParamValid(ModeloCloneSwitches switches) throws ModeloCloneException {
		if (switches.getModeloBase() ==  null) {
			throw new ModeloCloneException("Modelo base deve ser informado.");
		}
		
		if (switches.getModeloNovo() == null) {
			throw new ModeloCloneException("Modelo novo deve ser informado.");
		}
	}


}
