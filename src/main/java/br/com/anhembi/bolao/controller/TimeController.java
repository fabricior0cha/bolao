package br.com.anhembi.bolao.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import br.com.anhembi.bolao.model.Time;
import br.com.anhembi.bolao.service.TimeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/times/*")
public class TimeController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	private final TimeService service = new TimeService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();

		List<Time> times = service.findAll("");
		String json = gson.toJson(times);
		response.getWriter().write(json);
		response.setStatus(200);
	}
	
}
