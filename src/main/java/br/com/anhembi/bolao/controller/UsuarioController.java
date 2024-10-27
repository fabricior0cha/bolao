package br.com.anhembi.bolao.controller;

import java.io.IOException;

import com.google.gson.Gson;

import br.com.anhembi.bolao.model.Usuario;
import br.com.anhembi.bolao.service.UsuarioService;
import exception.NotFoundException;
import exception.StandardError;
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
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(request.getReader(), Usuario.class);
		
        service.insert(usuario);
        
        response.setStatus(201);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(request.getReader(), Usuario.class);
		
        service.update(usuario, Integer.parseInt(request.getPathInfo().substring(1)));
        
        response.setStatus(200);
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
