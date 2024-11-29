package br.com.anhembi.bolao.model.dto;

import java.io.Serializable;

public class PalpiteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer idParticipante;
	
	private Integer idJogo;
	
	private Boolean vencedor;
	
	private Integer resultadoTimeUm;
	
	private Integer resultadoTimeDois;

	public PalpiteDTO(Integer id, Integer idParticipante, Integer idJogo, Boolean vencedor, Integer resultadoTimeUm,
			Integer resultadoTimeDois) {
		this.id = id;
		this.idParticipante = idParticipante;
		this.idJogo = idJogo;
		this.vencedor = vencedor;
		this.resultadoTimeUm = resultadoTimeUm;
		this.resultadoTimeDois = resultadoTimeDois;
	}

	public PalpiteDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Integer idParticipante) {
		this.idParticipante = idParticipante;
	}

	public Integer getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Integer idJogo) {
		this.idJogo = idJogo;
	}

	public Boolean getVencedor() {
		return vencedor;
	}

	public void setVencedor(Boolean vencedor) {
		this.vencedor = vencedor;
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
	
	
}
