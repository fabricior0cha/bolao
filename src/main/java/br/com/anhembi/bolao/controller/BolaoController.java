package br.com.anhembi.bolao.controller;

import java.io.IOException;

import com.google.gson.Gson;

import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.StandardError;
import br.com.anhembi.bolao.model.Bolao;
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
		Gson gson = new Gson();
	
		Bolao bolao = gson.fromJson(request.getReader(), Bolao.class);
		
        service.insert(bolao);
        
        response.setStatus(201);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		Bolao bolao = gson.fromJson(request.getReader(), Bolao.class);

		service.update(bolao);

		response.setStatus(200);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();

		try {
			Bolao bolao = service.findById(Integer.parseInt(request.getPathInfo().substring(1)));
			String json = gson.toJson(bolao);
			response.getWriter().write(json);
			response.setStatus(200);
		} catch (NotFoundException e) {
			response.getWriter().write(gson.toJson(new StandardError(404, "Not found", e.getMessage())));
			response.setStatus(404);
		}

	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getPathInfo().substring(1));
	
		service.delete(id);

		response.setStatus(204);
	}
	
}
