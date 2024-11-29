package br.com.anhembi.bolao.service;

import java.util.List;

import br.com.anhembi.bolao.dao.CupomDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Cupom;
import br.com.anhembi.bolao.model.Participante;
import br.com.anhembi.bolao.model.Time;
import br.com.anhembi.bolao.model.dto.ResgateDTO;

public class CupomService {

	private final CupomDAO dao = new CupomDAO(DBConnection.getConnection());
	
	private final ParticipanteService participanteService = new ParticipanteService();

	public List<Cupom> findAll(Integer idParticipante){
		return dao.findAll(idParticipante);
	}
	
	public Cupom findById(Integer id) throws NotFoundException {
		Cupom cupom = dao.findById(id);

		if (cupom == null) {
			throw new NotFoundException("Nenhum cupom encontrado com id: " + id);
		}

		return cupom;
	}
	
	public void resgatar(ResgateDTO dto) throws NotFoundException, BadRequestException {
		Participante participante = new Participante();
		Cupom cupom = new Cupom();
		try {
			participante = participanteService.findById(dto.getIdParticipante());
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		}
		
		try {
			cupom = this.findById(dto.getIdCupom());
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		}
		
		try {
			dao.resgatar(participante, cupom);
		} catch (SQLProcedureException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
}
