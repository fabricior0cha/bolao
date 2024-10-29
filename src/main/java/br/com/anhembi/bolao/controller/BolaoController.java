package br.com.anhembi.bolao.controller;

import java.io.IOException;

import com.google.gson.Gson;

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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		BolaoService service = new BolaoService();
		Bolao bolao = gson.fromJson(request.getReader(), Bolao.class);
		
        service.insert(bolao);
        
        response.setStatus(201);
	}
}
