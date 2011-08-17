package br.com.zero.controlefinanceiro.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zero.controlefinanceiro.modelo.Orcamento;
import br.com.zero.controlefinanceiro.modelo.OrcamentoDAO;

/**
 * Servlet implementation class OrcamentoModeloExcluir
 */
public class OrcamentoModeloExcluir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int idOrcamento = Integer.parseInt(request.getParameter("id"));

		OrcamentoDAO dao = new OrcamentoDAO();

		Orcamento orcamento = dao.getById(idOrcamento);

		dao.excluir(orcamento);

		dao.close();

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("modelo.orcamento/exclusaoOk.jsp");

		dispatcher.forward(request, response);
	}

}
