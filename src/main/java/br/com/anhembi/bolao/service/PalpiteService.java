package br.com.anhembi.bolao.service;

import java.util.List;

import br.com.anhembi.bolao.dao.PalpiteDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.model.Bolao;
import br.com.anhembi.bolao.model.Jogo;
import br.com.anhembi.bolao.model.Palpite;
import br.com.anhembi.bolao.model.Participante;
import br.com.anhembi.bolao.model.Usuario;
import br.com.anhembi.bolao.model.dto.PalpiteDTO;

public class PalpiteService {
	private final PalpiteDAO dao = new PalpiteDAO(DBConnection.getConnection());

	private final ParticipanteService participanteService = new ParticipanteService();

	private final JogoService jogoService = new JogoService();

	private final UsuarioService usuarioService = new UsuarioService();

	public void insert(PalpiteDTO dto) throws NotFoundException, BadRequestException {

		Participante participante = new Participante();
		Jogo jogo = null;

		try {
			jogo = jogoService.findById(dto.getIdJogo());
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		}

		try {
			participante = participanteService.findById(dto.getIdParticipante());
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		}


//		if (dto.getId() == null) {
//			try {
//				Integer idParticipante = participanteService.insert(participante);
//				participante.setId(idParticipante);
//			} catch (BadRequestException e) {
//				throw new BadRequestException(e.getMessage());
//			}
//		}

		Palpite palpite = new Palpite();
		palpite.setJogo(jogo);
		palpite.setParticipante(participante);
		palpite.setResultadoTimeUm(dto.getResultadoTimeUm());
		palpite.setResultadoTimeDois(dto.getResultadoTimeDois());
		palpite.setId(dto.getId());

		dao.insert(palpite);
	}

	public List<Palpite> findByParticipante(Integer idParticipante) {
		return dao.findByParticipante(idParticipante);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}
}
