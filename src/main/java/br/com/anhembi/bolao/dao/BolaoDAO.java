package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.anhembi.bolao.model.Bolao;
import br.com.anhembi.bolao.model.Jogo;
import br.com.anhembi.bolao.model.Time;

public class BolaoDAO {
	private Connection conn;

	public BolaoDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void insert(Bolao bolao) {
		String query = "{CALL SP_BOLAO_IN_UP(?,?,?,?,?)}";
		
		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setNull(1, Types.INTEGER, null);
			stmt.setString(2, bolao.getTitulo());
			stmt.setDate(3, Date.valueOf(LocalDate.now()));
			stmt.setDouble(4, bolao.getPremio());
			stmt.setInt(5, bolao.getJogo().getId());
			stmt.execute();
			stmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update(Bolao bolao) {
		String query = "{CALL SP_BOLAO_IN_UP(?,?,?,?,?)}";
		
		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, bolao.getId());
			stmt.setString(2, bolao.getTitulo());
			stmt.setDate(3, Date.valueOf(LocalDate.now()));
			stmt.setDouble(4, bolao.getPremio());
			
			stmt.execute();
			stmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Bolao findById(Integer id) {
		Bolao bolao = null;
		String query = "{CALL SP_BOLAO_FIND_BY_ID (?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				bolao = new Bolao();
				bolao.setId(rs.getInt("BOL_INT_ID"));
				bolao.setTitulo(rs.getString("BOL_STR_TITULO"));
				bolao.setDataCriacao(rs.getDate("BOL_DT_CRIACAO"));
				bolao.setPremio(rs.getDouble("BOL_DOU_PREMIO"));
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bolao;
	}
	
	
	public List<Bolao> findAllByFiltros(Integer idUsuario){
		List<Bolao> boloes = new ArrayList<Bolao>();
		String query = "{CALL SP_BOLAO_FIND_ALL_BY_FILTROS(?, ?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, idUsuario);
			stmt.setDate(2, Date.valueOf(LocalDate.now()));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
		        Bolao bolao = new Bolao();
		    	bolao.setId(rs.getInt("BOL_INT_ID"));
				bolao.setTitulo(rs.getString("BOL_STR_TITULO"));
				bolao.setDataCriacao(rs.getDate("BOL_DT_CRIACAO"));
				bolao.setPremio(rs.getDouble("BOL_DOU_PREMIO"));
				bolao.setJogo(buildJogo(rs));
				
				boloes.add(bolao);
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boloes;
	}
	
	public List<Bolao> findAll(){
		List<Bolao> boloes = new ArrayList<Bolao>();
		String query = "{CALL SP_BOLAO_FIND_ALL()}";

		try {
			CallableStatement stmt = conn.prepareCall(query);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
		        Bolao bolao = new Bolao();
		    	bolao.setId(rs.getInt("BOL_INT_ID"));
				bolao.setTitulo(rs.getString("BOL_STR_TITULO"));
				bolao.setDataCriacao(rs.getDate("BOL_DT_CRIACAO"));
				bolao.setPremio(rs.getDouble("BOL_DOU_PREMIO"));
				bolao.setJogo(buildJogo(rs));
				
				boloes.add(bolao);
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boloes;
	}
	
	public Jogo buildJogo(ResultSet rs) throws SQLException {
		Jogo jogo = new Jogo();
        jogo.setId(rs.getInt("JOG_INT_ID"));
        jogo.setData(new java.util.Date(rs.getDate("JOG_DATE_DATA").getTime()));
        
        jogo.setTotalTimeUm(rs.getObject("JOG_INT_T1", Integer.class));
        jogo.setTotalTimeDois(rs.getObject("JOG_INT_T2", Integer.class));

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
        
        return jogo;
	}
}
