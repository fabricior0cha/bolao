package br.com.anhembi.bolao.service;

import br.com.anhembi.bolao.dao.ParticipanteDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
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

	public Participante findById(Integer id) throws NotFoundException {
		Participante participante = dao.findById(id);

		if (participante == null) {
			throw new NotFoundException("Nenhum participante encontrado com id: " + id);
		}

		return participante;
	}

	public Participante findByUsuario(Integer idUsuario) throws NotFoundException {
		Participante participante = dao.findByUsuario(idUsuario);

		if (participante == null) {
			throw new NotFoundException("Nenhum participante encontrado com id: " + idUsuario);
		}

		return participante;
	}

	public void updatePontos(Integer idJogo) {
		dao.updatePontos(idJogo);
	}
}
