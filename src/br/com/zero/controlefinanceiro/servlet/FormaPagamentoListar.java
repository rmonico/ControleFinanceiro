package br.com.zero.controlefinanceiro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zero.controlefinanceiro.comum.FormaPagamento;
import br.com.zero.controlefinanceiro.comum.FormaPagamentoDAO;

/**
 * Servlet implementation class FormaPagamentoListar
 */
public class FormaPagamentoListar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FormaPagamentoDAO dao = new FormaPagamentoDAO();
		
		List<FormaPagamento> listaFormasPagamento = dao.listarTodos();

		RequestDispatcher dispatcher = request.getRequestDispatcher("formapagamento/listar.jsp");
		
		request.setAttribute("listaFormasPagamento", listaFormasPagamento);
		request.setAttribute("paginaatual", 1);
		request.setAttribute("paginafinal", 1);

        dispatcher.forward(request, response);
	}

}
