<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alteração de Orçamento Modelo</title>
</head>
<body>
	<h1>&sdot;Alterar Orçamento Modelo&sdot;</h1><br/>
	<br/>
	<form action="modelo.orcamento.alterar" name="formularioAlteracao">
		Nome:&nbsp;<input type="text" name="nome" value="${orcamento.nome}"/><br/>
		<br/>
		<input type="hidden" name="acao" value="alterar" />
		<input type="hidden" name="id" value="${orcamento.id}" />
		<h6><a href="javascript:document.formularioAlteracao.submit();">Alterar</a>&nbsp;&bull;&nbsp;<a href="modelo.orcamento.listar">Cancelar</a></h6>
	</form>
</body>
</html>