package br.zero.controlefinanceiro.commandlineparser;

import java.util.Calendar;

import br.zero.commandlineparser.CommandLineSwitch;

public class ModeloSimulateSwitches {

	private String nomeModelo;
	private Calendar dataBase;

	public String getNomeModelo() {
		return nomeModelo;
	}

	@CommandLineSwitch(index = 1)
	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo;
	}

	public Calendar getDataBase() {
		return dataBase;
	}

	@CommandLineSwitch(index = 2, parser="MonthDateParser.parseCalendar")
	public void setDataBase(Calendar dataBase) {
		this.dataBase = dataBase;
	}

}
