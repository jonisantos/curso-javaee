package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import beans.Usuario;
import connection.SingleConnection;

public class DaoUsuario {
	
	private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
		
	}
	

	public void salvar(Usuario usuario) {
		
		String sql = "insert into usuario(login, senha, nome, telefone, cep, rua, bairro, cidade, estado) values (?, ?, ?, ?,?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getEstado());
			insert.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	public java.util.List<Usuario> listar() throws SQLException{
		java.util.List<Usuario> listar = new ArrayList<Usuario>();
		
		String sql = "select * from usuario";
		
		PreparedStatement statement;
		
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setTelefone(resultSet.getString("telefone"));
				usuario.setCep(resultSet.getString("cep"));
				usuario.setRua(resultSet.getString("Rua"));
				usuario.setBairro(resultSet.getString("bairro"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setEstado(resultSet.getString("estado"));
				listar.add(usuario);
			}
				
		return listar;
	}
	
	public void delete(String id) {
		
		try {
		String sql = "delete from usuario where id = '"+ id + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.execute();
		connection.commit();
		
		}catch(Exception e){
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}


	public Usuario consultar(String id) throws SQLException {
		
		String sql = "select * from usuario where id= '" + id +"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			
			Usuario usuario = new Usuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setTelefone(resultSet.getString("telefone"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setEstado(resultSet.getString("estado"));
			return usuario;
			
		}
		return null;
	}


	public void atualizar(Usuario usuario) {
		
		try {
		String sql = "update usuario set login = ?, senha= ?, nome = ?, telefone= ?, cep= ?, rua = ?, bairro= ?, cidade= ?, estado= ? "
				+ " where id = " + usuario.getId();
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, usuario.getLogin());
		preparedStatement.setString(2, usuario.getSenha());
		preparedStatement.setString(3, usuario.getNome());
		preparedStatement.setString(4, usuario.getTelefone());
		preparedStatement.setString(5, usuario.getCep());
		preparedStatement.setString(6, usuario.getRua());
		preparedStatement.setString(7, usuario.getBairro());
		preparedStatement.setString(8, usuario.getCidade());
		preparedStatement.setString(9, usuario.getEstado());
		preparedStatement.executeUpdate();
		connection.commit();
		
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	}


	public boolean validarLogin(String login) throws SQLException {
		
		String sql = "select count(1) as qtd from usuario where login= '" + login +"'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
	
			
			return resultSet.getInt("qtd") <= 0;
			
		}
		return false;
	}
	
	public boolean validarSenha(String senha) throws SQLException {
		
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";
		
		PreparedStatement smt = connection.prepareStatement(sql);
		ResultSet rset = smt.executeQuery();
		
		
		if(rset.next()) {
			return rset.getInt("qtd") <= 0;
			
		}
		
			return false;
	}



}
