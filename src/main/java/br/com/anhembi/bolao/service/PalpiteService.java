package br.com.anhembi.bolao.service;

import java.util.List;

import br.com.anhembi.bolao.dao.PalpiteDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.model.Bolao;
import br.com.anhembi.bolao.model.Palpite;
import br.com.anhembi.bolao.model.Participante;
import br.com.anhembi.bolao.model.Usuario;
import br.com.anhembi.bolao.model.dto.PalpiteDTO;

public class PalpiteService {
	private final PalpiteDAO dao = new PalpiteDAO(DBConnection.getConnection());

	private final ParticipanteService participanteService = new ParticipanteService();

	private final BolaoService bolaoService = new BolaoService();

	private final UsuarioService usuarioService = new UsuarioService();

	public void insert(PalpiteDTO dto) throws NotFoundException, BadRequestException {

		Participante participante = new Participante();
		Bolao bolao = null;
		Usuario usuario = null;

		try {
			bolao = bolaoService.findById(dto.getIdBolao());
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		}

		try {
			usuario = usuarioService.findById(dto.getIdUsuario());
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		}

		participante.setBolao(bolao);
		participante.setUsuario(usuario);
		participante.setVencedor(Boolean.FALSE);

		if (dto.getId() == null) {
			try {
				Integer idParticipante = participanteService.insert(participante);
				participante.setId(idParticipante);
			} catch (BadRequestException e) {
				throw new BadRequestException(e.getMessage());
			}
		}

		Palpite palpite = new Palpite();
		palpite.setBolao(bolao);
		palpite.setParticipante(participante);
		palpite.setResultadoTimeUm(dto.getResultadoTimeUm());
		palpite.setResultadoTimeDois(dto.getResultadoTimeDois());
		palpite.setId(dto.getId());

		System.out.println(palpite.getId());
		dao.insert(palpite);
	}

	public List<Palpite> findAllByUsuario(Integer idUsuario) {
		return dao.findAllByUsuario(idUsuario);
	}

	public void deleteByParticipante(Integer idParticipante) {
		dao.deleteByParticipante(idParticipante);
	}
}
