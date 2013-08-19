package br.zero.controlefinanceiro.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.zero.controlefinanceiro.model.Conta;

/**
 * Classe responsáve por contabilizar os saldos em Contabilizaveis.
 * 
 * @author Rafael Monico
 * 
 */
public class Contabilizador {

	private List<? extends Contabilizavel> lancamentoList;
	private Map<Conta, Double> saldos;

	public <Packed extends Contabilizavel, ToPack> List<Packed> packageList(List<ToPack> toPackList, Packer<Packed, ToPack> packager) {
		List<Packed> packedList = new ArrayList<Packed>();

		for (ToPack toPack : toPackList) {
			Packed packed = packager.pack(toPack);

			packedList.add(packed);
		}

		return packedList;
	}

	public void setList(List<? extends Contabilizavel> lancamentoForList) {
		this.lancamentoList = lancamentoForList;
	}

	public void contabilizar() {
		saldos = new HashMap<Conta, Double>();

		for (Contabilizavel lancamento : lancamentoList) {
			Double saldoOrigem = updateSaldo(lancamento.getContaOrigem(), -lancamento.getValor());

			lancamento.setSaldoOrigem(saldoOrigem);
			
			
			Double saldoDestino = updateSaldo(lancamento.getContaDestino(), lancamento.getValor());

			lancamento.setSaldoDestino(saldoDestino);
		}

	}

	private Double updateSaldo(Conta conta, Double valor) {
		if (!conta.getContabilizavel()) {
			return null;
		}

		Double saldo = saldos.get(conta);

		if (saldo == null) {
			saldo = 0.0;
		}

		saldo += valor;

		saldos.put(conta, saldo);
		
		return saldo;
	}

	public Map<Conta, Double> getSaldosPorConta() {
		return saldos;
	}
}
