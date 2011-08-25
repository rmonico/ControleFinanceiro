<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="contas.lista.titulo" /></title>

</head>
<body>
	<h1>&sdot;<s:text name="contas.lista.titulo" />&sdot;</h1>
	<br/>
	<table border=1 cellspacing=0 cellpadding="6">
		<!-- Header -->
		<tr>
			<td colspan="2" align="center"><b>Funções</b></td>
			<td><b>ID</b></td>
			<td><b>Nome</b></td>
		</tr>
		<!--  /Header -->
		
		<!-- Body -->
		<s:iterator value="contaList">
			<tr>
				<td>Alterar</td>
				<td>Excluir</td>
				<td><s:property value="id" /></td>
				<td><s:property value="nome" /></td>
			</tr>
		</s:iterator>
		<!-- c:forEach var="conta" items="${listacontas}" >
			<tr>
				<td><a href="conta.alterar?paginaorigem=${paginaatual}&id=${conta.id}">Alterar</a></td>
				<td><form action="conta.excluir" name="formularioConfirmacaoExclusao_${conta.id}"><input type="hidden" value="${conta.id}" name="id"><a href="javascript:confirmSubmit('Tem certeza de que deseja excluir essa conta?', document.formularioConfirmacaoExclusao_${conta.id});">Excluir</a></form></td>
				<td>${conta.id}</td>
				<td>${conta.nome}</td>
			</tr>
		</c:forEach -->
		<!-- /Body -->
	</table><br/>
	<br/>
</body>
</html>