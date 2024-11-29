package br.com.anhembi.bolao.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.anhembi.bolao.exception.SQLProcedureException;
import br.com.anhembi.bolao.model.Cupom;
import br.com.anhembi.bolao.model.Loja;
import br.com.anhembi.bolao.model.Participante;

public class CupomDAO {
	private Connection conn;

	public CupomDAO(Connection conn) {
		this.conn = conn;
	}

	public void resgatar(Participante participante, Cupom cupom) throws SQLProcedureException {
		String query = "{CALL SP_CUPOM_RESGATAR (?,?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, cupom.getId());
			stmt.setInt(2, participante.getId());

			stmt.execute();

			stmt.close();
		} catch (SQLException e) {
			if (e.getSQLState().equals("45000")) {
				throw new SQLProcedureException(e.getMessage());
			}
			e.printStackTrace();
		}

	}

	public List<Cupom> findAll(Integer idParticipante) {
		List<Cupom> cupons = new ArrayList<Cupom>();
		String query = "{CALL SP_CUPOM_FIND_ALL(?)}";

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

	public Cupom findById(Integer id) {
		Cupom cupom = null;
		String query = "{CALL SP_CUPOM_FIND_BY_ID(?)}";

		try {
			CallableStatement stmt = conn.prepareCall(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				cupom = this.build(rs);

			}
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cupom;
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
