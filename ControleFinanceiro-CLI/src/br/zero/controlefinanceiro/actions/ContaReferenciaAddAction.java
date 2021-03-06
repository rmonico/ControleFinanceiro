package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.ContaReferenciaAddSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.extrato.ReferenciaExtrato;
import br.zero.controlefinanceiro.model.extrato.ReferenciaExtratoDAO;
import br.zero.tinycontroller.Action;

public class ContaReferenciaAddAction implements Action<ContaReferenciaAddSwitches, Object> {

	public class ContaReferenciaAddException extends Exception {

		public ContaReferenciaAddException(String message) {
			super("ctaref-add: " + message);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	}

	@Override
	public Object run(ContaReferenciaAddSwitches switches) throws Exception {
		checkParamValid(switches);
		
		ContaDAO contaDAO = new ContaDAO();
		
		Conta conta = contaDAO.getByNome(switches.getNomeConta());
		
		if (conta == null) {
			throw new ContaReferenciaAddException("Conta não encontrada: \"" + switches.getNomeConta());
		}
		
		Conta banco = contaDAO.getByNome(switches.getNomeBanco());
		
		if (banco == null) {
			throw new ContaReferenciaAddException("Banco não encontrado: \"" + switches.getNomeBanco());
		}
		
		ReferenciaExtrato re = new ReferenciaExtrato();
		
		re.setConta(conta);
		re.setBanco(banco);
		
		ReferenciaExtratoDAO dao = new ReferenciaExtratoDAO();
		
		re.setN(dao.getNextNFor(conta, banco));
		re.setReferencia(switches.getRegex());

		dao.inserir(re);
		
		System.out.println();
		
		System.out.println("-- Referência Adicionada --");
		
		System.out.println(re);
		
		return null;
	}

	private void checkParamValid(ContaReferenciaAddSwitches switches) throws ContaReferenciaAddException {
		if (switches.getNomeConta() == null) {
			throw new ContaReferenciaAddException("Conta deve ser informada.");
		}

		if (switches.getNomeBanco() == null) {
			throw new ContaReferenciaAddException("Banco deve ser informado.");
		}

		if (switches.getRegex() == null) {
			throw new ContaReferenciaAddException("String de regex deve ser informada.");
		}
	}

}
