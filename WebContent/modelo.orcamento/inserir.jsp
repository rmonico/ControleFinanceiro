<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserir Orçamento Modelo</title>
</head>
<body>
	<h1>&sdot;Inserir Orçamento Modelo&sdot;</h1><br/>
	<br/>
	<form action="modelo.orcamento.inserir" name="formularioInsercao">
		Nome:&nbsp;<input type="text" name="nome"/><br/>
		<br/>
		<input type="hidden" name="acao" value="inserir" />
		<h6><a href="javascript:document.formularioInsercao.submit();">Inserir</a>&nbsp;&bull;&nbsp;<a href="modelo.orcamento.listar">Cancelar</a></h6>
	</form>
</body>
</html>