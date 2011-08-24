package br.com.zero.controlefinanceiro.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zero.controlefinanceiro.comum.Conta;
import br.com.zero.controlefinanceiro.comum.ContaDAO;

/**
 * Servlet implementation class ContaServletNew
 */
public class ContaListar extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println();
    	System.out.println(System.getProperties());
    	System.out.println();
        ContaDAO dao = new ContaDAO();

        List<Conta> listaContas = dao.listarTodos();

        dao.close();

        request.setAttribute("listacontas", listaContas);
        request.setAttribute("paginaatual", 1);
        request.setAttribute("paginafinal", 1);

        RequestDispatcher dispatcher = request.getRequestDispatcher("conta_jstl/listar.jsp");

        dispatcher.forward(request, response);
    }
}
