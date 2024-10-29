package br.com.anhembi.bolao.model;
import java.util.Date;

public class Bolao {
	
	private Integer id;
	
	private String titulo;
	
	private String codigo;
	
	private Date dataCriacao = null;
	
	private Double premio;
	
	public Bolao(Integer id, String titulo, String codigo, Date dataCriacao, Double premio) {
		this.id = id;
		this.titulo = titulo;
		this.codigo = codigo;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
