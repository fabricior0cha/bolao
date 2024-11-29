package br.com.anhembi.bolao.model;

public class Participante {

	private Integer id;
	
	private Usuario usuario;
	
	private Integer pontos;
	
	public Participante() {
	
	}

	public Participante(Integer id, Usuario usuario, Integer pontos) {
		this.id = id;
		this.usuario = usuario;
		this.pontos = pontos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	
}
