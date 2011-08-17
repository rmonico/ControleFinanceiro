<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Orçamento Modelo</title>

<script type="text/javascript">
	function confirmSubmit(mensagemConfirmacao, formulario) {
		if (confirm(mensagemConfirmacao)) {
			formulario.submit();
		}
	}
</script>

</head>
<body>
	<div></div>
	<h1>&sdot;Lista de Orçamento Modelo&sdot;</h1>
	<h6><a href=".">Controle Financeiro</a>&nbsp;&bull;&nbsp;<a href="sistema.sair">Sair &times; </a></h6>
	<a href="modelo.orcamento.listar?pagina=1">&laquo;</a>
	<a href="modelo.orcamento.listar?pagina=<%=((Integer)request.getAttribute("paginaatual"))>1?((Integer)request.getAttribute("paginaatual"))-1:1%>">&lsaquo;</a>
	${paginaatual}/${paginafinal}
	<a href="modelo.orcamento.listar?pagina=<%=((Integer)request.getAttribute("paginaatual"))<((Integer)request.getAttribute("paginafinal"))?((Integer)request.getAttribute("paginaatual"))+1:((Integer)request.getAttribute("paginafinal"))%>">&rsaquo;</a>
	<a href="modelo.orcamento.listar?pagina=${paginafinal}">&raquo;</a><br/>
	<br/>
	<h6><a href="modelo.orcamento.inserir">Inserir</a>&nbsp;&bull;&nbsp;<a href="modelo.orcamento.listar?pagina=${paginaatual}">Atualizar</a></h6>
	<table border=1 cellspacing=0 cellpadding="6">
		<!-- Header -->
		<tr>
			<td colspan="2" align="center"><b>Funções</b></td>
			<td colspan="1" align="center"><b>Links</b></td>
			<td><b>ID</b></td>
			<td><b>Nome</b></td>
		</tr>
		<!--  /Header -->
		
		<!-- Body -->
		<c:forEach var="modeloOrcamento" items="${listaModeloOrcamento}">
			<tr>
				<td><a href="modelo.orcamento.alterar?paginaorigem=${paginaatual}&id=${modeloOrcamento.id}">Alterar</a></td>
				<td><form action="modelo.orcamento.excluir" name="formularioConfirmacaoExclusao_${modeloOrcamento.id}"><input type="hidden" value="${modeloOrcamento.id}" name="id"><a href="javascript:confirmSubmit('Tem certeza de que deseja excluir esse Modelo de Orçamento?', document.formularioConfirmacaoExclusao_${modeloOrcamento.id});">Excluir</a></form></td>
				<td><a href="modelo.lancamento.listar?orcamentoid=${modeloOrcamento.id}">Lançamentos</a></td>
				<td>${modeloOrcamento.id}</td>
				<td>${modeloOrcamento.nome}</td>
			</tr>
		</c:forEach>
		<!-- /Body -->
	</table><br/>
	<a href="modelo.orcamento.listar?pagina=1">&laquo;</a>
	<a href="modelo.orcamento.listar?pagina=<%=((Integer)request.getAttribute("paginaatual"))>1?((Integer)request.getAttribute("paginaatual"))-1:1%>">&lsaquo;</a>
	${paginaatual}/${paginafinal}
	<a href="modelo.orcamento.listar?pagina=<%=((Integer)request.getAttribute("paginaatual"))<((Integer)request.getAttribute("paginafinal"))?((Integer)request.getAttribute("paginaatual"))+1:((Integer)request.getAttribute("paginafinal"))%>">&rsaquo;</a>
	<a href="modelo.orcamento.listar?pagina=${paginafinal}">&raquo;</a><br/>
	<h6><a href=".">Controle Financeiro</a>&nbsp;&bull;&nbsp;<a href="sistema.sair">Sair &times; </a></h6>
	<br/>
	<br/>
</body>
</html>