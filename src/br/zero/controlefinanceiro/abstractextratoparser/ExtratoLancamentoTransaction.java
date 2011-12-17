package br.zero.controlefinanceiro.abstractextratoparser;

import java.util.Calendar;

public interface ExtratoLancamentoTransaction extends ParsedExtratoLancamento {

	Calendar getData();

	String getReferencia();

	Double getValor();
}
