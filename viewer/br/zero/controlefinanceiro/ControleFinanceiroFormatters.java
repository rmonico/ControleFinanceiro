package br.zero.controlefinanceiro;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormatter;

public class ControleFinanceiroFormatters {

	public static final TextGridFormatter CONTA_FORMATTER = createContaFormatter();

	private static TextGridFormatter createContaFormatter() {
		TextGridFormatter contaFormatter = new TextGridFormatter() {
			
			@Override
			public StringBuilder parse(Object cellValue) throws TextGridException {
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

}
