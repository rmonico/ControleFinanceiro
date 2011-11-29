package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.textgrid.TextGridFormatter;

public class ControleFinanceiroFormatters {

	public static final TextGridFormatter CONTA_FORMATTER = createContaFormatter();
	public static final TextGridFormatter MODELO_FORMATTER = createModeloFormatter();

	private static TextGridFormatter createContaFormatter() {
		TextGridFormatter contaFormatter = new TextGridFormatter() {
			
			@Override
			public StringBuilder parse(Object cellValue) throws TextGridException {
				if (cellValue == null) {
					return TextGridFormattedColumn.NULL_FORMATTER.parse(cellValue);
				}
				
				if (!(cellValue instanceof Conta)) {
					throw new TextGridException("CONTA_FORMATTER: Must be used only with br.zero.controlefinanceiro.model.Conta fields.");
				}
				
				Conta value = (Conta) cellValue;
				
				StringBuilder finalValue = new StringBuilder(value.getNome());
				
				return finalValue;
			}
		};
		
		return contaFormatter;
	}

	private static TextGridFormatter createModeloFormatter() {
		TextGridFormatter modeloFormatter = new TextGridFormatter() {
			
			@Override
			public StringBuilder parse(Object cellValue) throws TextGridException {
				if (cellValue == null) {
					return new StringBuilder("[null]");
				}
				
				if (!(cellValue instanceof Modelo)) {
					throw new TextGridException("MODELO_FORMATTER: Must be used only with br.zero.controlefinanceiro.model.Modelo fields.");
				}
				
				Modelo value = (Modelo) cellValue;
				
				StringBuilder finalValue = new StringBuilder(value.getNome());
				
				return finalValue;
			}
		};
		
		return modeloFormatter;
	}

}
