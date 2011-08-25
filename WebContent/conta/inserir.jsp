<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="conta.inserir.titulo" /></title>
</head>
<body>
	<h1>&sdot;<s:text name="conta.inserir.titulo" />&sdot;</h1><br/>
	<br/>
	<s:form action="conta.inserir!salvar.action" method="post">
		<s:textfield name="conta.nome" value="%{conta.nome}" label="%{getText('conta.nome.label')}" size="40"/>
		<s:submit value="%{getText('application.inserir.label')}"/>
		<s:submit value="%{getText('application.cancelar.label')}" name="redirectAction:conta.listar"/>
	</s:form>
</body>
</html>