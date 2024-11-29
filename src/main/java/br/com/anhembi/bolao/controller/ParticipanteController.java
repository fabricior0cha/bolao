package br.com.anhembi.bolao.controller;

import java.io.IOException;

import com.google.gson.Gson;

import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.StandardError;
import br.com.anhembi.bolao.model.Participante;
import br.com.anhembi.bolao.model.Usuario;
import br.com.anhembi.bolao.service.ParticipanteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/participantes/*")
public class ParticipanteController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private final ParticipanteService service = new ParticipanteService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		String idUsuario = request.getParameter("idUsuario");
		try {
			Participante participante = service.findByUsuario(Integer.parseInt(idUsuario));
			String json = gson.toJson(participante);
			response.getWriter().write(json);
			response.setStatus(200);
		} catch (NotFoundException e) {
			response.getWriter().write(gson.toJson(new StandardError(404, "Not found", e.getMessage())));
			response.setStatus(404);
		}

	}
}
