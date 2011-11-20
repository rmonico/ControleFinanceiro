package br.zero.controlefinanceiro.action;

import br.zero.controlefinanceiro.actions.Action;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;

public class LancamentoListAction implements Action {

	@Override
	public void setSwitches(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		System.out.println("Modelos de Lançamento:\n\n");
		LancamentoDAO dao = new LancamentoDAO();

		for (Lancamento lancamento : dao.listarTodos()) {
			StringBuilder sb = new StringBuilder();

			sb.append("#" + lancamento.getId());
			sb.append("; " + lancamento.getData());
			sb.append("; " + lancamento.getN() + " [");
			sb.append(lancamento.getContaOrigem().getNome() + " -- " + lancamento.getContaDestino().getNome() + "; ");
			sb.append("$" + lancamento.getValor() + "; ");
			if (lancamento.getObservacao() != null) {
				sb.append("; (" + lancamento.getObservacao() + ")");
			}

			sb.append("]");

			System.out.println(sb.toString());
		}

		System.out.println("\n");

		System.out.println("-- Fim");

	}

}
