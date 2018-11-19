package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Telefone;
import connection.SingleConnection;

public class DaoTelefone {
	
	private Connection connection;
	
	public DaoTelefone() {
		
	connection = SingleConnection.getConnection();
	
	}
	

	public void salvar(Telefone telefone) {
		
		String sql = "insert into telefone(numero, tipo, usuario) values (?, ?, ?)";
		
		try {
			
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			
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
	
	public java.util.List<Telefone> listar(Long user) throws SQLException{
		java.util.List<Telefone> listar = new ArrayList<Telefone>();
		
		String sql = "select * from telefone where usuario = " + user;
		
		PreparedStatement statement;
		
			statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Telefone telefone = new Telefone();
				telefone.setId(resultSet.getLong("id"));
				telefone.setNumero(resultSet.getString("numero"));
				telefone.setTipo(resultSet.getString("tipo"));
				telefone.setUsuario(resultSet.getLong("usuario"));
				listar.add(telefone);
			}
				
		return listar;
	}
	
	public void delete(String id) {
		
		try {
		String sql = "delete from telefone where id = '"+ id + "'";
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

}
