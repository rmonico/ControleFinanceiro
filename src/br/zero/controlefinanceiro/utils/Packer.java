package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.Lancamento;

public interface Packer<Packed extends Contabilizavel, ToPack> {
	public Packed pack(ToPack p);

	// TODO Tirar esta implementação daqui e mover para o LancamentoListAction, só é usado la
	public static final Packer<LancamentoContabilizavel, Lancamento> LANCAMENTO_LANCAMENTOCONTABILIZAVEL_PACKER = new Packer<LancamentoContabilizavel, Lancamento>() {
		@Override
		public LancamentoContabilizavel pack(Lancamento lancamento) {
			LancamentoContabilizavel lancamentoContabilizavel = new LancamentoContabilizavel();
			lancamentoContabilizavel.setLancamentoBase(lancamento);

			return lancamentoContabilizavel;
		};
	};
}
