package br.com.anhembi.bolao.service;

import br.com.anhembi.bolao.dao.ParticipanteDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Participante;

public class ParticipanteService {

	private final ParticipanteDAO dao = new ParticipanteDAO(DBConnection.getConnection());

	public Integer insert(Participante participante) throws BadRequestException {
		try {
			return dao.insert(participante);
		} catch (SQLProcedureException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	public void update(Participante participante) throws BadRequestException {
		try {
			dao.update(participante);
		} catch (SQLProcedureException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
}
