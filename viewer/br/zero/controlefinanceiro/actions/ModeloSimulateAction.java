package br.zero.controlefinanceiro.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.zero.controlefinanceiro.commandlineparser.ModeloSimulateSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.controlefinanceiro.model.modelo.ModeloDAO;
import br.zero.controlefinanceiro.utils.Contabilizador;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.utils.LancamentoContabilizavel;
import br.zero.controlefinanceiro.utils.Packager;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.textgrid.TextGridFormatter;
import br.zero.tinycontroller.Action;

public class ModeloSimulateAction implements Action {

	private class ModeloSimulateException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public ModeloSimulateException(String message) {
			super("modelo simulate: " + message);
		}

	}

	private enum TipoLancamento {
		REALIZADO(new StringBuilder("R")), PREVISTO(new StringBuilder("P"));

		private StringBuilder sb;

		private TipoLancamento(StringBuilder sb) {
			this.sb = sb;
		}

		public StringBuilder getTipoLancamentoStr() {
			return sb;
		}
	}

	public class LancamentoSimulated extends LancamentoContabilizavel implements Comparable<LancamentoSimulated> {
		private TipoLancamento tipo;

		public TipoLancamento getTipo() {
			return tipo;
		}

		public void setTipo(TipoLancamento tipo) {
			this.tipo = tipo;
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

			if (getTipo() == null) {
				return +1;
			} else if (otherInstance.getTipo() == null) {
				return -1;
			}

			int tipoComparision = getTipo().compareTo(otherInstance.getTipo());

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

		TextGridFormatter tipoLancamentoFormatter = new TextGridFormatter() {
			@Override
			public StringBuilder parse(Object cellValue) throws TextGridException {
				if (cellValue == null) {
					return new StringBuilder("[null]");
				}

				if (!(cellValue instanceof TipoLancamento)) {
					throw new TextGridException("tipoLancamentoFormatter: Must be used only with br.zero.controlefinanceiro.ModeloSimulateAction$TipoLancamento fields.");
				}

				TipoLancamento tipoLancamento = (TipoLancamento) cellValue;

				return tipoLancamento.getTipoLancamentoStr();
			}
		};

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Tipo", tipoLancamentoFormatter, TextGridColumnAlignment.LEFT, "getTipo");
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
	public void run(Object param) throws ModeloSimulateException, TextGridException {
		ModeloSimulateSwitches switches = checkParamValid(param);

		List<Lancamento> lancamentoList = getLancamentoList();

		Contabilizador contabilizador = new Contabilizador();

		List<LancamentoSimulated> lancamentoContabilizavelList = packListLancamento(contabilizador, lancamentoList);
		
		List<LancamentoModelo> lancamentoModeloList = getLancamentoModeloList(switches.getNomeModelo());

		List<LancamentoSimulated> lancamentoModeloContabilizavelList = packListLancamentoModelo(contabilizador, switches.getDataBase(), lancamentoModeloList);

		List<LancamentoSimulated> list = new ArrayList<LancamentoSimulated>();

		list.addAll(lancamentoContabilizavelList);
		list.addAll(lancamentoModeloContabilizavelList);

		Collections.sort(list);

		Calendar data = null;
		int n = 1;

		// TODO Criar campo na tabela Conta chamado contabilizavel (boolean,
		// indica que a conta pode acumular valores, não é como a conta de
		// almoço por exemplo).
		for (LancamentoSimulated lancamento : list) {
			if (!lancamento.getData().equals(data)) {
				n = 1;
				data = lancamento.getData();
			}

			lancamento.setN(n++);
		}

		contabilizador.setList(list);

		contabilizador.contabilizar();

		TextGrid grid = createGrid();

		grid.setValues(list);

		grid.show();

		Map<Conta, Double> saldos = contabilizador.getSaldosPorConta();

		for (Conta conta : saldos.keySet()) {
			System.out.println("===> " + conta.getNome() + ": " + saldos.get(conta));
		}
	}

	private List<LancamentoSimulated> packListLancamentoModelo(Contabilizador contabilizador, final Calendar dataBase, List<LancamentoModelo> lancamentoModeloList) {
		Packager<LancamentoSimulated, LancamentoModelo> packager = new Packager<LancamentoSimulated, LancamentoModelo>() {
			@Override
			public LancamentoSimulated pack(LancamentoModelo lm) {
				LancamentoSimulated lfl = new LancamentoSimulated();
				lfl.setTipo(TipoLancamento.PREVISTO);
				lfl.setLancamentoModeloBase(lm, dataBase);

				return lfl;
			}
		};

		List<LancamentoSimulated> lancamentoSimulatedList = contabilizador.packageList(lancamentoModeloList, packager);
		return lancamentoSimulatedList;
	}

	private List<LancamentoSimulated> packListLancamento(Contabilizador contabilizador, List<Lancamento> lancamentoList) {
		// TODO Extrair a montagem das listas para métodos separados
		Packager<LancamentoSimulated, Lancamento> lancamentoToLancamentoForListPackager = new Packager<ModeloSimulateAction.LancamentoSimulated, Lancamento>() {

			@Override
			public LancamentoSimulated pack(Lancamento lancamento) {
				LancamentoSimulated lfl = new LancamentoSimulated();
				lfl.setTipo(TipoLancamento.REALIZADO);
				lfl.setLancamentoBase(lancamento);

				return lfl;
			}
		};

		List<LancamentoSimulated> lancamentoSimulatedList = contabilizador.packageList(lancamentoList, lancamentoToLancamentoForListPackager);

		return lancamentoSimulatedList;
	}

	private List<LancamentoModelo> getLancamentoModeloList(String nomeModelo) throws ModeloSimulateException {
		ModeloDAO modeloDAO = new ModeloDAO();

		Modelo modelo = modeloDAO.getByNome(nomeModelo);

		if (modelo == null) {
			throw new ModeloSimulateException("Modelo não encontrado: \"" + nomeModelo + "\".");
		}

		LancamentoModeloDAO dao = new LancamentoModeloDAO();

		return dao.listarPorModelo(modelo);
	}

	private List<Lancamento> getLancamentoList() {
		LancamentoDAO dao = new LancamentoDAO();

		return dao.listarTodos();
	}

	private ModeloSimulateSwitches checkParamValid(Object param) throws ModeloSimulateException {
		if (!(param instanceof ModeloSimulateSwitches)) {
			throw new ModeloSimulateException("Parametro deve ser um ModeloSimulateSwitches.");
		}

		ModeloSimulateSwitches switches = (ModeloSimulateSwitches) param;

		if (switches.getNomeModelo() == null) {
			throw new ModeloSimulateException("Nome do Modelo deve ser informado.");
		}

		if (switches.getDataBase() == null) {
			throw new ModeloSimulateException("Data base deve ser informada.");
		}

		return switches;
	}

}
