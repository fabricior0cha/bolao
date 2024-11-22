package br.com.anhembi.bolao.service;

import br.com.anhembi.bolao.dao.JogoDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Jogo;

public class JogoService {
	
	private final JogoDAO dao = new JogoDAO(DBConnection.getConnection());
	
	private final ParticipanteService participanteService = new ParticipanteService();
	
	public Integer insert(Jogo jogo) throws BadRequestException {
		Integer idJogo = null;
		try {
			idJogo = dao.insert(jogo);
			if(jogo.getId() != null) {
				participanteService.updateVencedor(jogo.getId());
			}
		} catch (SQLProcedureException e) {
			throw new BadRequestException(e.getMessage());
		}
		
		return idJogo;
	}
	
}
