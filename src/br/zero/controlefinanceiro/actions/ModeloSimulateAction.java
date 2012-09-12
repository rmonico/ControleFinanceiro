package br.zero.controlefinanceiro.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.zero.controlefinanceiro.commandlineparser.ModeloSimulate;
import br.zero.controlefinanceiro.commandlineparser.ModeloSimulateSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.utils.Contabilizador;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.utils.LancamentoContabilizavel;
import br.zero.controlefinanceiro.utils.Packer;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class ModeloSimulateAction implements Action<ModeloSimulateSwitches, Object> {

	private class ModeloSimulateException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public ModeloSimulateException(String message) {
			super("modelo simulate: " + message);
		}

	}

	public class LancamentoSimulated extends LancamentoContabilizavel implements Comparable<LancamentoSimulated> {
		private String nomeLista;

		public String getNomeLista() {
			return nomeLista;
		}

		public void setNomeLista(String nomeLista) {
			this.nomeLista = nomeLista;
		}

		@Override
		public int compareTo(LancamentoSimulated otherInstance) {
			if (getData() == null) {
				return +1;
			} else if (otherInstance.getData() == null) {
				return -1;
			}

			int dataComparision = getData().compareTo(otherInstance.getData());

			if (dataComparision != 0) {
				return dataComparision;
			}

			if ((getN() != null) && (otherInstance.getN() != null)) {
				int nComparision = getN().compareTo(otherInstance.getN());

				if (nComparision != 0) {
					return nComparision;
				}
			}

			if (getNomeLista() == null) {
				return +1;
			} else if (otherInstance.getNomeLista() == null) {
				return -1;
			}

			int tipoComparision = getNomeLista().compareTo(otherInstance.getNomeLista());

			if (tipoComparision != 0) {
				return tipoComparision;
			}

			if (getId() == null) {
				return +1;
			} else if (otherInstance.getId() == null) {
				return -1;
			}

			return getId().compareTo(otherInstance.getId());
		}

	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Lançamentos");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Modelo", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getNomeLista");
		TextGridFormattedColumn.createFormattedColumn(grid, "Data", TextGridFormattedColumn.DATE_FORMATTER, TextGridColumnAlignment.CENTER, "getData");
		TextGridFormattedColumn.createFormattedColumn(grid, "N", TextGridFormattedColumn.INTEGER_FORMATTER, TextGridColumnAlignment.LEFT, "getN");
		TextGridFormattedColumn.createFormattedColumn(grid, "Origem", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.RIGHT, "getContaOrigem");
		TextGridFormattedColumn.createFormattedColumn(grid, "Saldo", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getSaldoOrigem");
		TextGridFormattedColumn.createFormattedColumn(grid, "Destino", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getContaDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Saldo", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getSaldoDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Valor", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getValor");
		TextGridFormattedColumn.createFormattedColumn(grid, "Observação", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getObservacao");

		return grid;
	}

	@Override
	public Object run(ModeloSimulateSwitches switches) throws ModeloSimulateException, TextGridException {
		checkParamValid(switches);

		Contabilizador contabilizador = new Contabilizador();

		List<LancamentoSimulated> lancamentoContabilizavelList = getLancamentoSimulatedList(contabilizador);

		List<LancamentoSimulated> lancamentoModeloContabilizavelList = new ArrayList<LancamentoSimulated>();

		for (ModeloSimulate ms : switches.getModelo()) {
			List<LancamentoSimulated> list = getLancamentoModeloSimulatedList(contabilizador, ms.getNomeModelo(), ms.getDataBase());

			lancamentoModeloContabilizavelList.addAll(list);
		}

		List<LancamentoSimulated> list = createFinalList(lancamentoContabilizavelList, lancamentoModeloContabilizavelList);

		contabilizador.setList(list);

		contabilizador.contabilizar();

		TextGrid grid = createGrid();

		grid.setValues(list);

		grid.show();

		Map<Conta, Double> saldos = contabilizador.getSaldosPorConta();

		for (Conta conta : saldos.keySet()) {
			System.out.println("===> " + conta.getNome() + ": " + saldos.get(conta));
		}
		
		return null;
	}

	private List<LancamentoSimulated> createFinalList(List<LancamentoSimulated> lancamentoContabilizavelList, List<LancamentoSimulated> lancamentoModeloContabilizavelList) {
		List<LancamentoSimulated> list = new ArrayList<LancamentoSimulated>();

		list.addAll(lancamentoContabilizavelList);
		list.addAll(lancamentoModeloContabilizavelList);

		Collections.sort(list);

		calcNs(list);

		return list;
	}

	private List<LancamentoSimulated> getLancamentoModeloSimulatedList(Contabilizador contabilizador, String nomeModelo, Calendar dataBase) {
		List<LancamentoModelo> lancamentoModeloList = getLancamentoModeloList(nomeModelo);

		List<LancamentoSimulated> lancamentoModeloContabilizavelList = packListLancamentoModelo(contabilizador, dataBase, lancamentoModeloList);

		return lancamentoModeloContabilizavelList;
	}

	private List<LancamentoSimulated> getLancamentoSimulatedList(Contabilizador contabilizador) {
		List<Lancamento> lancamentoList = getLancamentoList();

		List<LancamentoSimulated> lancamentoSimulatedList = packListLancamento(contabilizador, lancamentoList);

		return lancamentoSimulatedList;
	}

	private void calcNs(List<LancamentoSimulated> list) {
		Calendar data = null;
		int n = 1;

		for (LancamentoSimulated lancamento : list) {
			if (!lancamento.getData().equals(data)) {
				n = 1;
				data = lancamento.getData();
			}

			lancamento.setN(n++);
		}
	}

	private List<LancamentoSimulated> packListLancamentoModelo(Contabilizador contabilizador, final Calendar dataBase, List<LancamentoModelo> lancamentoModeloList) {
		Packer<LancamentoSimulated, LancamentoModelo> packer = new Packer<LancamentoSimulated, LancamentoModelo>() {
			@Override
			public LancamentoSimulated pack(LancamentoModelo lm) {
				LancamentoSimulated lfl = new LancamentoSimulated();
				lfl.setNomeLista(lm.getModelo().getNome());
				lfl.setLancamentoModeloBase(lm, dataBase);

				return lfl;
			}
		};

		List<LancamentoSimulated> lancamentoSimulatedList = contabilizador.packageList(lancamentoModeloList, packer);
		return lancamentoSimulatedList;
	}

	private List<LancamentoSimulated> packListLancamento(Contabilizador contabilizador, List<Lancamento> lancamentoList) {
		// TODO Extrair a montagem das listas para métodos separados
		Packer<LancamentoSimulated, Lancamento> lancamentoToLancamentoForListPacker = new Packer<ModeloSimulateAction.LancamentoSimulated, Lancamento>() {

			@Override
			public LancamentoSimulated pack(Lancamento lancamento) {
				LancamentoSimulated lfl = new LancamentoSimulated();
				lfl.setNomeLista("[Realizado]");
				lfl.setLancamentoBase(lancamento);

				return lfl;
			}
		};

		List<LancamentoSimulated> lancamentoSimulatedList = contabilizador.packageList(lancamentoList, lancamentoToLancamentoForListPacker);

		return lancamentoSimulatedList;
	}

	private List<LancamentoModelo> getLancamentoModeloList(String nomeModelo) {
		LancamentoModeloDAO dao = new LancamentoModeloDAO();

		return dao.listarPorModelo(nomeModelo);
	}

	private List<Lancamento> getLancamentoList() {
		LancamentoDAO dao = new LancamentoDAO();

		return dao.listarTodos();
	}

	private void checkParamValid(ModeloSimulateSwitches switches) throws ModeloSimulateException {
		if (switches.getModelo().size() == 0) {
			throw new ModeloSimulateException("Pelo menos um modelo deve ser informado.");
		}

		for (ModeloSimulate ms : switches.getModelo()) {
			if (ms.getNomeModelo() == null) {
				throw new ModeloSimulateException("Nome do Modelo deve ser informado.");
			}

			if (ms.getDataBase() == null) {
				throw new ModeloSimulateException("Data base deve ser informada.");
			}
		}
	}

}
