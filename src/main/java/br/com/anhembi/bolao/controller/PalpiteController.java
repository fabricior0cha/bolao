package br.com.anhembi.bolao.controller;

import java.io.IOException;

import com.google.gson.Gson;

import br.com.anhembi.bolao.exception.BadRequestException;
import br.com.anhembi.bolao.exception.NotFoundException;
import br.com.anhembi.bolao.exception.StandardError;
import br.com.anhembi.bolao.model.Usuario;
import br.com.anhembi.bolao.model.dto.PalpiteDTO;
import br.com.anhembi.bolao.service.PalpiteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/palpites/*")
public class PalpiteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final PalpiteService service = new PalpiteService();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		PalpiteDTO dto = gson.fromJson(request.getReader(), PalpiteDTO.class);

		try {
			service.insert(dto);
			response.setStatus(201);
		} catch (BadRequestException e) {
			response.setStatus(400);
			response.getWriter().write(gson.toJson(new StandardError(400, "Bad Request", e.getMessage())));

		} catch (NotFoundException e) {
			response.setStatus(404);
			response.getWriter().write(gson.toJson(new StandardError(404, "Not Found", e.getMessage())));

		}
	}
}
