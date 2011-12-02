package br.zero.controlefinanceiro.model;

import java.util.Calendar;

public interface ExtratoParser {
	
	public void parse(String line);
	
	public Calendar getData();
}
