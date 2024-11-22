package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Bolao;
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
			if(jogo.getId() != null) {
				stmt.setInt(1, jogo.getId());
			} else {
				stmt.setNull(1, Types.INTEGER, null);
			}
			
			stmt.setDate(2, new Date(jogo.getData().getTime()));
			
			if(jogo.getTotalTimeUm() != null && jogo.getTotalTimeDois() != null) {
				stmt.setInt(3, jogo.getTotalTimeUm());
				stmt.setInt(4, jogo.getTotalTimeDois());
			} else {
				stmt.setNull(3, Types.INTEGER, null);
				stmt.setNull(4, Types.INTEGER, null);
			}
			
			stmt.setInt(5, jogo.getTimeUm().getId());
			stmt.setInt(6, jogo.getTimeDois().getId());
		
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
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
		String query = "{CALL SP_JOGO_IN_UP (?,?,?,?,?,?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, jogo.getId());
			stmt.setDate(2, Date.valueOf(jogo.getData().toString()));
			stmt.setInt(3, 0);
			stmt.setInt(4, 0);
			stmt.setInt(5, jogo.getTimeUm().getId());
			stmt.setInt(6, jogo.getTimeDois().getId());
			stmt.setInt(7, jogo.getBolao().getId());

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
				jogo = buildJogo(rs);     
	            
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jogo;
	}
	
	public Jogo findByBolaoId(Integer bolaoId) {
		Jogo jogo = null;
		String query = "{CALL SP_JOGO_FIND_BY_BOLAO (?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, bolaoId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				jogo = new Jogo();
	            jogo.setId(rs.getInt("JOG_INT_ID"));
	            jogo.setData(rs.getDate("JOG_DATE_DATA"));
	            jogo.setTotalTimeUm(rs.getInt("JOG_INT_T1"));
	            jogo.setTotalTimeDois(rs.getInt("JOG_INT_T2"));

	            Time timeUm = new Time();
	            timeUm.setId(rs.getInt("TIM1_INT_ID"));
	            timeUm.setNome(rs.getString("TIM1_STR_NOME"));
	            timeUm.setUrlEmblema(rs.getString("TIM1_STR_URL_EMBLEMA"));
	            jogo.setTimeUm(timeUm);

	            Time timeDois = new Time();
	            timeDois.setId(rs.getInt("TIM2_INT_ID"));
	            timeDois.setNome(rs.getString("TIM2_STR_NOME"));
	            timeDois.setUrlEmblema(rs.getString("TIM2_STR_URL_EMBLEMA"));
	            jogo.setTimeDois(timeDois);

	            Bolao bolao = new Bolao();
	        	bolao.setId(rs.getInt("BOL_INT_ID"));
				bolao.setTitulo(rs.getString("BOL_STR_TITULO"));
				bolao.setDataCriacao(rs.getDate("BOL_DT_CRIACAO"));
				bolao.setPremio(rs.getDouble("BOL_DOU_PREMIO"));
	            jogo.setBolao(bolao);
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jogo;
	}
	
	public List<Jogo> findAll() {
		List<Jogo> jogos = new ArrayList<Jogo>();
		String query = "{CALL SP_JOGO_FIND_ALL ()}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Jogo jogo = buildJogo(rs);     
	            jogos.add(jogo);
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jogos;
	}
	
	public Jogo buildJogo(ResultSet rs) throws SQLException {
		Jogo jogo = new Jogo();
        jogo.setId(rs.getInt("JOG_INT_ID"));
        jogo.setData(new java.util.Date(rs.getDate("JOG_DATE_DATA").getTime()));
        jogo.setTotalTimeUm(rs.getInt("JOG_INT_T1"));
        jogo.setTotalTimeDois(rs.getInt("JOG_INT_T2"));

        Time timeUm = new Time();
        timeUm.setId(rs.getInt("TIM1_INT_ID"));
        timeUm.setNome(rs.getString("TIM1_STR_NOME"));
        timeUm.setUrlEmblema(rs.getString("TIM1_STR_URL_EMBLEMA"));
        timeUm.setCodigo(rs.getString("TIM1_STR_CODIGO"));
        jogo.setTimeUm(timeUm);

        Time timeDois = new Time();
        timeDois.setId(rs.getInt("TIM2_INT_ID"));
        timeDois.setNome(rs.getString("TIM2_STR_NOME"));
        timeDois.setUrlEmblema(rs.getString("TIM2_STR_URL_EMBLEMA"));
        timeDois.setCodigo(rs.getString("TIM2_STR_CODIGO"));

        jogo.setTimeDois(timeDois);
        
        Bolao bolao = new Bolao();
    	bolao.setId(rs.getInt("BOL_INT_ID"));
		bolao.setTitulo(rs.getString("BOL_STR_TITULO"));
		bolao.setDataCriacao(rs.getDate("BOL_DT_CRIACAO"));
		bolao.setPremio(rs.getDouble("BOL_DOU_PREMIO"));
        jogo.setBolao(bolao);
        
        return jogo;
	}
}
