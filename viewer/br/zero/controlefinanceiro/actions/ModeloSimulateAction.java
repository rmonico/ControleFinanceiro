package br.zero.controlefinanceiro.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.zero.controlefinanceiro.ControleFinanceiroException;
import br.zero.controlefinanceiro.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.commandlineparser.ModeloSimulateSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.controlefinanceiro.model.modelo.ModeloDAO;
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
		PREVISTO(new StringBuilder("P")), REALIZADO(new StringBuilder("R"));

		private StringBuilder sb;

		private TipoLancamento(StringBuilder sb) {
			this.sb = sb;
		}

		public StringBuilder getTipoLancamentoStr() {
			return sb;
		}
	}

	public class LancamentoForList implements Comparable<LancamentoForList> {
		private Integer id;
		private TipoLancamento tipo;
		private Calendar data;
		private Integer n;
		private Conta contaOrigem;
		private Double saldoOrigem;
		private Conta contaDestino;
		private Double saldoDestino;
		private Double valor;
		private String observacao;

		public LancamentoForList(Lancamento lancamento) {
			super();

			setId(lancamento.getId());
			setTipo(TipoLancamento.REALIZADO);
			setData(lancamento.getData());
			setN(lancamento.getN());
			setContaOrigem(lancamento.getContaOrigem());
			setContaDestino(lancamento.getContaDestino());
			setValor(lancamento.getValor());
			setObservacao(lancamento.getObservacao());
		}

		public LancamentoForList(Calendar dataBase, LancamentoModelo lancamentoModelo) {
			super();

			setId(lancamentoModelo.getId());
			setTipo(TipoLancamento.PREVISTO);

			Calendar dataLancamento = (Calendar) dataBase.clone();
			dataLancamento.add(Calendar.DAY_OF_MONTH, lancamentoModelo.getDiaVencimento() - 1);

			setData(dataLancamento);
			setContaOrigem(lancamentoModelo.getContaOrigem());
			setContaDestino(lancamentoModelo.getContaDestino());
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public TipoLancamento getTipo() {
			return tipo;
		}

		public void setTipo(TipoLancamento tipo) {
			this.tipo = tipo;
		}

		public Calendar getData() {
			return data;
		}

		public void setData(Calendar data) {
			this.data = data;
		}

		public Integer getN() {
			return n;
		}

		public void setN(Integer n) {
			this.n = n;
		}

		public Conta getContaOrigem() {
			return contaOrigem;
		}

		public void setContaOrigem(Conta contaOrigem) {
			this.contaOrigem = contaOrigem;
		}

		public Double getSaldoOrigem() {
			return saldoOrigem;
		}

		public void setSaldoOrigem(Double saldoOrigem) {
			this.saldoOrigem = saldoOrigem;
		}

		public Conta getContaDestino() {
			return contaDestino;
		}

		public void setContaDestino(Conta contaDestino) {
			this.contaDestino = contaDestino;
		}

		public Double getSaldoDestino() {
			return saldoDestino;
		}

		public void setSaldoDestino(Double saldoDestino) {
			this.saldoDestino = saldoDestino;
		}

		public Double getValor() {
			return valor;
		}

		public void setValor(Double valor) {
			this.valor = valor;
		}

		public String getObservacao() {
			return observacao;
		}

		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

		@Override
		public int compareTo(LancamentoForList otherInstance) {
			if (otherInstance == null) {
				return -1;
			}

			if (data == null) {
				return +1;
			} else if (otherInstance.getData() == null) {
				return -1;
			}

			int dataComparision = data.compareTo(otherInstance.getData());

			if (dataComparision != 0) {
				return dataComparision;
			}

			if (n == null) {
				return +1;
			} else if (otherInstance.getN() == null) {
				return -1;
			}

			int nComparision = n.compareTo(otherInstance.getN());

			if (nComparision != 0) {
				return nComparision;
			}

			if (tipo == null) {
				return +1;
			} else if (otherInstance.getTipo() == null) {
				return -1;
			}

			int tipoComparision = tipo.compareTo(otherInstance.getTipo());

			if (tipoComparision != 0) {
				return tipoComparision;
			}

			if (id == null) {
				return +1;
			} else if (otherInstance.getId() == null) {
				return -1;
			}

			return id.compareTo(otherInstance.getId());
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

		List<LancamentoForList> list = new ArrayList<LancamentoForList>();

		packageLancamentos(list, lancamentoList);

		List<LancamentoModelo> lancamentoModeloList = getLancamentoModeloList(switches.getNomeModelo());

		packageLancamentosModelo(list, switches.getDataBase(), lancamentoModeloList);

		Collections.sort(list);

		// Calendar data = null;
		// int n;
		// for (LancamentoForList lancamento : list) {
		// if (!lancamento.getData().equals(data)) {
		// n=0;
		// }
		//
		//
		// }

		TextGrid grid = createGrid();

		grid.setValues(list);

		grid.show();
	}

	private void packageLancamentosModelo(List<LancamentoForList> list, Calendar dataBase, List<LancamentoModelo> lancamentoModeloList) {
		for (LancamentoModelo lancamentoModelo : lancamentoModeloList) {
			LancamentoForList lancamentoForList = new LancamentoForList(dataBase, lancamentoModelo);

			list.add(lancamentoForList);
		}
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

	private void packageLancamentos(List<LancamentoForList> list, List<Lancamento> lancamentoList) {
		for (Lancamento lancamento : lancamentoList) {
			LancamentoForList lancamentoForList = new LancamentoForList(lancamento);

			list.add(lancamentoForList);
		}
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
