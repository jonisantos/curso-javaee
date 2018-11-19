 <jsp:useBean id="calcula"  class="beans.Usuario" type="beans.Usuario" scope="page"/> 
 
 <jsp:useBean id="produto" class="beans.Produto" type="beans.Produto" scope="page" />
 	
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro</title>
<link rel="stylesheet" href="resources/css/menu.css">
</head> 

<body>
	<div class="menu">
	<a href="acessoliberado.jsp">Inicío</a>
	<a href="cadastroUsuario.jsp">Cadastro de Usuário</a>
	<a href="cadastroProduto.jsp">Cadastro de Produtro</a>
	<a href="index.jsp">Sair</a>
	</div>
	<jsp:setProperty property="*" name="calcula"/>  
	<jsp:setProperty property="*" name="produto"/>
	
	<h3>Seja bem vindo ao sistema em jsp</h3>
	
	<a href="salvarUsuario?acao=listartodos ">
	
	<img src="resources/img/pessoa.png" alt="cadastro usuario" title="Cadastro de Usuário" whidth="100px" height="100px">
	
	</a>
	
	<a href="salvarProduto?acao=listartodos">
	
	<img src="resources/img/produto.png" alt="cadastro de Produto" title="Cadastro de Produto" whidth="100px" height="100px">
	
	</a>
	
	</body>
</html>