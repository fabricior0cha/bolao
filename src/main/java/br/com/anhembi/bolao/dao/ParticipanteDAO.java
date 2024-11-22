package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Participante;

public class ParticipanteDAO {

	private Connection conn;

	public ParticipanteDAO(Connection conn) {
		this.conn = conn;
	}

	public Integer insert(Participante participante) throws SQLProcedureException {
		Integer id = null;
		String query = "{CALL SP_PARTICIPANTE_IN_UP (?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setNull(1, Types.INTEGER, null);
			stmt.setInt(2, participante.getUsuario().getId());
			stmt.setInt(3, participante.getBolao().getId());
			stmt.setBoolean(4, Boolean.FALSE);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("PAR_INT_ID");
			}
			stmt.close();

		} catch (SQLException e) {
			throw new SQLProcedureException("Erro: Usuário já participa desse bolão!");
		}

		return id;
	}

	
	public void updateVencedor(Integer idJogo)  {

		String query = "{CALL SP_PARTICIPANTE_UP_VENCEDOR(?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, idJogo);
			

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
