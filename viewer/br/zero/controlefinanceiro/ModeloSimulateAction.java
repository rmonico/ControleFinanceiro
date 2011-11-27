package br.zero.controlefinanceiro;

import java.util.Calendar;

import br.zero.controlefinanceiro.commandlineparser.ModeloSimulateSwitches;
import br.zero.controlefinanceiro.model.Conta;
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

	private class LancamentoForList {
		private Integer id;
		private TipoLancamento tipo;
		private Integer lancamentoId;
		private Calendar data;
		private Integer n;
		private Conta contaOrigem;
		private Double saldoOrigem;
		private Conta contaDestino;
		private Double saldoDestino;
		private Double valor;
		private String observacao;

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

		public Integer getLancamentoId() {
			return lancamentoId;
		}

		public void setLancamentoId(Integer lancamentoId) {
			this.lancamentoId = lancamentoId;
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
		TextGridFormattedColumn.createFormattedColumn(grid, "Lanc ID", TextGridFormattedColumn.INTEGER_FORMATTER, TextGridColumnAlignment.RIGHT, "getLancamentoId");
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
	public void run(Object param) throws ModeloSimulateException {
		ModeloSimulateSwitches switches = checkParamValid(param);

	}

	private ModeloSimulateSwitches checkParamValid(Object param) throws ModeloSimulateException {
		if (!(param instanceof ModeloSimulateSwitches)) {
			throw new ModeloSimulateException("Parametro deve ser um ModeloSimulateSwitches.");
		}

		ModeloSimulateSwitches switches = (ModeloSimulateSwitches) param;

		if (switches.getNomeModelo() == null) {
			throw new ModeloSimulateException("Nome do Modelo deve ser informado.");
		}

		return switches;
	}

}
