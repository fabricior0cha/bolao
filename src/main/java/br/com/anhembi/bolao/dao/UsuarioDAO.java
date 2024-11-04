package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import br.com.anhembi.bolao.exception.UniqueException;
import br.com.anhembi.bolao.model.Usuario;

public class UsuarioDAO {

	private Connection conn;

	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}

	public void insert(Usuario usuario) throws UniqueException {
		String query = "{CALL SP_USUARIO_IN_UP (?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getEmail());
			stmt.setNull(4, Types.INTEGER, null);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			if (e.getSQLState().equals("45000")) {
				throw new UniqueException(e.getMessage());
			}
			e.printStackTrace();
		}
	}

	public void update(Usuario usuario) {
		String query = "{CALL SP_USUARIO_IN_UP (?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getEmail());
			stmt.setInt(4, usuario.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void login(Usuario usuario) {
		String query = "{CALL SP_USUARIO_LOGIN (?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
