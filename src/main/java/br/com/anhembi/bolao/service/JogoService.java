package br.com.anhembi.bolao.service;

import java.util.List;

import br.com.anhembi.bolao.dao.JogoDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Jogo;

public class JogoService {
	
	private final JogoDAO dao = new JogoDAO(DBConnection.getConnection());
	
	public Integer insert(Jogo jogo) throws BadRequestException {
		try {
			return dao.insert(jogo);
		} catch (SQLProcedureException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	public void update(Jogo jogo) throws BadRequestException {
		try {
			dao.insert(jogo);
		} catch (SQLProcedureException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	public Jogo findById(Integer id) throws NotFoundException {
		Jogo jogo = dao.findById(id);

		if (jogo == null) {
			throw new NotFoundException("Jogo não encontrado com id: " + id);
		}

		return jogo;
	}
	
	public Jogo findByBolaoId(Integer bolaoId) throws NotFoundException {
		Jogo jogo = dao.findByBolaoId(bolaoId);

		if (jogo == null) {
			throw new NotFoundException("Jogo não encontrado com id do bolão: " + bolaoId);
		}

		return jogo;
	}
	
	public List<Jogo> findAll() {
		List<Jogo> jogos = dao.findAll();

		return jogos;
	}
}
