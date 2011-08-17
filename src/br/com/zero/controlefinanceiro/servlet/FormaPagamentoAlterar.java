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
 * Servlet implementation class FormaPagamentoAlterar
 */
public class FormaPagamentoAlterar extends HttpServlet {
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
		int idFormaPagamento = Integer.parseInt(request.getParameter("id"));

		FormaPagamentoDAO dao = new FormaPagamentoDAO();

		FormaPagamento formaPagamento = dao.getById(idFormaPagamento);

		Acao acao = Acao.getAcaoByString(request.getParameter("acao"));

		switch (acao) {
		case PADRAO: {
			dao.close();

			request.setAttribute("formapagamento", formaPagamento);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("formapagamento/alterar.jsp");

			dispatcher.forward(request, response);

			break;
		}
		case ALTERAR: {
			formaPagamento.setNome(request.getParameter("nome"));

			dao.alterar(formaPagamento);

			dao.close();

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("formapagamento.listar");

			dispatcher.forward(request, response);

			break;
		}
		default:
			throw new ServletException(new Exception("Acão não implementada."));

		}
	}

}
