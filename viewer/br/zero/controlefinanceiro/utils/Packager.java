package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.Lancamento;

public interface Packager<Packed extends Contabilizavel, ToPack> {
	public Packed pack(ToPack p);

	public static final Packager<LancamentoContabilizavel, Lancamento> LANCAMENTO_LANCAMENTOCONTABILIZAVEL_PACKAGER = new Packager<LancamentoContabilizavel, Lancamento>() {
		@Override
		public LancamentoContabilizavel pack(Lancamento lancamento) {
			LancamentoContabilizavel lancamentoContabilizavel = new LancamentoContabilizavel();
			lancamentoContabilizavel.setLancamento(lancamento);

			return lancamentoContabilizavel;
		};
	};
}
