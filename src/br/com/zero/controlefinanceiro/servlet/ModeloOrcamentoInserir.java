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
 * Servlet implementation class ModeloOrcamentoInserir
 */
public class ModeloOrcamentoInserir extends HttpServlet {
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
					.getRequestDispatcher("modelo.orcamento/inserir.jsp");

			dispatcher.forward(request, response);
			
			break;
		}
		case INSERIR: {
			String nome = request.getParameter("nome");

			Orcamento orcamento = new Orcamento();

			orcamento.setNome(nome);

			OrcamentoDAO dao = new OrcamentoDAO();

			dao.inserir(orcamento);
			
			dao.close();

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("modelo.orcamento/insercaoOk.jsp");

			dispatcher.forward(request, response);
			break;
		}
		}
	}
}
