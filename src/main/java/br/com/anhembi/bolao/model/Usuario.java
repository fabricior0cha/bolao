package br.com.anhembi.bolao.model;

public class Usuario {
	
	private Integer id;
	
	private String email;
	
	private String nome;
	
	private String senha;
	
	private TipoUsuario tipo;

	public Usuario() {
	}
	
	

	public Usuario(Integer id, String email, String nome, String senha, TipoUsuario tipo) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}



	public TipoUsuario getTipo() {
		return tipo;
	}



	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	
}
