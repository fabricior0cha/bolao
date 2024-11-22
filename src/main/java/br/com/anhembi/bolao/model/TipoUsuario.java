package br.com.anhembi.bolao.model;

public class TipoUsuario {
	
	private Integer id;
	
	private String codigo;

	public TipoUsuario(Integer id, String codigo) {
		this.id = id;
		this.codigo = codigo;
	}

	public TipoUsuario() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
