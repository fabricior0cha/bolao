package br.com.anhembi.bolao.controller;

import java.io.IOException;

import com.google.gson.Gson;

import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.StandardError;
import br.com.anhembi.bolao.model.Usuario;
import br.com.anhembi.bolao.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/usuarios/*")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final UsuarioService service = new UsuarioService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(request.getReader(), Usuario.class);

		try {
			service.insert(usuario);
			response.setStatus(201);
		} catch (BadRequestException e) {
			response.setStatus(400);
			response.getWriter().write(gson.toJson(new StandardError(400, "Bad Request", e.getMessage())));

		}

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(request.getReader(), Usuario.class);

		try {
			service.insert(usuario);
			response.setStatus(200);
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

		try {
			Usuario usuario = service.findById(Integer.parseInt(request.getPathInfo().substring(1)));
			String json = gson.toJson(usuario);
			response.getWriter().write(json);
			response.setStatus(200);
		} catch (NotFoundException e) {
			response.getWriter().write(gson.toJson(new StandardError(404, "Not found", e.getMessage())));
			response.setStatus(404);
		}

	}
}
