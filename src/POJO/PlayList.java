package POJO;

import java.util.ArrayList;

public class PlayList {
	ArrayList<Musica> Ouvidas;
	int idUser;
	
	public PlayList(Usuario user){
		this.idUser = user.getId();
	}
	
	public PlayList(int cpf) {
		this.idUser = cpf;
	}

	public ArrayList<Musica> getOuvidas() {
		return Ouvidas;
	}

	public void setOuvidas(Musica musica) {
		Ouvidas.add(musica);
	}

	public int getCpf() {
		return idUser;
	}
	

}
