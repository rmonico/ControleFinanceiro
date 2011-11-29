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
	
	public <Packed extends Contabilizavel, ToPack> List<Packed> packageList(List<ToPack> toPackList, Packager<Packed, ToPack> packager) {
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
		 Conta contaOrigem = lancamento.getContaOrigem();
		
		 Double saldoOrigem = saldos.get(contaOrigem);
		
		 if (saldoOrigem == null) {
		 saldoOrigem = 0.0;
		 }
		
		 saldoOrigem -= lancamento.getValor();
		
		 saldos.put(contaOrigem, saldoOrigem);
		
		 lancamento.setSaldoOrigem(saldoOrigem);
		
		
		 Conta contaDestino = lancamento.getContaDestino();
		
		 Double saldoDestino = saldos.get(contaDestino);
		
		 if (saldoDestino == null) {
		 saldoDestino = 0.0;
		 }
		
		 saldoDestino += lancamento.getValor();
		
		 saldos.put(contaDestino, saldoDestino);
		
		 lancamento.setSaldoDestino(saldoDestino);
		 }

	}

	public Map<Conta, Double> getSaldosPorConta() {
		return saldos;
	}
}
