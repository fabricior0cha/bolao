package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import br.com.anhembi.bolao.model.Bolao;

public class BolaoDAO {
	private Connection conn;

	public BolaoDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void insert(Bolao bolao) {
		String query = "{CALL SP_BOLAO_IN_UP(?,?,?,?)}";
		
		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setNull(1, Types.INTEGER, null);
			stmt.setString(2, bolao.getTitulo());
			stmt.setDate(3, Date.valueOf(LocalDate.now()));
			stmt.setDouble(4, bolao.getPremio());
			
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
	
	public void delete(Integer id) {
		String query = "{CALL SP_BOLAO_DELETE_BY_ID(?)}";
		
		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
