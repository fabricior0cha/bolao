package br.com.anhembi.bolao.service;

import br.com.anhembi.bolao.dao.BolaoDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.model.Bolao;

public class BolaoService {

	private final BolaoDAO dao = new BolaoDAO(DBConnection.getConnection());

	public void insert(Bolao bolao) {
		dao.insert(bolao);
	}

	public void update(Bolao bolao) {
		dao.update(bolao);
	}

	public Bolao findById(Integer id) throws NotFoundException {
		Bolao bolao = dao.findById(id);

		if (bolao == null) {
			throw new NotFoundException("Bolão não encontrado com id: " + id);
		}

		return bolao;
	}

	public void delete(Integer id) {
		dao.delete(id);
	}
}
