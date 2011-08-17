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
 * Servlet implementation class ContaInserir
 */
public class ContaInserir extends HttpServlet {
	private enum Acao {
		PADRAO, INSERIR;

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
		Acao acao = Acao.getAcaoByString(request.getParameter("acao"));

		switch (acao) {
		case PADRAO: {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("conta/inserir.jsp");

			dispatcher.forward(request, response);
			break;
		}
		case INSERIR: {
			String nome = request.getParameter("nome");

			Conta conta = new Conta();

			conta.setNome(nome);

			ContaDAO dao = new ContaDAO();

			dao.inserir(conta);
			
			dao.close();

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("conta/insercaoOk.jsp");

			dispatcher.forward(request, response);
			break;
		}
		default:
			throw new ServletException(new Exception("Acão não implementada."));

		}

	}

}
