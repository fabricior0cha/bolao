package br.com.anhembi.bolao.service;

import java.util.List;

import br.com.anhembi.bolao.dao.TimeDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.model.Time;

public class TimeService {

	private final TimeDAO dao = new TimeDAO(DBConnection.getConnection());


	public Time findById(Integer id) throws NotFoundException {
		Time time = dao.findById(id);

		if (time == null) {
			throw new NotFoundException("Nenhum time encontrado com id: " + id);
		}

		return time;
	}
	
	public List<Time> findByAll(String nome){
		
		return dao.findByAll(nome);
	}
}
