package br.com.anhembi.bolao.controller;

import java.io.IOException;

import com.google.gson.Gson;

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
		Gson gson = new Gson();
		UsuarioService service = new UsuarioService();
		Usuario usuario = gson.fromJson(request.getReader(), Usuario.class);
		
        service.login(usuario);
        
        response.setStatus(200);
	}
}
