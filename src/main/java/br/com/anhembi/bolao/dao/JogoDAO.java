package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Jogo;
import br.com.anhembi.bolao.model.Time;

public class JogoDAO {
	private Connection conn;

	public JogoDAO(Connection conn) {
		this.conn = conn;
	}

	public Integer insert(Jogo jogo) throws SQLProcedureException {
		String query = "{CALL SP_JOGO_IN_UP (?,?,?,?,?,?)}";
		Integer idJogo = null;
		try {
			CallableStatement stmt = conn.prepareCall(query);
			if (jogo.getId() != null) {
				stmt.setInt(1, jogo.getId());
			} else {
				stmt.setNull(1, Types.INTEGER, null);
			}

			stmt.setTimestamp(2, Timestamp.valueOf(jogo.getHorario()));

			if (jogo.getTotalTimeUm() != null && jogo.getTotalTimeDois() != null) {
				stmt.setInt(3, jogo.getTotalTimeUm());
				stmt.setInt(4, jogo.getTotalTimeDois());
			} else {
				stmt.setNull(3, Types.INTEGER, null);
				stmt.setNull(4, Types.INTEGER, null);
			}

			stmt.setInt(5, jogo.getTimeUm().getId());
			stmt.setInt(6, jogo.getTimeDois().getId());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				idJogo = rs.getInt("JOG_INT_ID");
			}
			stmt.close();
		} catch (SQLException e) {
			if (e.getSQLState().equals("45000")) {
				throw new SQLProcedureException(e.getMessage());
			}
			e.printStackTrace();
		}

		return idJogo;
	}

	public void update(Jogo jogo) throws SQLProcedureException {
		String query = "{CALL SP_JOGO_IN_UP (?,?,?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, jogo.getId());
			stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setInt(3, 0);
			stmt.setInt(4, 0);
			stmt.setInt(5, jogo.getTimeUm().getId());
			stmt.setInt(6, jogo.getTimeDois().getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			if (e.getSQLState().equals("45000")) {
				throw new SQLProcedureException(e.getMessage());
			}
			e.printStackTrace();
		}
	}

	public Jogo findById(Integer id) {
		Jogo jogo = null;
		String query = "{CALL SP_JOGO_FIND_BY_ID (?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				jogo = build(rs);

			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jogo;
	}

	public List<Jogo> findByParticipante(Integer participanteId) {
		List<Jogo> jogos = new ArrayList<Jogo>();
		String query = "{CALL SP_JOGO_FIND_ALL_BY_PARTICIPANTE (?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, participanteId);
			stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Jogo jogo = build(rs);
				jogos.add(jogo);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jogos;
	}
	public List<Jogo> findAll() {
		List<Jogo> jogos = new ArrayList<Jogo>();
		String query = "{CALL SP_JOGO_FIND_ALL ()}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Jogo jogo = build(rs);
				jogos.add(jogo);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jogos;
	}
	public Jogo build(ResultSet rs) throws SQLException {
		Jogo jogo = new Jogo();
		jogo.setId(rs.getInt("JOG_INT_ID"));
		jogo.setHorario(rs.getTimestamp("JOG_DT_HORARIO").toLocalDateTime());
		jogo.setTotalTimeUm(rs.getObject("JOG_INT_T1", Integer.class));
		jogo.setTotalTimeDois(rs.getObject("JOG_INT_T2", Integer.class));

		Time timeUm = new Time();
		timeUm.setId(rs.getInt("TIM1_INT_ID"));
		timeUm.setNome(rs.getString("TIM1_STR_NOME"));
		timeUm.setCodigo(rs.getString("TIM1_STR_CODIGO"));
		jogo.setTimeUm(timeUm);

		Time timeDois = new Time();
		timeDois.setId(rs.getInt("TIM2_INT_ID"));
		timeDois.setNome(rs.getString("TIM2_STR_NOME"));
		timeDois.setCodigo(rs.getString("TIM2_STR_CODIGO"));
		jogo.setTimeDois(timeDois);

		return jogo;
	}
}
