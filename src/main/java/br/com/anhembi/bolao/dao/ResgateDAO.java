package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Cupom;
import br.com.anhembi.bolao.model.Loja;
import br.com.anhembi.bolao.model.Participante;

public class ResgateDAO {
	private Connection conn;

	public ResgateDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Cupom> findAll(Integer idParticipante) {
		List<Cupom> cupons = new ArrayList<Cupom>();
		String query = "{CALL SP_RESGATE_FIND_ALL(?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, idParticipante);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Cupom cupom = this.build(rs);

				cupons.add(cupom);
			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cupons;
	}
	
	
	
	public Cupom build(ResultSet rs) throws SQLException {
		Cupom cupom = new Cupom();
		Loja loja = new Loja();

		cupom.setId(rs.getInt("CUP_INT_ID"));
		cupom.setPontos(rs.getInt("CUP_INT_PONTOS"));
		cupom.setDesconto(rs.getInt("CUP_INT_DESCONTO"));
		cupom.setDataVencimento(new Date(rs.getDate("CUP_DT_VENCIMENTO").getTime()));
		cupom.setCodigo(rs.getString("CUP_STR_CODIGO"));
		loja.setId(rs.getInt("LOJ_INT_ID"));
		loja.setNome(rs.getString("LOJ_STR_NOME"));
		loja.setCodigo(rs.getString("LOJ_STR_CODIGO"));

		cupom.setLoja(loja);
		return cupom;
	}
}
