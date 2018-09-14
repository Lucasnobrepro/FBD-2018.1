package POJO;

public class Musica {
	private int id;
	private String nome;
	private String cantor;
	private String genero;
	
	public Musica(String nome, String cantor, String genero) {
		super();
		
		this.nome = nome;
		this.cantor = cantor;
		this.genero = genero;
	}
	
	public Musica(int id,String nome, String cantor, String genero) {
		super();
		this.id = id;
		this.nome = nome;
		this.cantor = cantor;
		this.genero = genero;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCantor() {
		return cantor;
	}
	public void setCantor(String cantor) {
		this.cantor = cantor;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
}
