package br.com.anhembi.bolao.model;

public class Participante {

	private Integer id;
	
	private Usuario usuario;
	
	private Bolao bolao;
	
	private Boolean vencedor;
	
	public Participante() {
	
	}

	public Participante(Integer id, Usuario usuario, Bolao bolao, Boolean vencedor) {
		this.id = id;
		this.usuario = usuario;
		this.bolao = bolao;
		this.vencedor = vencedor;
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

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
	}

	public Boolean getVencedor() {
		return vencedor;
	}

	public void setVencedor(Boolean vencedor) {
		this.vencedor = vencedor;
	}
	
}
