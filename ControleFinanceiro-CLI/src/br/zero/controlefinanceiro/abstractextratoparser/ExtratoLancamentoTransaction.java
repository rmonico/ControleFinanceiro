package br.zero.controlefinanceiro.abstractextratoparser;

import java.util.Calendar;

public interface ExtratoLancamentoTransaction extends DatedExtratoLancamento {

	Calendar getData();

	String getReferencia();

	Double getValor();
}
