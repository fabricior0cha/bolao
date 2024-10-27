package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import br.com.anhembi.bolao.model.Usuario;

public class UsuarioDAO {

	private Connection conn;

	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}

	public void insert(Usuario usuario) {
		String query = "{CALL sp_usuario_InserirAtualizar (?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getEmail());
			stmt.setNull(4, Types.INTEGER, null);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Usuario usuario, Integer id) {
		String query = "{CALL sp_usuario_InserirAtualizar (?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getEmail());
			stmt.setInt(4, id);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void login(Usuario usuario) {
		String query = "{CALL sp_usuario_Login (?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Usuario findById(Integer id) {
		Usuario usuario = null;
		String query = "{CALL sp_usuario_FindById (?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("usuario_id"));
				usuario.setNome(rs.getString("usuario_nome"));
				usuario.setEmail(rs.getString("usuario_email"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
