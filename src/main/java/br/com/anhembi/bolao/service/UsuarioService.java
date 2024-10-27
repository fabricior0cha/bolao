package br.com.anhembi.bolao.service;

import br.com.anhembi.bolao.dao.UsuarioDAO;
import br.com.anhembi.bolao.db.DBConnection;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.model.Usuario;

public class UsuarioService {
	
	private final UsuarioDAO dao = new UsuarioDAO(DBConnection.getConnection());

	public void insert(Usuario usuario) {
		dao.insert(usuario);
	}

	public void update(Usuario usuario, Integer id) {
		dao.update(usuario, id);
	}
	
	public Usuario findById(Integer id) throws NotFoundException {
		Usuario usuario = dao.findById(id);
		
		if(usuario == null) {
			throw new NotFoundException("Usuário não encontrado com id: " + id); 
		}
		
		return usuario;
	}
	
	public void login(Usuario usuario) {
		dao.login(usuario);
	}
}
