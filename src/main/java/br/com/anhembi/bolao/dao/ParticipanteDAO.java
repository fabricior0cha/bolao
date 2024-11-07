package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import br.com.anhembi.bolao.exception.UniqueException;
import br.com.anhembi.bolao.model.Participante;

public class ParticipanteDAO {

	private Connection conn;

	public ParticipanteDAO(Connection conn) {
		this.conn = conn;
	}

	public void insert(Participante participante) throws UniqueException {

		String query = "{CALL SP_PARTICIPANTE_IN_UP (?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setNull(1, Types.INTEGER, null);
			stmt.setInt(2, participante.getUsuario().getId());
			stmt.setInt(3, participante.getBolao().getId());
			stmt.setBoolean(4, Boolean.FALSE);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			if (e.getSQLState().equals("45000")) {
				throw new UniqueException(e.getMessage());
			}
			e.printStackTrace();
		}
	}

	public void update(Participante participante) throws UniqueException {

		String query = "{CALL SP_PARTICIPANTE_IN_UP (?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, participante.getId());
			stmt.setInt(2, participante.getUsuario().getId());
			stmt.setInt(3, participante.getBolao().getId());
			stmt.setBoolean(4, participante.getVencedor());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			if (e.getSQLState().equals("45000")) {
				throw new UniqueException(e.getMessage());
			}
			e.printStackTrace();
		}
	}
	

}
