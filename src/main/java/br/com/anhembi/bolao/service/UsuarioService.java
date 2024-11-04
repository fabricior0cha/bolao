package br.com.anhembi.bolao.service;

import br.com.anhembi.bolao.dao.UsuarioDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.UniqueException;
import br.com.anhembi.bolao.model.Usuario;

public class UsuarioService {

	private final UsuarioDAO dao = new UsuarioDAO(DBConnection.getConnection());

	public void insert(Usuario usuario) throws BadRequestException {

		if (usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null) {
			throw new BadRequestException("Erro: Preencha todos os campos!");
		}

		try {
			dao.insert(usuario);
		} catch (UniqueException e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public void update(Usuario usuario) {
		dao.update(usuario);
	}

	public Usuario findById(Integer id) throws NotFoundException {
		Usuario usuario = dao.findById(id);

		if (usuario == null) {
			throw new NotFoundException("Usuário não encontrado com id: " + id);
		}

		return usuario;
	}

	public void login(Usuario usuario) {
		dao.login(usuario);
	}
}
