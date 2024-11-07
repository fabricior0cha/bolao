package br.com.anhembi.bolao.model;

public class Time {
	
	private Integer id;
	
	private String nome;
	
	private String urlEmblema;
	
public Time () {
	
}

public Time (Integer id, String nome, String urlEmblema) {
	this.id = id;
	this.nome = nome;
	this.urlEmblema = urlEmblema;
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

}