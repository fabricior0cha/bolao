package br.com.anhembi.bolao.controller;

import java.io.IOException;

import com.google.gson.Gson;

import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.StandardError;
import br.com.anhembi.bolao.model.Jogo;
import br.com.anhembi.bolao.service.JogoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jogos/*")
public class JogoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private JogoService service = new JogoService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
	
		Jogo jogo = gson.fromJson(request.getReader(), Jogo.class);
		
        try {
			service.insert(jogo);
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
	
		Jogo jogo = gson.fromJson(request.getReader(), Jogo.class);
		
        try {
			service.insert(jogo);
			response.setStatus(200);
		} catch (BadRequestException e) {
			response.setStatus(400);
			response.getWriter().write(gson.toJson(new StandardError(400, "Bad Request", e.getMessage())));
		}
        
        
	}

	
}
