package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.anhembi.bolao.model.Bolao;
import br.com.anhembi.bolao.model.Jogo;
import br.com.anhembi.bolao.model.Palpite;
import br.com.anhembi.bolao.model.Participante;
import br.com.anhembi.bolao.model.Time;

public class PalpiteDAO {
	private Connection conn;

	public PalpiteDAO(Connection conn) {
		this.conn = conn;
	}

	public void insert(Palpite palpite) {
		String query = "{CALL SP_PALPITE_IN_UP(?,?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			if (palpite.getId() != null) {
				stmt.setInt(1, palpite.getId());
			} else {
				stmt.setNull(1, Types.INTEGER, null);
			}
			stmt.setInt(2, palpite.getResultadoTimeUm());
			stmt.setInt(3, palpite.getResultadoTimeDois());
			stmt.setInt(4, palpite.getParticipante().getId());
			stmt.setInt(5, palpite.getJogo().getId());

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Palpite> findByParticipante(Integer idParticipante) {
		String query = "{CALL SP_PALPITE_FIND_BY_PARTICIPANTE(?)}";
		List<Palpite> palpites = new ArrayList<Palpite>();
		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, idParticipante);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Palpite palpite = new Palpite();
				Participante participante = new Participante();


				participante.setId(rs.getInt("PAR_INT_ID"));
				
				palpite.setParticipante(participante);
				palpite.setId(rs.getInt("PAL_INT_ID"));
				palpite.setResultadoTimeUm(rs.getInt("PAL_INT_T1"));
				palpite.setResultadoTimeDois(rs.getInt("PAL_INT_T2"));
				palpite.setJogo(buildJogo(rs));
			
				palpites.add(palpite);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return palpites;
	}

	public void deleteById(Integer id) {
		String query = "{CALL SP_PALPITE_DELETE(?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Jogo buildJogo(ResultSet rs) throws SQLException {
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
