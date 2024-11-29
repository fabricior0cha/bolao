package br.com.anhembi.bolao.model;

import java.util.Date;

public class Cupom {

	private Integer id;
	
	private Integer pontos;
	
	private Integer desconto;
	
	private Date dataVencimento;
	
	private String codigo;
	
	private Loja loja;
	
	
	public Cupom() {
	}


	public Cupom(Integer id, Integer pontos, Integer desconto, Date dataVencimento, String codigo, Loja loja) {
		this.id = id;
		this.pontos = pontos;
		this.desconto = desconto;
		this.dataVencimento = dataVencimento;
		this.codigo = codigo;
		this.loja = loja;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getPontos() {
		return pontos;
	}


	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}


	public Integer getDesconto() {
		return desconto;
	}


	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}


	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	


	public Loja getLoja() {
		return loja;
	}


	public void setLoja(Loja loja) {
		this.loja = loja;
	}


	
	
}
