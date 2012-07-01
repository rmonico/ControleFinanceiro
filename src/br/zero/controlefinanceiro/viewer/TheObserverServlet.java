package br.zero.controlefinanceiro.viewer;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.zero.controlefinanceiro.ControleFinanceiroController;
import br.zero.observer.Document;
import br.zero.observer.ObserverException;
import br.zero.observer.Renderer;
import br.zero.observer.htmlrenderer.HTMLDefaultStyle;
import br.zero.observer.htmlrenderer.HTMLRenderer;
import br.zero.observer.htmlrenderer.HTMLStyle;
import br.zero.tinycontroller.TinyControllerException;

/**
 * Servlet implementation class TheObserverServlet
 */
public class TheObserverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws
	 * @see HttpServlet#HttpServlet()
	 */
	public TheObserverServlet() throws TinyControllerException {
		ControleFinanceiroController.setup();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Converter a linha de comando passada no request e converter em
		// uma matriz de strings, o nome do servlet deve ser a primeira string
		HTMLStyle defaultStyle = new HTMLDefaultStyle();

		Renderer renderer;
		renderer = new HTMLRenderer(defaultStyle);

		Document document = getDocument(request.getRequestURI());

		renderer.setDocument(document);

		PrintStream output = new PrintStream(response.getOutputStream());

		renderer.setOutput(output);

		try {
			renderer.renderize();
		} catch (ObserverException e) {
			throw new ServletException(e);
		}

	}

	private Document getDocument(String uri) {
		String documentKey = extractDocumentMappingKey(uri);

		Document document = registeredDocuments.get(documentKey);

		return document;
	}

	private String extractDocumentMappingKey(String uri) {
		int lastBarIndex = uri.lastIndexOf('/') + 1;

		return uri.substring(lastBarIndex);
	}

}
