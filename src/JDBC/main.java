package JDBC;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.MusicaDAO;
import DAO.PlayListDAO;
import DAO.UsuarioDAO;
import POJO.Musica;
import POJO.Usuario;

public class main {

	private static Scanner sc;

	public static void main(String[] argv){
	
	sc = new Scanner(System.in);
	
	PlayListDAO playlistDAO = new PlayListDAO();
	MusicaDAO musicaDAO = new MusicaDAO();
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	//Adicionar musica;
	//Adicionar usuario;
	//Escultar Musica;
	//Recomendar Musicas apatir de genero;
	
	
	String opcao = "i";
		while(opcao != "exit"){
			
			//Menu;
			System.out.println("---------------------------------------");
			System.out.println("AddUser -nome -cpf");
			System.out.println("AddMusic -nome -cantor -genero");
			System.out.println("Ouvir -nome idUser");
			System.out.println("Sugestao -genero");
			System.out.println("Delete -id");
			System.out.println("Update -id * -nome -cantor -genero");
			System.out.println("---------------------------------------");
			opcao = sc.next();
			
			//Funciona;
			if(opcao.equals("AddUser")){
				System.out.println("-nome");
				//Pegar os atributos para o Usuario;
				String nome = sc.next();
				//String cpf = sc.nextLine();
				//Adiciona um usuario na tabela de Usuario;
				usuarioDAO.addUsuario(new Usuario(nome));
			}
			//Funciona;
			if(opcao.equals("AddMusic")){
				System.out.println("-nome -cantor -genero");
				//Pegar os atributos para a Musica;
				String nomeM = sc.next();
				String cantor = sc.next();
				String genero = sc.next();
				//Adiciona uma musica na tabela de musica;
				musicaDAO.addMusica(new Musica(nomeM,cantor, genero));
			}
			//Funciona;
			if(opcao.equals("Ouvir")){
				System.out.println("-nomeM -NomeUser");
				String Mnome = sc.next();
				String Unome = sc.next();
				
				
				//Essa função me retorna uma Musica, essa Musica eu irei guarda em musicaouvida;
				Musica musicaouvida = musicaDAO.ouvirMusica(Mnome);
				System.out.println("Voce escultou: " + musicaouvida.getNome());
				//Vou adicionar musicaouvida na tabela playlist do usuario que o id corresponder;
				playlistDAO.addMusica(musicaouvida, usuarioDAO.getUsuarioNome(Unome).getId());
				
			}
			
			/*Funcionando pacialmente falta fazer a comparação na playList pra 
			 * saber se usuario já ouviu a musica;
		     */
			if(opcao.equals("Sugestao")){
				System.out.println("-genero -IdUsuari" +
						"o");
				String genero1 = sc.next();
				int genero2 = sc.nextInt();
				ArrayList <Musica> list = musicaDAO.recomendarMusica(genero1 ,genero2);
				for(Musica Musc : list){
					System.out.println("--" +Musc.getNome());
				}
			}
			if(opcao.equals("Delete")){
				System.out.println("-id");
				
				int idDelete = sc.nextInt();
				playlistDAO.excluirUsuario(idDelete);
				musicaDAO.excluirUsuario(idDelete);
				
			}
			if(opcao.equals("Update")){
				System.out.println(" -id * -nome -cantor -genero");
				int idUP = sc.nextInt();
				
				String nomeM = sc.next();
				String cantor = sc.next();
				String genero = sc.next();
				//Adiciona uma musica na tabela de musica;
				System.out.println("Musica ID: "+musicaDAO.updateMusica(new Musica(nomeM,cantor,genero), idUP) + " foi atualizada");
			}
		}
	}
}
