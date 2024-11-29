package br.com.anhembi.bolao.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.StandardError;
import br.com.anhembi.bolao.model.Cupom;
import br.com.anhembi.bolao.model.dto.ResgateDTO;
import br.com.anhembi.bolao.service.CupomService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cupons/*")
public class CupomController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CupomService service = new CupomService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String idParticipante = request.getParameter("idParticipante");

		List<Cupom> cupons = service.findAll(Integer.parseInt(idParticipante));
		String json = gson.toJson(cupons);
		response.getWriter().write(json);
		response.setStatus(200);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		ResgateDTO dto = gson.fromJson(request.getReader(), ResgateDTO.class);

		try {
			service.resgatar(dto);
			response.setStatus(200);
		} catch (BadRequestException e) {
			response.setStatus(400);
			response.getWriter().write(gson.toJson(new StandardError(400, "Bad Request", e.getMessage())));

		} catch (NotFoundException e) {
			response.setStatus(404);
			response.getWriter().write(gson.toJson(new StandardError(404, "Not Found", e.getMessage())));

		}
	}
}
