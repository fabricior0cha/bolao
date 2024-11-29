package br.com.anhembi.bolao.service;

import java.util.List;

import br.com.anhembi.bolao.dao.JogoDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Jogo;
import br.com.anhembi.bolao.model.Time;

public class JogoService {
	
	private final JogoDAO dao = new JogoDAO(DBConnection.getConnection());
	
	private final ParticipanteService participanteService = new ParticipanteService();
	
	public Integer insert(Jogo jogo) throws BadRequestException {
		Integer idJogo = null;
		try {
			idJogo = dao.insert(jogo);
			if(jogo.getId() != null) {
				participanteService.updatePontos(jogo.getId());
			}
		} catch (SQLProcedureException e) {
			throw new BadRequestException(e.getMessage());
		}
		
		return idJogo;
	}
	
	public List<Jogo> findByParticipante(Integer participanteId){
		return dao.findByParticipante(participanteId);
	}
	
	public List<Jogo> findAll(){
		return dao.findAll();
	}
	
	public Jogo findById(Integer id) throws NotFoundException {
		Jogo jogo = dao.findById(id);

		if (jogo == null) {
			throw new NotFoundException("Nenhum jogo encontrado com id: " + id);
		}

		return jogo;
	}
}
