package br.com.anhembi.bolao.model;
import java.util.Date;

public class Bolao {
	
	private Integer id;
	
	private String titulo;
	
	private Date dataCriacao;
	
	private Double premio;
	
	
	public Bolao() {
		
	}

	public Bolao(Integer id, String titulo, Date dataCriacao, Double premio) {
		this.id = id;
		this.titulo = titulo;
		this.dataCriacao = dataCriacao;
		this.premio = premio;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Double getPremio() {
		return premio;
	}

	public void setPremio(Double premio) {
		this.premio = premio;
	}

	
	
	
}
