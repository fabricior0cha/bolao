package br.com.anhembi.bolao.model.dto;

import java.io.Serializable;

public class PalpiteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer idUsuario;
	
	private Integer idBolao;
	
	private Boolean vencedor;
	
	private Integer resultadoTimeUm;
	
	private Integer resultadoTimeDois;

	public PalpiteDTO(Integer id, Integer idUsuario, Integer idBolao, Boolean vencedor, Integer resultadoTimeUm,
			Integer resultadoTimeDois) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.idBolao = idBolao;
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

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdBolao() {
		return idBolao;
	}

	public void setIdBolao(Integer idBolao) {
		this.idBolao = idBolao;
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
