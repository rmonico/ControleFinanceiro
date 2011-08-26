<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="conta == null || conta.id == null">
	<s:set name="funcao" value="%{'inserir'}"/>
</s:if>
<s:else>
	<s:set name="funcao" value="&{'alterar'}"/>
</s:else>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
<s:if test="#funcao == 'inserir'"><s:text name="conta.inserir.titulo" /></s:if>
<s:if test="#funcao == 'alterar'"><s:text name="conta.alterar.titulo" /></s:if>
</title>
</head>
<body>
	<h1>
	&sdot;
	<s:if test="#funcao == 'inserir'"><s:text name="conta.inserir.titulo" /></s:if>
	<s:if test="#funcao == 'alterar'"><s:text name="conta.alterar.titulo" /></s:if>
	&sdot;
	</h1><br/>
	<br/>
	<s:form action="conta!salvar.action" method="post">
	    <s:hidden name="conta.id" value="%{conta.id}"/>
		<s:textfield name="conta.nome" value="%{conta.nome}" label="%{getText('conta.nome.label')}" size="40"/>
		<s:submit value="%{getText('application.salvar.label')}"/>
		<s:submit value="%{getText('application.cancelar.label')}" name="redirectAction:conta!listar"/>
	</s:form>
</body>
</html>