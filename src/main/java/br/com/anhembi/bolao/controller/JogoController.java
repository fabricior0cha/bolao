package br.com.anhembi.bolao.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

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
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
	        @Override
	        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
	                throws JsonParseException {
	        
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            return LocalDateTime.parse(json.getAsString(), formatter);
	        }
	    }).create();

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
	    Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
	        @Override
	        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
	                throws JsonParseException {
	        
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            return LocalDateTime.parse(json.getAsString(), formatter);
	        }
	    }).create();

		Jogo jogo = gson.fromJson(request.getReader(), Jogo.class);

		try {
			service.insert(jogo);
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
		List<Jogo> jogos = new ArrayList<Jogo>();
		if (idParticipante != null) {
			jogos = service.findByParticipante(Integer.parseInt(idParticipante));
		} else {
			jogos = service.findAll();
		}

		String json = gson.toJson(jogos);
		response.getWriter().write(json);
		response.setStatus(200);
	}
}
