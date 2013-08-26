package br.zero.fin.database;

import br.zero.androidhelpers.databasestructure.table.AbstractTableStructure;
import br.zero.androidhelpers.databasestructure.table.DatabaseStructureException;
import br.zero.androidhelpers.databasestructure.table.Field;
import br.zero.androidhelpers.databasestructure.table.FieldType;
import br.zero.fin.model.Lancamento;

public class LancamentoTableStructure extends AbstractTableStructure<Lancamento> {

	private Field lancamentomodeloid;
	private Field date;
	private Field contaorigemid;
	private Field contadestinoid;
	private Field valor;
	private Field observacao;
	private Field n;
	private Field extratoid;

	public LancamentoTableStructure()
			throws DatabaseStructureException {
		super(Lancamento.class);
// TODO Depois faço a estrutura destas tabelas		
//		lancamentomodeloid = new Field("lancamentoid", new ForeignFieldType<br.zero.fin.model.modelo.Lancamento>(LancamentoModeloTableStructure.class), Lancamento.class, "getModelo", "setModelo");
		lancamentomodeloid = new Field("lancamentomodeloid", FieldType.INTEGER, Lancamento.class, "getModelo", "setModelo");
		date = new Field("data", FieldType.DATE, Lancamento.class, "getDate", "setDate");
		contaorigemid = new Field("contaorigemid", ContaTableStructure.getForeignFieldType(), Lancamento.class, "getOrigem", "setOrigem");
		contadestinoid = new Field("contadestinoid", ContaTableStructure.getForeignFieldType(), Lancamento.class, "getDestino", "setDestino");
		valor = new Field("valor", FieldType.REAL, Lancamento.class, "getValor", "setValor");
		observacao = new Field("observacao", FieldType.TEXT, Lancamento.class, "getObservacao", "setObservacao");
		n = new Field("n", FieldType.INTEGER, Lancamento.class, "getN", "setN");
//		extratoid = new Field("extratoid", FieldType.FOREIGN, Lancamento.class, "getExtrato", "setExtrato");
		extratoid = new Field("extratoid", FieldType.INTEGER, Lancamento.class, "getExtrato", "setExtrato");
	}

	@Override
	public String getName() {
		return "lancamento";
	}

	@Override
	protected Field[] createFields() {
		return new Field[] { _id, lancamentomodeloid, date, contaorigemid, contadestinoid, valor, observacao, n, extratoid };
	}

}
