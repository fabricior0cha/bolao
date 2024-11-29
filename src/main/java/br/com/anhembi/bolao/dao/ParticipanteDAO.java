package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Participante;
import br.com.anhembi.bolao.model.Time;

public class ParticipanteDAO {

	private Connection conn;

	public ParticipanteDAO(Connection conn) {
		this.conn = conn;
	}

	public Integer insert(Participante participante) throws SQLProcedureException {
		Integer id = null;
		String query = "{CALL SP_PARTICIPANTE_IN_UP (?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setNull(1, Types.INTEGER, null);
			stmt.setInt(2, participante.getUsuario().getId());
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

	
	public void updatePontos(Integer idJogo)  {

		String query = "{CALL SP_PARTICIPANTE_UP_PONTOS(?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, idJogo);
			

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Participante findById(Integer id) {
		Participante participante = null;
		String query = "{CALL SP_PARTICIPANTE_FIND_BY_ID (?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				participante = new Participante();
				participante.setId(rs.getInt("PAR_INT_ID"));
				participante.setPontos(rs.getInt("PAR_INT_PONTOS"));
			
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participante;
	}
	
	public Participante findByUsuario(Integer idUsuario) {
		Participante participante = null;
		String query = "{CALL SP_PARTICIPANTE_FIND_BY_USUARIO (?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, idUsuario);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				participante = new Participante();
				participante.setId(rs.getInt("PAR_INT_ID"));
				participante.setPontos(rs.getInt("PAR_INT_PONTOS"));
			
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participante;
	}

}
