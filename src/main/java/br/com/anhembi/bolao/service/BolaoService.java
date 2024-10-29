package br.com.anhembi.bolao.service;

import br.com.anhembi.bolao.dao.BolaoDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.model.Bolao;

public class BolaoService  {

	private final BolaoDAO dao = new BolaoDAO(DBConnection.getConnection());
	
	public void insert(Bolao bolao) {
		dao.insert(bolao);
	}
}
