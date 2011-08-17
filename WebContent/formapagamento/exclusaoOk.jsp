<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exclusão de Forma de Pagamento</title>

<script type="text/javascript">
	function confirmSubmit(mensagemConfirmacao, formulario) {
		if (confirm(mensagemConfirmacao)) {
			formulario.submit();
		}
	}
</script>
</head>
<body>
	<h1>&sdot;Excluir Forma de Pagamento&sdot;</h1><br/>
	<br/>
	Forma de Pagamento excluída com sucesso.<br/>
	<br/>
	<h6><a href=".">Controle Financeiro</a>&nbsp;&bull;&nbsp;<a href="formapagamento.listar">Lista de Formas de Pagamento</a></h6>
</body>
</html>