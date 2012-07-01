package br.zero.controlefinanceiro.viewer.formatters;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.observer.Formatter;
import br.zero.observer.NullFormatter;
import br.zero.observer.ObserverException;

public class ContaFormatter implements Formatter {

	@Override
	public String parse(Object value) throws ObserverException {
		if (value == null) {
			return NullFormatter.instance.parse(value);
		}
		
		if (!(value instanceof Conta)) {
			throw new ObserverException("ContaFormatter: Must be used only with br.zero.controlefinanceiro.model.Conta.");
		}

		Conta conta = (Conta) value;
		
		return conta.getNome(); 
	}

}
