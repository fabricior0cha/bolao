package br.com.anhembi.bolao.service;

import java.util.List;

import br.com.anhembi.bolao.dao.BolaoDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.model.Bolao;
import br.com.anhembi.bolao.model.Jogo;
import br.com.anhembi.bolao.model.Time;
import br.com.anhembi.bolao.model.dto.BolaoDTO;

public class BolaoService {

	private final BolaoDAO dao = new BolaoDAO(DBConnection.getConnection());

	private final TimeService timeService = new TimeService();
	
	private final JogoService jogoService = new JogoService();
	
	public void insert(BolaoDTO dto) throws NotFoundException, BadRequestException {
		Time timeUm = null;
		Time timeDois = null;
		Jogo jogo = new Jogo();
		Integer idJogo = null;
		
		try {
			timeUm = timeService.findById(dto.getIdTimeUm());
			timeDois = timeService.findById(dto.getIdTimeDois());
		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		}
		
		jogo.setTimeUm(timeUm);
		jogo.setTimeDois(timeDois);
		jogo.setData(dto.getDataJogo());
		
		try {
			idJogo = jogoService.insert(jogo);
			jogo.setId(idJogo);
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		
		Bolao bolao = new Bolao();
		
		bolao.setId(dto.getId());
		bolao.setJogo(jogo);
		bolao.setPremio(dto.getPremio());
		bolao.setTitulo(dto.getTitulo());
		
		dao.insert(bolao);
	}

	
	public Bolao findById(Integer id) throws NotFoundException {
		Bolao bolao = dao.findById(id);

		if (bolao == null) {
			throw new NotFoundException("Bolão não encontrado com id: " + id);
		}

		return bolao;
	}

	public List<Bolao> findAllByFiltros(Integer idUsuario){
		return dao.findAllByFiltros(idUsuario);
	}
	
	public List<Bolao> findAll(){
		return dao.findAll();
	}
}
