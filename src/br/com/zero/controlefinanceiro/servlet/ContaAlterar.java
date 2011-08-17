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
 * Servlet implementation class ContaAlterar
 */
public class ContaAlterar extends HttpServlet {
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

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int idConta = Integer.parseInt(request.getParameter("id"));
		
		ContaDAO dao = new ContaDAO();
		
		Conta conta = dao.getById(idConta);

		Acao acao = Acao.getAcaoByString(request.getParameter("acao"));
		
		switch (acao) {
		case PADRAO: {
			dao.close();
			
			request.setAttribute("conta", conta);
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("conta/alterar.jsp");

			dispatcher.forward(request, response);
			
			break;
		}
		case ALTERAR: {
			conta.setNome(request.getParameter("nome"));
			
			dao.alterar(conta);
			
			dao.close();
			
			RequestDispatcher dispatcher = request
			.getRequestDispatcher("conta.listar");

			dispatcher.forward(request, response);			
			
			break;
		}
		default:
			throw new ServletException(new Exception("Acão não implementada."));
		}
	}

}
