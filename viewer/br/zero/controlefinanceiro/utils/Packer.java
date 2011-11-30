package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.Lancamento;

public interface Packer<Packed extends Contabilizavel, ToPack> {
	public Packed pack(ToPack p);

	public static final Packer<LancamentoContabilizavel, Lancamento> LANCAMENTO_LANCAMENTOCONTABILIZAVEL_PACKAGER = new Packer<LancamentoContabilizavel, Lancamento>() {
		@Override
		public LancamentoContabilizavel pack(Lancamento lancamento) {
			LancamentoContabilizavel lancamentoContabilizavel = new LancamentoContabilizavel();
			lancamentoContabilizavel.setLancamentoBase(lancamento);

			return lancamentoContabilizavel;
		};
	};
}
