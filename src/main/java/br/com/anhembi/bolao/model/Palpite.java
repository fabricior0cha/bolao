package br.com.anhembi.bolao.model;

import java.util.Date;

public class Palpite {
	private Integer id;

	private Integer resultadoTimeUm;

	private Integer resultadoTimeDois;

	private Participante participante;

	private Jogo jogo;

	public Palpite(Integer id, Integer resultadoTimeUm, Integer resultadoTimeDois,
			Participante participante, Jogo jogo) {

		this.id = id;
		this.resultadoTimeUm = resultadoTimeUm;
		this.resultadoTimeDois = resultadoTimeDois;
		this.participante = participante;
		this.jogo = jogo;
	}

	public Palpite() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getResultadoTimeUm() {
		return resultadoTimeUm;
	}

	public void setResultadoTimeUm(Integer resultadoTimeUm) {
		this.resultadoTimeUm = resultadoTimeUm;
	}

	public Integer getResultadoTimeDois() {
		return resultadoTimeDois;
	}

	public void setResultadoTimeDois(Integer resultadoTimeDois) {
		this.resultadoTimeDois = resultadoTimeDois;
	}

	

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	

}
