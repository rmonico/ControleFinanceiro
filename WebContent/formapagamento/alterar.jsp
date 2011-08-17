<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alteração de Forma de Pagamento</title>
</head>
<body>
	<h1>&sdot;Alterar Forma de Pagamento&sdot;</h1><br/>
	<br/>
	<form action="formapagamento.alterar" name="formularioAlteracao">
		Nome:&nbsp;<input type="text" name="nome" value="${formapagamento.nome}"/><br/>
		<br/>
		<input type="hidden" name="acao" value="alterar" />
		<input type="hidden" name="id" value="${formapagamento.id}" />
		<h6><a href="javascript:document.formularioAlteracao.submit();">Alterar</a>&nbsp;&bull;&nbsp;<a href="formapagamento.listar">Cancelar</a></h6>
	</form>
</body>
</html>