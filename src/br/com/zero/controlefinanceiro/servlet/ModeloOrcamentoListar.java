package br.com.zero.controlefinanceiro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zero.controlefinanceiro.modelo.Orcamento;
import br.com.zero.controlefinanceiro.modelo.OrcamentoDAO;

/**
 * Servlet implementation class ModeloOrcamentoListar
 */
public class ModeloOrcamentoListar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("modelo.orcamento/listar.jsp");
		
		OrcamentoDAO dao = new OrcamentoDAO();
		
		List<Orcamento> listaModeloOrcamento = dao.listarTodos();
		
		request.setAttribute("listaModeloOrcamento", listaModeloOrcamento);
		request.setAttribute("paginaatual", 1);
		request.setAttribute("paginafinal", 1);

        dispatcher.forward(request, response);
	}

}
