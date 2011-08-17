package br.com.zero.controlefinanceiro.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zero.controlefinanceiro.comum.FormaPagamento;
import br.com.zero.controlefinanceiro.comum.FormaPagamentoDAO;

/**
 * Servlet implementation class FormaPagamentoExcluir
 */
public class FormaPagamentoExcluir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int idFormaPagamento = Integer.parseInt(request.getParameter("id"));

		FormaPagamentoDAO dao = new FormaPagamentoDAO();

		FormaPagamento formaPagamento = dao.getById(idFormaPagamento);

		dao.excluir(formaPagamento);

		dao.close();

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("formapagamento/exclusaoOk.jsp");

		dispatcher.forward(request, response);

	}

}
