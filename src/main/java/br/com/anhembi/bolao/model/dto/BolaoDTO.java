package br.com.anhembi.bolao.model.dto;

import java.io.Serializable;
import java.util.Date;

public class BolaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date dataJogo;
	private Integer idTimeUm;
	private Integer idTimeDois;
	private String titulo;
	private Double premio;
	private Integer totalTimeUm;
	private Integer totalTimeDois;

	

	public BolaoDTO(Integer id, Date dataJogo, Integer idTimeUm, Integer idTimeDois, String titulo, Double premio,
			Integer totalTimeUm, Integer totalTimeDois) {
		this.id = id;
		this.dataJogo = dataJogo;
		this.idTimeUm = idTimeUm;
		this.idTimeDois = idTimeDois;
		this.titulo = titulo;
		this.premio = premio;
		this.totalTimeUm = totalTimeUm;
		this.totalTimeDois = totalTimeDois;
	}

	public BolaoDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataJogo() {
		return dataJogo;
	}

	public void setDataJogo(Date dataJogo) {
		this.dataJogo = dataJogo;
	}

	public Integer getIdTimeUm() {
		return idTimeUm;
	}

	public void setIdTimeUm(Integer idTimeUm) {
		this.idTimeUm = idTimeUm;
	}

	public Integer getIdTimeDois() {
		return idTimeDois;
	}

	public void setIdTimeDois(Integer idTimeDois) {
		this.idTimeDois = idTimeDois;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getPremio() {
		return premio;
	}

	public void setPremio(Double premio) {
		this.premio = premio;
	}

	public Integer getTotalTimeUm() {
		return totalTimeUm;
	}

	public void setTotalTimeUm(Integer totalTimeUm) {
		this.totalTimeUm = totalTimeUm;
	}

	public Integer getTotalTimeDois() {
		return totalTimeDois;
	}

	public void setTotalTimeDois(Integer totalTimeDois) {
		this.totalTimeDois = totalTimeDois;
	}

	
}
