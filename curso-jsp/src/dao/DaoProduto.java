package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Produto;

import connection.SingleConnection;

public class DaoProduto {
	
	private Connection connection;
	
	public DaoProduto() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void salvarProduto(Produto produto) {
		
		
		
		try {
			String sql = " insert into produto(nome, quantidade, valor) values (?, ?, ?)";
			PreparedStatement inserirProduto = connection.prepareStatement(sql);
			
			inserirProduto.setString(1, produto.getNome());
			inserirProduto.setDouble(2, produto.getQuantidade());
			inserirProduto.setDouble(3, produto.getValor());
			
			inserirProduto.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				
				connection.rollback();
			} catch (SQLException e1) {
				
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
		}
	}
	
		public List<Produto> listarProduto() throws SQLException{
			
			List<Produto> listar = new ArrayList<Produto>();
			
			String sql = "select * from produto";
			
			PreparedStatement stm = connection.prepareStatement(sql);
			
			ResultSet resultSet = stm.executeQuery();
			
			while (resultSet.next()) {
				
			 Produto produto = new Produto();
			 
			 produto.setId(resultSet.getLong("id"));
			 produto.setNome(resultSet.getString("nome"));
			 produto.setQuantidade(resultSet.getDouble("quantidade"));
			 produto.setValor(resultSet.getDouble("valor"));
			 
			 listar.add(produto);
			}
			return listar;
			
		}
		
		public void deletarProduto(String id) {
			
			try {

				String sql = "delete from produto where id = '"+id + "'";
				PreparedStatement stm = connection.prepareStatement(sql);
				stm.execute();
				connection.commit();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
				try {
					connection.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
			
		}
		
		public Produto consultarProduto(String id) throws SQLException {
			
			String sql = "select * from produto where id = '"+ id +"'";
			PreparedStatement stm = connection.prepareStatement(sql);
			
			ResultSet resultSet = stm.executeQuery();
			
			if(resultSet.next()) {
				Produto produto = new Produto();
				 produto.setId(resultSet.getLong("id"));
				 produto.setNome(resultSet.getString("nome"));
				 produto.setQuantidade(resultSet.getDouble("quantidade"));
				 produto.setValor(resultSet.getDouble("valor"));
				 
				 return produto;
			}
			
			return null;
			
		}
		
		public void atualizarProduto(Produto produto) {
			
			
			try {
				
				String sql = "update produto set nome = ?, quantidade = ?, valor = ? where id = " + produto.getId();
				PreparedStatement inserirProduto = connection.prepareStatement(sql);
				
				inserirProduto.setString(1, produto.getNome());
				inserirProduto.setDouble(2, produto.getQuantidade());
				inserirProduto.setDouble(3, produto.getValor());
				
				inserirProduto.executeUpdate();
				connection.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				try {
					
					connection.rollback();
				} catch (SQLException e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		}
		
		public boolean validarProduto(String nome) throws SQLException {
			
			String sql = "select count(1) as qtd from produto where nome = '" + nome + "'";
			
			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet resultSet = stm.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getInt("qtd") <= 0;
			}
			
			return false;
			
		}
		
}
