package br.com.anhembi.bolao.model.dto;

import java.io.Serializable;

public class ResgateDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idParticipante;
	
	private Integer idCupom;

	public ResgateDTO() {
	}

	public ResgateDTO(Integer idParticipante, Integer idCupom) {
		this.idParticipante = idParticipante;
		this.idCupom = idCupom;
	}

	public Integer getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Integer idParticipante) {
		this.idParticipante = idParticipante;
	}

	public Integer getIdCupom() {
		return idCupom;
	}

	public void setIdCupom(Integer idCupom) {
		this.idCupom = idCupom;
	}
	
	
}
