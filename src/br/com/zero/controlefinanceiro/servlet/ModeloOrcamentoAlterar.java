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
 * Servlet implementation class ModeloOrcamentoAlterar
 */
public class ModeloOrcamentoAlterar extends HttpServlet {
	private enum Acao {
		PADRAO, ALTERAR;
		
		public static Acao getAcaoByString(String string) {
			if (string == null || string.isEmpty()) {
				return PADRAO;
			}
			
			for (Acao acao : values()) {
				if (acao.name().equalsIgnoreCase(string)) {
					return acao;
				}
			}
			
			return null;
		}
	}

	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int idOrcamento = Integer.parseInt(request.getParameter("id"));
		
		OrcamentoDAO dao = new OrcamentoDAO();
		
		Orcamento orcamento = dao.getById(idOrcamento);
		
		Acao acao = Acao.getAcaoByString(request.getParameter("acao"));
		
		switch (acao) {
		case PADRAO: {
			dao.close();
			
			request.setAttribute("orcamento", orcamento);

			RequestDispatcher dispatcher = request
			.getRequestDispatcher("modelo.orcamento/alterar.jsp");

			dispatcher.forward(request, response);
			
			break;
		}
		case ALTERAR: {
			orcamento.setNome(request.getParameter("nome"));
			
			dao.alterar(orcamento);
			
			dao.close();
			
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("modelo.orcamento.listar");

			dispatcher.forward(request, response);
			
			break;
		}
		default: 
			throw new ServletException(new Exception("Acão não implementada."));
		}
	}
}
