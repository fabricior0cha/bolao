package br.com.anhembi.bolao.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Jogo {

	private Integer id;
	
	private LocalDateTime horario;
	
	private Time timeUm;
	
	private Time timeDois;
	
	private Integer totalTimeUm;
	
	private Integer totalTimeDois;
	
	public Jogo() {
	}

	public Jogo(Integer id, LocalDateTime horario, Time timeUm, Time timeDois, Integer totalTimeUm,
			Integer totalTimeDois) {
		super();
		this.id = id;
		this.horario = horario;
		this.timeUm = timeUm;
		this.timeDois = timeDois;
		this.totalTimeUm = totalTimeUm;
		this.totalTimeDois = totalTimeDois;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
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
