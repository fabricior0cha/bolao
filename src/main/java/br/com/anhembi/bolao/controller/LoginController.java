package br.com.anhembi.bolao.controller;

import java.io.IOException;

import com.google.gson.Gson;

import br.com.anhembi.bolao.exception.StandardError;
import br.com.anhembi.bolao.exception.UnauthorizedException;
import br.com.anhembi.bolao.model.Usuario;
import br.com.anhembi.bolao.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		UsuarioService service = new UsuarioService();
		Usuario usuario = gson.fromJson(request.getReader(), Usuario.class);
		
        try {
        	String json = gson.toJson(service.login(usuario));
			response.getWriter().write(json);
			response.setStatus(200);
		} catch (UnauthorizedException e) {
			response.getWriter().write(gson.toJson(new StandardError(401, "Unauthorized", e.getMessage())));
			response.setStatus(401);
		}
        
        
	}
}
