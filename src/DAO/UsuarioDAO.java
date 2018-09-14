package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.ConnectionFactory;
import POJO.Musica;
import POJO.Usuario;

public class UsuarioDAO {
	private Connection connection;
	
	public void USuarioDAO(){

	}
	 //Função falta fechar coneção.
	public void addUsuario(Usuario usuario){
		String sql = "insert into usuario (nome) values (?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome());
			
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public boolean excluirUsuario(int idDelete) {
		String sql = "DELETE FROM Usuario WHERE id = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Setar valores
			stmt.setInt(1, idDelete);
			
			int affectedRows = stmt.executeUpdate();
			stmt.close();
			
			if(affectedRows > 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
	
	public Usuario getUsuarioNome(String nome) {
		String sql = "SELECT * FROM Usuario U WHERE U.nome = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nome);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Usuario usuario = new Usuario(rs.getString("nome"),rs.getInt("id"));
			
			stmt.close();
			
			return usuario;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public String getUsuario(int id) {
		String sql = "SELECT * FROM Usuario U WHERE U.id = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			String usuario = rs.getString("nome");
			
			stmt.close();
			
			return usuario;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}










