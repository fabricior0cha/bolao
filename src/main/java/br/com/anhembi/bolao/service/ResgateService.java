package br.com.anhembi.bolao.service;

import java.util.List;

import br.com.anhembi.bolao.dao.ResgateDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.model.Cupom;

public class ResgateService {
	private final ResgateDAO dao = new ResgateDAO(DBConnection.getConnection());
	
	public List<Cupom> findAll(Integer idParticipante){
		return dao.findAll(idParticipante);
	}
}
