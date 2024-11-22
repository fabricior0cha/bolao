package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import br.com.anhembi.bolao.exception.UniqueException;
import br.com.anhembi.bolao.model.TipoUsuario;
import br.com.anhembi.bolao.model.Usuario;

public class UsuarioDAO {

	private Connection conn;

	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}

	public void insert(Usuario usuario) throws UniqueException {
		String query = "{CALL SP_USUARIO_IN_UP (?,?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getEmail());
		
			if(usuario.getId() != null) {
				stmt.setInt(4, usuario.getId());
			} else {
				stmt.setNull(4, Types.INTEGER, null);
			}
			stmt.setInt(5, 2);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			if (e.getSQLState().equals("45000")) {
				throw new UniqueException(e.getMessage());
			}
			e.printStackTrace();
		}
	}

	
	public Usuario login(Usuario usuario) {
		String query = "{CALL SP_USUARIO_LOGIN (?,?)}";
		Usuario usuarioLogado = null;
		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				usuarioLogado = new Usuario();
				usuarioLogado.setId(rs.getInt("USU_INT_ID"));
				usuarioLogado.setNome(rs.getString("USU_STR_NOME"));
				usuarioLogado.setEmail(rs.getString("USU_STR_EMAIL"));
				usuarioLogado.setSenha(rs.getString("USU_STR_SENHA"));
				TipoUsuario tipo = new TipoUsuario(rs.getInt("TP_INT_ID"), rs.getString("TP_STR_CODIGO"));
				usuarioLogado.setTipo(tipo);
			}
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioLogado;
	}

	public Usuario findById(Integer id) {
		Usuario usuario = null;
		String query = "{CALL SP_USUARIO_FIND_BY_ID (?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("USU_INT_ID"));
				usuario.setNome(rs.getString("USU_STR_NOME"));
				usuario.setEmail(rs.getString("USU_STR_EMAIL"));
				usuario.setSenha(rs.getString("USU_STR_SENHA"));
				TipoUsuario tipo = new TipoUsuario(rs.getInt("TP_INT_ID"), rs.getString("TP_STR_CODIGO"));
				usuario.setTipo(tipo);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
