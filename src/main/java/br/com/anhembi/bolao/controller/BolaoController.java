package br.com.anhembi.bolao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.StandardError;
import br.com.anhembi.bolao.model.Bolao;
import br.com.anhembi.bolao.model.dto.BolaoDTO;
import br.com.anhembi.bolao.service.BolaoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boloes/*")
public class BolaoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final BolaoService service = new BolaoService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();

		BolaoDTO dto = gson.fromJson(request.getReader(), BolaoDTO.class);

		try {
			service.insert(dto);
			response.setStatus(201);
		} catch (NotFoundException e) {
			response.getWriter().write(gson.toJson(new StandardError(404, "Not found", e.getMessage())));
			response.setStatus(404);
		} catch (BadRequestException e) {
			response.setStatus(400);
			response.getWriter().write(gson.toJson(new StandardError(400, "Bad Request", e.getMessage())));
		}

	}

	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		String idUsuario = request.getParameter("idUsuario");
		List<Bolao> boloes = new ArrayList<Bolao>();
		if (idUsuario != null) {
			boloes = service.findAllByFiltros(Integer.parseInt(idUsuario));
		} else {
			boloes = service.findAll();
		}

		String json = gson.toJson(boloes);
		response.getWriter().write(json);
		response.setStatus(200);

	}

	

}
