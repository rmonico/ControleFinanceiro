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
	<s:url action="main.action" var="mainActionLink" />
	<s:url action="logoff.action" var="logoffActionLink" />
	<s:url action="conta.inserir.action" var="contaInserirActionLink" />
	<s:url action="conta.listar.action" var="contaListarActionLink" />

	<h1>&sdot;<s:text name="contas.lista.titulo" />&sdot;</h1>
	<h6><a href="${mainActionLink}"><s:text name="application.name" /></a>&nbsp;&bull;&nbsp;<a href="${logoffActionLink}"><s:text name="logoff.label" /> &times; </a></h6>
	<br/>
	<h6><a href="${contaInserirActionLink}"><s:text name="application.inserir.label" /> </a>&nbsp;&bull;&nbsp;<a href="${contaListarActionLink}"><s:text name="application.atualizar.label" /> </a></h6>
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

		<!-- /Body -->
	</table><br/>
	<h6><a href="${mainActionLink}"><s:text name="application.name" /></a>&nbsp;&bull;&nbsp;<a href="${logoffActionLink}"><s:text name="logoff.label" /> &times; </a></h6>
</body>
</html>