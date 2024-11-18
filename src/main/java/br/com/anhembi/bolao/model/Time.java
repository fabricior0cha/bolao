package br.com.anhembi.bolao.model;

public class Time {

	private Integer id;

	private String nome;

	private String urlEmblema;

	private String codigo;

	public Time() {

	}

	public Time(Integer id, String nome, String urlEmblema, String codigo) {
		this.id = id;
		this.nome = nome;
		this.urlEmblema = urlEmblema;
		this.codigo = codigo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrlEmblema() {
		return urlEmblema;
	}

	public void setUrlEmblema(String urlEmblema) {
		this.urlEmblema = urlEmblema;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	

}