<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Controle Financeiro</title>
</head>
<body>
	<h1>&sdot;Controle Financeiro&sdot;</h1>
	
    <ul>
        <li><a href="realizado.lancamento.listar">Realizado</a></li>
            
        <li><a href="previsaomes.orcamento.listar">Previsão mensal</a></li>
            
        <li><a href="modelo.orcamento.listar">Orçamentos Modelo</a></li>
            
        <li>Comum
            <ul>
                <li><a href="conta.listar.servlet">Contas</a></li>
                <li><a href="conta.listar">Contas - struts</a></li>
                <li><a href="formapagamento.listar">Formas de Pagamento</a></li>
            </ul>
        </li>
        
        <li>Administra&ccedil;&atilde;o
        	<ul>
        		<li><a href="sistema.mudarsenha">Mudar senha</a></li>
        		<li><a href="sistema.sair">Sair&nbsp;&times; </a></li>
        	</ul>
        </li>
            
    </ul>
</body>
</html>