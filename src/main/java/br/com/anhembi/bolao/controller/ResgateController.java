package br.com.anhembi.bolao.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import br.com.anhembi.bolao.model.Cupom;
import br.com.anhembi.bolao.model.Palpite;
import br.com.anhembi.bolao.service.ResgateService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/resgates/*")
public class ResgateController extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	private final ResgateService service = new ResgateService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String idParticipante = request.getParameter("idParticipante");
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class,
						(JsonSerializer) (localDateTime, type,
								jsonSerializationContext) -> new JsonPrimitive(((LocalDateTime) localDateTime)
										.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
				.registerTypeAdapter(LocalDateTime.class,
						(JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
							try {
								return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(),
										DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
							} catch (DateTimeParseException e) {
								return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(),
										DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
							}
						})
				.create();

		List<Cupom> resgates = service.findAll(Integer.parseInt(idParticipante));
		String json = gson.toJson(resgates);
		response.getWriter().write(json);
		response.setStatus(200);
	}
}
