package br.zero.controlefinanceiro.abstractextratoparser;

import java.util.Calendar;

public interface DatedExtratoLancamento extends ParsedExtratoLancamento {
	
	Calendar getData();

}
