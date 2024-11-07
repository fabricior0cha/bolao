package br.com.anhembi.bolao.model;

import java.util.Date;

public class Jogo {

	private Integer id;
	
	private Date data;
	
	private Time timeUm;
	
	private Time timeDois;
	
	private Bolao bolao;
	
	private Integer totalTimeUm;
	
	private Integer totalTimeDois;
	
	public Jogo() {
	}

	public Jogo(Integer id, Date data, Time timeUm, Time timeDois, Bolao bolao, Integer totalTimeUm,
			Integer totalTimeDois) {
		super();
		this.id = id;
		this.data = data;
		this.timeUm = timeUm;
		this.timeDois = timeDois;
		this.bolao = bolao;
		this.totalTimeUm = totalTimeUm;
		this.totalTimeDois = totalTimeDois;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getTimeUm() {
		return timeUm;
	}

	public void setTimeUm(Time timeUm) {
		this.timeUm = timeUm;
	}

	public Time getTimeDois() {
		return timeDois;
	}

	public void setTimeDois(Time timeDois) {
		this.timeDois = timeDois;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
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
