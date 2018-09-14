package DAO;

import java.sql.*;
import java.util.ArrayList;

import JDBC.ConnectionFactory;
import POJO.Musica;

public class PlayListDAO {
	private Connection connection;

	public PlayListDAO(){
		
	}
	public boolean excluirUsuario(int idDelete) {
		String sql = "DELETE FROM playlist WHERE idMusc = ?";
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

	
	//Para adicionar musica;
	public boolean addMusica(Musica musica, int id) {
		String sql = "INSERT INTO playList (idUser, idMusc) VALUES (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			//Necessário para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//Set values
			stmt.setInt(1, id);
			stmt.setInt(2, musica.getId());
			
			
			int affectedRows = stmt.executeUpdate();
			stmt.close();
			
			if(affectedRows > 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally{
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public ArrayList<Musica> getMusica(String genero){
		String sql = "SELECT * FROM musica where genero = ?";
		ArrayList<Musica> listMusica = new ArrayList<Musica>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			/*ResultSet é uma interface utilizada pra guardar
			 * dados vindos de um banco de dados.
			*Basicamente, ela guarda o resultado de uma pesquisa
			*numa estrutura de dados que pode ser percorrida,
			*de forma que você possa ler os dados do banco. */
			
			stmt.setString(1,genero);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String nome = rs.getString("nome");
				String cantor = rs.getString("cantor");
				String genero1 = rs.getString("genero");
				
				Musica musica = new Musica(nome, cantor, genero1);
				
				listMusica.add(musica);
			}
			stmt.close();
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return listMusica;
	}
	
	//Função para DELETAR;
	
	
	
	//FUNÇÃO PARA PEGAR UMA UNICA MUSICA;
		//--Essa função vai retorna o nome da musica para um array da play list;
		//--Irei pegar o ID do usuario e procurar sua playList
		//--Add a musica a playList
	public Musica getMusicaById(String nome) {
		String sql = "SELECT * FROM musica WHERE nome = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nome);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Musica musica = new Musica(rs.getInt("id"),rs.getString("nome"), rs.getString("cantor"), rs.getString("genero"));
			
			stmt.close();
			
			return musica;
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



