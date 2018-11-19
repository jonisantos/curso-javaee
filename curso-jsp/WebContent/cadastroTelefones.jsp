<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Telefones</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
<link rel="stylesheet" href="resources/css/menu.css">
</head>


<body>
	<div class="menu">
	<a href="acessoliberado.jsp">Inicío</a>
	<a href="cadastroUsuario.jsp">Cadastro de Usuário</a>
	<a href="cadastroProduto.jsp">Cadastro de Produtro</a>
	<a href="cadastroProduto.jsp">Cadastro de Produtro</a>
	</div>
	<center><h1>Cadastro de Telefones</h1></center>

	<center><h3 style="color: red;">${msg} </h3></center>
	
	<form action="salvarTelefones" method="post" id="formFone" onsubmit="return validarCampos()? true : false">
		
		<ul class="form-style-1">
			<li>
		<table>
			
			<tr>
				<td>User:</td>
				
				<td><input type="text" readonly="readonly" id="id" name="id" class="field-Long" value="${userEscolhido.id}"></td>
				
				<td><input type="text" readonly="readonly" id="nome" name="nome" class="field-Long" value="${userEscolhido.nome}"></td>
			</tr>
			
			<tr>
				<td>Código:</td>
				<td><input type="text" readonly="readonly" id="id" name="id"></td>
			</tr>
			
			<tr>
				<td>Número:</td>
				<td><input type="text" id="numero" name="numero" ></td>
				<td><select id="tipo" name="tipo" >
				<option>Casa</option>
				<option>Contato</option>
				<option>Celular</option>
				</select>
				</td>
			</tr>
			
			
			
					
			<tr>
			
				<td><input type="submit" value="Salvar">
				
				</td>
			</tr>
		</table>
		
		</li>
		</ul>
	</form>
	
	<div class="container">
	<table class="responsive-table">
		<caption>Telefone</caption>
		<tr>
			<th>Id</th>
			<th>Número</th>
			<th>Tipo</th>			
			<th>Excluir</th>
		</tr>
	
	<c:forEach items="${telefones}" var="fone">
	<tr >
	<td style="width: 150px"><c:out value="${fone.id }"></c:out></td>
	<td style="width: 150px"><c:out value="${fone.numero }"></c:out></td>
	<td style="width: 150px"><c:out value="${fone.tipo }"></c:out></td>

	
	
	<td style="width: 150px"><a href="salvarTelefones?acao=delete&fone=${fone.id }">
	<img src="resources/img/excluir.png" alt="Excluir" title="Excluir" whidth="20px" height="20px"></a></td>
	
	
	
	</tr>
	</c:forEach>
	</table>
	</div>
	
	<script type="text/javascript">
	
	function validarCampos() {
		
		if(document.getElementById("numero").value ==''){
			alert('Informe o Número');
			return false;
			
		}else
		if(document.getElementById("tipo").value ==''){
			alert('Informe o Tipo');
			return false;
			
		}
		return true;
	}
	
		
	
	</script>
</body>
</html>