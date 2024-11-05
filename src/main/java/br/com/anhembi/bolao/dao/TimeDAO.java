package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.anhembi.bolao.model.Time;

public class TimeDAO {
	
	private Connection conn;
	
	public TimeDAO(Connection conn) {
		this.conn = conn;
	}
	
	public Time findById(Integer id) {
		Time time = null;
		String query = "{CALL SP_TIME_FIND_BY_ID (?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				time = new Time();
				time.setId(rs.getInt("TIM_INT_ID"));
				time.setNome(rs.getString("TIM_STR_NOME"));
				time.setUrlEmblema(rs.getString("TIM_STR_URL_EMBLEMA"));
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	public List<Time> findByAll(String nome) {
		
		ArrayList<Time> times = new ArrayList<Time>();
		
		String query = "{CALL SP_TIME_FIND_ALL (?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				Time time = new Time();
				time.setId(rs.getInt("TIM_INT_ID"));
				time.setNome(rs.getString("TIM_STR_NOME"));
				time.setUrlEmblema(rs.getString("TIM_STR_URL_EMBLEMA"));
				times.add(time);
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return times;
	}

}
