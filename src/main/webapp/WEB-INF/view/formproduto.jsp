<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Cadastro de Produto</title>
</head>
<body>
	<fieldset>
		<form action="salvarProduto">
		<input type="hidden" name="id" value="${produto.id }">
			<h1>Formulário de cadastro de produto</h1>
			<table>
				<tr>
					<td><label>Nome:</label></td>
					<td><input type="text" name="nome" value="${produto.nome }"/></td>
				</tr>
				<tr>
					<td><label>Validade:</label></td>
					<td><input type="date" name="dataValidade" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${produto.dataValidade.time }"/>" /></td>
				</tr>
				<tr>
					<td><label>Preço:</label></td>
					<td><input type="text" name="preco" value="${produto.preco }" /></td>
				</tr>
				<tr>
					<td><label>Estoque:</label></td>
					<td><input type="number" name="estoque" value="${produto.estoque }"/></td>
				</tr>
				<tr>
					<td><label>Tipo de Produto:</label></td>
					<td>
					<select name="tipoProduto">
						<c:forEach items="${tipos }" var="t">
							<option <c:if test="${produto.tipoProduto == t }">selected</c:if> value="${t }">${t.toString() }</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><button type="submit">Salvar</button></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>