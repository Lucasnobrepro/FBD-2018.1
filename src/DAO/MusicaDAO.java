package DAO;

import java.sql.*;
import java.util.ArrayList;

import JDBC.ConnectionFactory;
import POJO.Musica;


public class MusicaDAO {
	private Connection connection;
	
	public MusicaDAO(){
		
	}

	public String updateMusica(Musica musc, int idUP) {
		String sql = "UPDATE Musica set nome = ?, cantor = ?, genero = ? where id = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, musc.getNome());
			stmt.setString(2, musc.getCantor());
			stmt.setString(3, musc.getGenero());
			stmt.setInt(4, idUP);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			stmt.close();
			
			return musc.getNome();
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
	//Para adicionar musica;
//	public boolean addMusica(Musica musica) {
//		String sql = "INSERT INTO Musica (nome, cantor,genero) VALUES (?, ?, ?)";
//		this.connection = new ConnectionFactory().getConnection();
//		
//		try {
//			//Necessário para inserção
//			PreparedStatement stmt = connection.prepareStatement(sql);
//			
//			//Set values
//			stmt.setString(1, musica.getNome());
//			stmt.setString(2, musica.getCantor());
//			stmt.setString(3, musica.getGenero());
//			
//			int affectedRows = stmt.executeUpdate();
//			stmt.close();
//			
//			if(affectedRows > 0) {
//				return true;
//			}
//			return false;
//		}catch(SQLException e) {
//			System.err.println(e.getMessage());
//		}finally{
//			try {
//				this.connection.close();
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
	public boolean addMusica(Musica musica) {
	String sql = "INSERT INTO Musica (nome, cantor,genero) VALUES (?, ?, ?)";
	this.connection = new ConnectionFactory().getConnection();
	boolean continua = false;
	//sc = new Scanner(System.in);
	while(continua == false){

	try {
		//Necessário para inserção
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		//Set values
		stmt.setString(1, musica.getNome());
		stmt.setString(2, musica.getCantor());
		stmt.setString(3, musica.getGenero());
		
		int affectedRows = stmt.executeUpdate();
		stmt.close();
		continua = true;
		if(affectedRows > 0) {
			return true;
		}
		return false;
	}catch(SQLException e) {
		continua = false;
		System.out.println(e);
	
		
	}finally{
		try {
			this.connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	}
	return false;
}
	public ArrayList<Musica> recomendarMusica(String genero,int genero2){
	String sql = "SELECT * FROM Musica m WHERE NOT EXISTS (SELECT * FROM playList p, Musica m1 WHERE m1.id = p.idMusc and m.id = p.idMusc AND p.IdUser = ?) AND m.genero = ?";
//	String sql = "SELECT * FROM Musica where genero = ?";
	ArrayList<Musica> listMusica = new ArrayList<Musica>();
	this.connection = new ConnectionFactory().getConnection();
	boolean continua = false;
	//sc = new Scanner(System.in);
	while(continua == false){
	
	try {
		PreparedStatement stmt = connection.prepareStatement(sql);
		/*ResultSet é uma interface utilizada pra guardar
		 * dados vindos de um banco de dados.
		*Basicamente, ela guarda o resultado de uma pesquisa
		*numa estrutura de dados que pode ser percorrida,
		*de forma que você possa ler os dados do banco. */
		
		stmt.setInt(1,genero2);
		stmt.setString(2,genero);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			String nome = rs.getString("nome");
			String cantor = rs.getString("cantor");
			String genero1 = rs.getString("genero");
			
			Musica musica = new Musica(nome, cantor, genero1);
			
			listMusica.add(musica);
		}
		continua = true;
		stmt.close();
	}catch(SQLException e) {
		continua = false;
		System.err.println(e.getMessage());
	}finally {
		try {
			this.connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	}
	return listMusica;
}
//	public ArrayList<Musica> recomendarMusica(String genero){
//		String sql = "SELECT * FROM Musica m WHERE NOT EXISTS (SELECT * FROM playList p, Musica m1 WHERE m1.id = p.idMusc and m.id = p.idMusc) AND m.genero = ? ";
////		String sql = "SELECT * FROM Musica where genero = ?";
//		ArrayList<Musica> listMusica = new ArrayList<Musica>();
//		this.connection = new ConnectionFactory().getConnection();
//		
//		try {
//			PreparedStatement stmt = connection.prepareStatement(sql);
//			/*ResultSet é uma interface utilizada pra guardar
//			 * dados vindos de um banco de dados.
//			*Basicamente, ela guarda o resultado de uma pesquisa
//			*numa estrutura de dados que pode ser percorrida,
//			*de forma que você possa ler os dados do banco. */
//			
//			stmt.setString(1,genero);
//			ResultSet rs = stmt.executeQuery();
//			while(rs.next()) {
//				String nome = rs.getString("nome");
//				String cantor = rs.getString("cantor");
//				String genero1 = rs.getString("genero");
//				
//				Musica musica = new Musica(nome, cantor, genero1);
//				
//				listMusica.add(musica);
//			}
//			stmt.close();
//		}catch(SQLException e) {
//			System.err.println(e.getMessage());
//		}finally {
//			try {
//				this.connection.close();
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return listMusica;
//	}
	
	//Função para DELETAR;
	
	public boolean excluirUsuario(int idDelete) {
		String sql = "DELETE FROM Musica WHERE id = ?";
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
	
	//FUNÇÃO PARA PEGAR UMA UNICA MUSICA;
		//--Essa função vai retorna o nome da musica para um array da play list;
		//--Irei pegar o ID do usuario e procurar sua playList
		//--Add a musica a playList
	public Musica ouvirMusica(String nome) {
		String sql = "SELECT * FROM Musica M WHERE M.nome = ?";
		
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
