<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lançamentos Modelo - ${orcamento.nome}</title>

<script type="text/javascript">
	function confirmSubmit(mensagemConfirmacao, formulario) {
		if (confirm(mensagemConfirmacao)) {
			formulario.submit();
		}
	}
</script>

</head>
<body>
	<h1>&sdot;Lançamentos Modelo - ${orcamento.nome}&sdot;</h1>
	<h6><a href=".">Controle Financeiro</a>&nbsp;&bull;&nbsp;<a href="modelo.orcamento.listar">Orçamento Modelo</a>&nbsp;&bull;&nbsp;<a href="sistema.sair">Sair &times; </a></h6>
	<a href="modelo.lancamento.listar?pagina=1">&laquo;</a>
	<a href="modelo.lancamento.listar?pagina=<%=((Integer)request.getAttribute("paginaatual"))>1?((Integer)request.getAttribute("paginaatual"))-1:1%>">&lsaquo;</a>
	${paginaatual}/${paginafinal}
	<a href="modelo.lancamento.listar?pagina=<%=((Integer)request.getAttribute("paginaatual"))<((Integer)request.getAttribute("paginafinal"))?((Integer)request.getAttribute("paginaatual"))+1:((Integer)request.getAttribute("paginafinal"))%>">&rsaquo;</a>
	<a href="modelo.lancamento.listar?pagina=${paginafinal}">&raquo;</a><br/>
	<br/>
	<h6><a href="modelo.lancamento.inserir">Inserir</a>&nbsp;&bull;&nbsp;<a href="modelo.lancamento.listar?pagina=${paginaatual}">Atualizar</a></h6>
	<table border=1 cellspacing=0 cellpadding="6">
		<!-- Header -->
		<tr>
			<td colspan="2" align="center"><b>Funções</b></td>
			<td><b>ID</b></td>
			<td><b>Nome</b></td>
		</tr>
		<!--  /Header -->
		
		<!-- Body -->
		<c:forEach var="modeloLancamento" items="${listaModeloLancamento}">
			<tr>
				<td><a href="modelo.lancamento.alterar?paginaorigem=${paginaatual}&id=${modeloLancamento.id}">Alterar</a></td>
				<td><form action="modelo.lancamento.excluir" name="formularioConfirmacaoExclusao_${modeloLancamento.id}"><input type="hidden" value="${modeloLancamento.id}" name="id"><a href="javascript:confirmSubmit('Tem certeza de que deseja excluir essa Lançamento?', document.formularioConfirmacaoExclusao_${modeloLancamento.id});">Excluir</a></form></td>
				<td>${modeloLancamento.id}</td>
				<td>${modeloLancamento.nome}</td>
			</tr>
		</c:forEach>
		<!-- /Body -->
	</table><br/>
	<a href="modelo.lancamento.listar?pagina=1">&laquo;</a>
	<a href="modelo.lancamento.listar?pagina=<%=((Integer)request.getAttribute("paginaatual"))>1?((Integer)request.getAttribute("paginaatual"))-1:1%>">&lsaquo;</a>
	${paginaatual}/${paginafinal}
	<a href="modelo.lancamento.listar?pagina=<%=((Integer)request.getAttribute("paginaatual"))<((Integer)request.getAttribute("paginafinal"))?((Integer)request.getAttribute("paginaatual"))+1:((Integer)request.getAttribute("paginafinal"))%>">&rsaquo;</a>
	<a href="modelo.lancamento.listar?pagina=${paginafinal}">&raquo;</a><br/>
	<h6><a href=".">Controle Financeiro</a>&nbsp;&bull;&nbsp;<a href="modelo.orcamento.listar">Orçamento Modelo</a>&nbsp;&bull;&nbsp;<a href="sistema.sair">Sair &times; </a></h6>
	<br/>
	<br/>
</body>
</html>