<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Produto</title>
</head>
<body>
	<h1>Lista de Produtos</h1>
	<table border="1">
	 	<tr>
	 		<th>Id</th>
	 		<th>Nome</th>
	 		<th>Tipo</th>
	 		<th>Validade</th>
	 		<th>Vencido</th>
	 		<th>Ver+</th>
	 		<th>Excluir</th>
	 	</tr>
	 	<c:forEach items="${produtos }" var="p">
	 	<tr <c:if test="${p.vencido }"> style="color:red;" </c:if> >
	 		<td>${p.id }</td>
	 		<td>${p.nome }</td>
	 		<td>${p.tipoProduto.toString() }</td>
	 		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${p.dataValidade.time }"/></td>
	 		<td>${p.vencido ? "Sim" : "Não" }</td>
	 		<td><a href="alterarProduto?idProduto=${p.id }">Alterar</a></td>
	 		<td><a href="excluirProduto?idProduto=${p.id }" onclick="return confirm('Deseja Excluir')">Excluir</a></td>
	 	</tr>
	 	</c:forEach>
	</table>
</body>
</html>