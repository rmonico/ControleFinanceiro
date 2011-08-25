package br.com.zero.controlefinanceiro.actions;

import java.util.List;

import br.com.zero.controlefinanceiro.comum.Conta;
import br.com.zero.controlefinanceiro.comum.ContaDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ContaActions extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4495054400441765031L;
    private List<Conta> contaList;
//	private ListProperties<Conta> contaListProperties;
	
	/* Ações */
	
	public String doListar() {
		return SUCCESS;
	}
	
	public String doInserir() {
		return INPUT;
	}
	
	/* Beans */
//	public ListProperties<Conta> getContaListProperties() {
//		if (contaListProperties == null) {
//			contaListProperties = new ListProperties<Conta>(getContaList());
//		}
//		return contaListProperties;
//	}
	
	public List<Conta> getContaList() {
        if (contaList == null) {
	        ContaDAO dao = new ContaDAO();

	        contaList = dao.listarTodos();

	        dao.close();
		}
        
        return contaList;
	}
}
