package br.zero.controlefinanceiro.viewer;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MasterServlet
 */
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, DocumentFactory> registeredDocumentFactories;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MasterServlet() {
		registeredDocumentFactories = new HashMap<String, DocumentFactory>();

		// TODO Está dependente do nome do contexto, tirar isso daqui e fazer
		// ficar dependente apenas do nome da servlet
		registerDocumentFactory("lanc-add", new LancamentoAddDocumentFactory());
		registerDocumentFactory("lanc-list", new LancamentoListDocumentFactory());
	}

	private void registerDocumentFactory(String uri, DocumentFactory documentFactory) {
		registeredDocumentFactories.put(uri, documentFactory);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletName = extractServletName(request.getRequestURI());
		DocumentFactory documentFactory = registeredDocumentFactories.get(servletName);

		Document document = documentFactory.createDocument();

		document.render(new PrintStream(response.getOutputStream()));
	}

	private String extractServletName(String uri) {
		int lastBarIndex = uri.lastIndexOf('/') + 1;

		return uri.substring(lastBarIndex);
	}

}
