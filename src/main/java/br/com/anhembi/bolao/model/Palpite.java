package br.com.anhembi.bolao.model;

import java.util.Date;

public class Palpite {
	private Integer id;

	private Integer resultadoTimeUm;

	private Integer resultadoTimeDois;

	private Date dataCriacao;

	private Participante participante;

	private Bolao bolao;

	public Palpite(Integer id, Integer resultadoTimeUm, Integer resultadoTimeDois, Date dataCriacao,
			Participante participante, Bolao bolao) {

		this.id = id;
		this.resultadoTimeUm = resultadoTimeUm;
		this.resultadoTimeDois = resultadoTimeDois;
		this.dataCriacao = dataCriacao;
		this.participante = participante;
		this.bolao = bolao;
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
	}

}
