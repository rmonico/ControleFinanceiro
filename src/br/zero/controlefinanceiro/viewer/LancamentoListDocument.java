package br.zero.controlefinanceiro.viewer;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.utils.Contabilizador;
import br.zero.controlefinanceiro.utils.LancamentoContabilizavel;
import br.zero.controlefinanceiro.utils.Packer;
import br.zero.observer.DateFormatter;
import br.zero.observer.Document;
import br.zero.observer.Link;
import br.zero.observer.ObserverException;
import br.zero.observer.Table;
import br.zero.observer.TableColumn;

public class LancamentoListDocument extends Document {

	public final class LancamentoLine {
		private LancamentoContabilizavel lancamento;

		public LancamentoLine(LancamentoContabilizavel lancamento) {
			this.lancamento = lancamento;
			updateLinks();
		}

		private Link linkInserir = new Link("lanc-add", "Inserir");
		private Link linkAlterar = new Link("lanc-edit", "Alterar");
		private Link linkExcluir = new Link("lanc-rm", "Remover");

		@TableColumn(title = "ID", index = 0)
		public Integer getId() {
			return lancamento.getId();
		}

		private void updateLinks() {
			linkInserir.getParams().put("id", getId());
			linkAlterar.getParams().put("id", getId());
			linkExcluir.getParams().put("id", getId());
		}

		@TableColumn(title = "Data", index = 1, formatterClass = DateFormatter.class)
		public Calendar getData() {
			return lancamento.getData();
		}

		@TableColumn(title = "N", index = 2)
		public Integer getN() {
			return lancamento.getN();
		}

		@TableColumn(title = "Origem", index = 3)
		public Conta getContaOrigem() {
			return lancamento.getContaOrigem();
		}

		@TableColumn(title = "Saldo", index = 4)
		public Double getSaldoOrigem() {
			return lancamento.getSaldoOrigem();
		}

		@TableColumn(title = "Destino", index = 5)
		public Conta getContaDestino() {
			return lancamento.getContaDestino();
		}

		@TableColumn(title = "Saldo", index = 6)
		public Double getSaldoDestino() {
			return lancamento.getSaldoDestino();
		}

		@TableColumn(title = "Valor", index = 7)
		public Double getValor() {
			return lancamento.getValor();
		}

		@TableColumn(title = "Observação", index = 8)
		public String getObservacao() {
			return lancamento.getObservacao();
		}

		@TableColumn(title = "Inserir", index = 9)
		public Link getLinkInserir() {
			return linkInserir;
		}

		@TableColumn(title = "Alterar", index = 10)
		public Link getLinkAlterar() {
			return linkAlterar;
		}

		@TableColumn(title = "Excluir", index = 11)
		public Link getLinkExcluir() {
			return linkExcluir;
		}

	}

	public final class SaldoLine {
		private Entry<Conta, Double> entry;

		public SaldoLine(Entry<Conta, Double> entry) {
			this.entry = entry;
		}

		@TableColumn(title = "Conta", index = 0)
		public Conta getConta() {
			return entry.getKey();
		}

		@TableColumn(title = "Saldo", index = 1)
		public Double getSaldo() {
			return entry.getValue();
		}

	}

	@Override
	public void create() throws ObserverException {
		LancamentoDAO dao = new LancamentoDAO();

		List<Lancamento> lancamentoList = dao.listarTodos();

		Contabilizador contabilizador = new Contabilizador();

		Packer<LancamentoContabilizavel, Lancamento> packager = Packer.LANCAMENTO_LANCAMENTOCONTABILIZAVEL_PACKER;

		List<LancamentoContabilizavel> lancamentoContabilizavelList = contabilizador.packageList(lancamentoList, packager);

		contabilizador.setList(lancamentoContabilizavelList);

		contabilizador.contabilizar();

		setTitle("Lista de Lançamentos");

		Table<LancamentoLine> table = new Table<LancamentoLine>(LancamentoLine.class);

		for (LancamentoContabilizavel lancamentoContabilizavel : lancamentoContabilizavelList) {
			LancamentoLine row = new LancamentoLine(lancamentoContabilizavel);

			table.addRow(row);
		}

		addElement(table);

		Map<Conta, Double> saldos = contabilizador.getSaldosPorConta();

		Table<SaldoLine> saldoTable = new Table<SaldoLine>(SaldoLine.class);

		for (Entry<Conta, Double> entry : saldos.entrySet()) {
			SaldoLine saldoLine = new SaldoLine(entry);
			
			saldoTable.addRow(saldoLine);
		}
		
		addElement(saldoTable);
	}

}
 