package br.zero.controlefinanceiro.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.zero.controlefinanceiro.commandlineparser.ModeloAnalyseSwitches;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;
import br.zero.types.DateRange;

public class ModeloAnalyseAction implements Action {

	private List<Lancamento> unrelatedLancamentoList;
	private Map<LancamentoModelo, List<Lancamento>> lancamentoModeloMap;

	public class ModeloAnalyseException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8408201496696477110L;

		public ModeloAnalyseException(String message) {
			super(message);
		}

	}

	@Override
	public void run(Object param) throws ModeloAnalyseException, TextGridException {
		ModeloAnalyseSwitches switches = checkParamValid(param);
		
		mountLists(switches);
		
		showUnrelatedLancamentos();
		
		showProposedChanges();
	}

	private void showProposedChanges() {
		Set<LancamentoModelo> keySet = lancamentoModeloMap.keySet();
		
		for (LancamentoModelo lm : keySet) {
			List<Lancamento> ll = lancamentoModeloMap.get(lm);
			
			System.out.println();
			
			int llSize = ll.size();
			
			if (llSize == 0) {
				System.out.println(lm.toString() + " ==> Nenhum lançamento correspondente!");
			} else if (llSize == 1) {
				System.out.println(lm.toString() + " ==> " + ll.get(0).toString());
			} else {
				System.out.println(lm.toString() + " ==> <Múltiplos lançamentos encontrados>");
				
				for (Lancamento l : ll) {
					System.out.println(l.toString());
				}
			}
		}
		
		int keySetSize = keySet.size();
		System.out.println("(" + keySetSize + " line" + (keySetSize == 1 ? "" : "s") + ")");
		System.out.println("--");
	}

	private void showUnrelatedLancamentos() throws TextGridException {
		TextGrid grid = createGridForUnrelatedLancamentos();
		
		Collections.sort(unrelatedLancamentoList);

		grid.setValues(unrelatedLancamentoList);
		
		grid.show();
	}

	private TextGrid createGridForUnrelatedLancamentos() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lançamentos não previstos:");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Data", TextGridFormattedColumn.DATE_FORMATTER, TextGridColumnAlignment.CENTER, "getData");
		TextGridFormattedColumn.createFormattedColumn(grid, "N", TextGridFormattedColumn.INTEGER_FORMATTER, TextGridColumnAlignment.LEFT, "getN");
		TextGridFormattedColumn.createFormattedColumn(grid, "Origem", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.RIGHT, "getContaOrigem");
		TextGridFormattedColumn.createFormattedColumn(grid, "Destino", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getContaDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Valor", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getValor");
		TextGridFormattedColumn.createFormattedColumn(grid, "Observação", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getObservacao");

		return grid;
	}

	private void mountLists(ModeloAnalyseSwitches switches) {
		DateRange lancamentoRange = getLancamentoRange(switches);
		
		List<Lancamento> lancamentoList = getLancamentoMap(lancamentoRange);
		
		unrelatedLancamentoList = new ArrayList<Lancamento>();
		unrelatedLancamentoList.addAll(lancamentoList);
		
		lancamentoModeloMap = getLancamentoModeloMap(switches.getNomeModelo());

		boolean preview = !switches.getRealizeFlag();
		
		LancamentoDAO ld = preview ? null : new LancamentoDAO();
		
		for (LancamentoModelo lm : lancamentoModeloMap.keySet()) {
			List<Lancamento> ll = lancamentoModeloMap.get(lm);
			
			for (Lancamento l : lancamentoList) {
				if (isRelated(lm, l)) {
					if (!preview) {
						l.setLancamentoModelo(lm);
						
						ld.alterar(l);
					}
					
					ll.add(l);
					unrelatedLancamentoList.remove(l);
				}
			}
		}
		
		List<LancamentoModelo> noLancamentoFound = new ArrayList<LancamentoModelo>();
		
		for (LancamentoModelo lm : lancamentoModeloMap.keySet()) {
			List<Lancamento> ll = lancamentoModeloMap.get(lm);
			
			if (ll.size()== 0) {
				noLancamentoFound.add(lm);
			}
		}
	}

	private DateRange getLancamentoRange(ModeloAnalyseSwitches switches) {
		if (switches.getLancamentoRange() == null) {
			 DateRange dr = new DateRange();
			 
			 dr.setStart(switches.getDataBase());
			 
			 Calendar endDate = (Calendar) switches.getDataBase().clone();
			 endDate.add(Calendar.MONTH, 1);
			 dr.setEnd(endDate);
			 
			 return dr;
		} else {
			return switches.getLancamentoRange();
		}
	}

	private boolean isRelated(LancamentoModelo lm, Lancamento l) {
		return lm.getContaOrigem().equals(l.getContaOrigem()) && lm.getContaDestino().equals(l.getContaDestino());
	}

	private Map<LancamentoModelo, List<Lancamento>> getLancamentoModeloMap(String nomeModelo) {
		LancamentoModeloDAO dao = new LancamentoModeloDAO();
		
		List<LancamentoModelo> listaLancamentoModelo = dao.listarPorModelo(nomeModelo);
		
		
		Map<LancamentoModelo, List<Lancamento>> lancamentoModeloMap = new HashMap<LancamentoModelo, List<Lancamento>>();
		
		for (LancamentoModelo lancamentoModelo : listaLancamentoModelo) {
			lancamentoModeloMap.put(lancamentoModelo, new ArrayList<Lancamento>());
		}
		
		return lancamentoModeloMap;
	}

	private List<Lancamento> getLancamentoMap(DateRange lancamentoRange) {
		LancamentoDAO dao = new LancamentoDAO();
		
		return dao.listarSemModeloPorData(lancamentoRange.getStart(), lancamentoRange.getEnd());
	}

	private ModeloAnalyseSwitches checkParamValid(Object param) throws ModeloAnalyseException {
		if (!(param instanceof ModeloAnalyseSwitches)) {
			throw new ModeloAnalyseException("Parametro deve ser um ModeloAnalyseSwitches.");
		}
		
		ModeloAnalyseSwitches switches = (ModeloAnalyseSwitches) param;
		
		if (switches.getNomeModelo() ==  null) {
			throw new ModeloAnalyseException("Nome do modelo deve ser informado.");
		}
		
		if (switches.getDataBase() == null) {
			throw new ModeloAnalyseException("Data base deve ser informada.");
		}
		
		return switches;
	}

}
