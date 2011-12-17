package br.zero.controlefinanceiro.model;

import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;

public interface ParsedExtratoLancamento {
	void setOrigem(ExtratoLancamento lancamento);
	ExtratoLancamento getOrigem();
}
