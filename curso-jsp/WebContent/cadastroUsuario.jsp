<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
<link rel="stylesheet" href="resources/css/menu.css">
</head>

    <!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>


<body>
	<div class="menu">
	<a href="acessoliberado.jsp">Inicío</a>
	<a href="cadastroUsuario.jsp">Cadastro de Usuário</a>
	<a href="cadastroProduto.jsp">Cadastro de Produtro</a>
	<a href="cadastroProduto.jsp">Cadastro de Produtro</a>
	</div>
	<center><h1>Cadastro de usuário</h1></center>

	<center><h3 style="color: red;">${msg} </h3></center>
	
	<form action="salvarUsuario" method="post" id="formUser" onsubmit="return validarCampos()? true : false">
		
		<ul class="form-style-1">
			<li>
		<table>
	
			<tr>
				<td>Código:</td>
				<td><input type="text" readonly="readonly" id="id" name="id" value="${user.id}"></td>
			</tr>
			
			<tr>
				<td>Login:</td>
				<td><input type="text" id="login" name="login" value="${user.login }"></td>
			</tr>
			
			<tr>
				<td>Senha:</td>
				<td><input type="password" id="senha" name="senha" value="${user.senha }"></td>
			</tr>
			
			<tr>
				<td>Nome:</td>
				<td><input type="text" id="nome" name="nome" value="${user.nome }"></td>
			</tr>
			
			<tr>
				<td>Telefone:</td>
				<td><input type="text" id="telefone" name="telefone" value="${user.telefone }"></td>
			</tr>
			<tr>
			
			<td>Cep:</td>
				<td><input type="text" id="cep" name="cep" onblur="consultaCep()" value="${user.cep}"></td>
			</tr>
			<td>Rua:</td>
				<td><input type="text" id="rua" name="rua"  value="${user.rua}"></td>
			</tr>
			
			<td>Bairro:</td>
				<td><input type="text" id="bairro" name="bairro" value="${user.bairro}"></td>
			</tr>
			
			<td>Cidade:</td>
				<td><input type="text" id="cidade" name="cidade" value="${user.cidade}"></td>
			</tr>
			
			<td>Estado:</td>
				<td><input type="text" id="uf" name="uf" value="${user.estado}"></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Salvar">
				<input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"> </td>
			</tr>
		</table>
		
		</li>
		</ul>
	</form>
	
	<div class="container">
	<table class="responsive-table">
		<caption>Usuários cadastrados</caption>
		<tr>
			<th>Id</th>
			<th>Login</th>
			<th>Nome</th>
			<th>Telefone</th>
			<th>CEP</th>
			<th>Rua</th>
			<th>Bairro</th>
			<th>Cidade</th>
			<th>Estado</th>
			<th>Delete</th>
			<th>Editar</th>
			<th>Telefone</th>
		
		</tr>
	
	<c:forEach items="${usuarios}" var="user">
	<tr >
	<td style="width: 150px"><c:out value="${user.id }"></c:out></td>
	<td style="width: 150px"><c:out value="${user.login }"></c:out></td>
	<td style="width: 150px"><c:out value="${user.nome }"></c:out></td>
	<td style="width: 150px"><c:out value="${user.telefone }"></c:out></td>
	<td style="width: 150px"><c:out value="${user.cep }"></c:out></td>
	<td style="width: 150px"><c:out value="${user.rua }"></c:out></td>
	<td style="width: 150px"><c:out value="${user.bairro }"></c:out></td>
	<td style="width: 150px"><c:out value="${user.cidade }"></c:out></td>
	<td style="width: 150px"><c:out value="${user.estado }"></c:out></td>
	
	<td style="width: 150px"><a href="salvarUsuario?acao=delete&user=${user.id }">
	<img src="resources/img/excluir.png" alt="Excluir" title="Excluir" whidth="20px" height="20px"></a></td>
	
	<td style="width: 150px"><a href="salvarUsuario?acao=editar&user=${user.id }">
	<img src="resources/img/editar.jpg" alt="Editar" title="Editar" whidth="20px" height="20px"></a></td>
	
	<td style="width: 150px"><a href="salvarTelefones?user=${user.id }">
	<img src="resources/img/telefone.png" alt="Telefones" title="Telefones" whidth="20px" height="20px"></a></td>
	
	
	</tr>
	</c:forEach>
	</table>
	</div>
	
	<script type="text/javascript">
	
	function validarCampos() {
		if(document.getElementById("login").value ==''){
			alert('Informe o Login');
			return false;
			
		}else if(document.getElementById("senha").value ==''){
			alert('Informe a Senha');
			return false;
			
		}else if(document.getElementById("nome").value ==''){
			alert('Informe o Nome');
			return false;
			
		}else if(document.getElementById("telefone").value ==''){
			alert('Informe o Telefone');
			return false;
		}
		return true;
	}
	
		function consultaCep() {
			
			var cep = $("#cep").val();
		
			 // Consulta o Webservice viacep.com.br/
			 $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                 if (!("erro" in dados)) {
                     //Atualiza os campos com os valores da consulta.
                     $("#rua").val(dados.logradouro);
                     $("#bairro").val(dados.bairro);
                     $("#cidade").val(dados.localidade);
                     $("#uf").val(dados.uf);
                  
                     
                 } //end if.
                 else {
                	 
                	 //CEP pesquisado não foi encontrado.
                	 $("#cep").val('');
                     $("#rua").val('');
                     $("#bairro").val('');
                     $("#cidade").val('');
                     $("#estado").val('');
                     alert("CEP não encontrado.");
                 }
             });

		}
	
	</script>
</body>
</html>