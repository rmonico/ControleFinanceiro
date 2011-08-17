package br.com.zero.controlefinanceiro.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zero.controlefinanceiro.comum.Conta;
import br.com.zero.controlefinanceiro.comum.ContaDAO;

/**
 * Servlet implementation class ContaExcluir
 */
public class ContaExcluir extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int idConta = Integer.parseInt(request.getParameter("id"));

		ContaDAO dao = new ContaDAO();

		Conta conta = dao.getById(idConta);

		dao.excluir(conta);

		dao.close();

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("conta/exclusaoOk.jsp");

		dispatcher.forward(request, response);
	}

}
