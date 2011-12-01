package br.zero.controlefinanceiro.commandlineparser;

import java.util.Calendar;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.types.DateRange;

public class ModeloAnalyseSwitches {

	private String nomeModelo;
	private Calendar dataBase;
	private DateRange lancamentoRange;

	
	public String getNomeModelo() {
		return nomeModelo;
	}

	@CommandLineSwitch(index=1)
	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo;
	}

	public Calendar getDataBase() {
		return dataBase;
	}

	@CommandLineSwitch(index=2, parser="MonthDateParser.parseCalendar")
	public void setDataBase(Calendar database) {
		this.dataBase = database;
	}
	
	@CommandLineSwitch(param={"-lr", "--lancamento-range"}, parser="UtilsParser.parseDateRange")
	public void setLancamentoRange(DateRange value) {
		lancamentoRange = value;
	}
	
	public DateRange getLancamentoRange() {
		return lancamentoRange;
	}

}
