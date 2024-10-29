package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Types;
import java.time.LocalDate;


import br.com.anhembi.bolao.model.Bolao;

public class BolaoDAO {
	private Connection conn;

	public BolaoDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void insert(Bolao bolao) {
		String query = "{CALL sp_bolao_InserirAtualizar(?,?,?,?,?)}";
		
		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setNull(1, Types.INTEGER, null);
			stmt.setString(2, bolao.getTitulo());
			stmt.setString(3, bolao.getCodigo());
			stmt.setDate(4, Date.valueOf(LocalDate.now()));
			stmt.setDouble(5, bolao.getPremio());
			
			stmt.execute();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
