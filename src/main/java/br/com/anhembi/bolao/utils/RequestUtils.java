package br.com.anhembi.bolao.utils;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static Integer getPathParamId(HttpServletRequest request) {
		if(request.getPathInfo() != null) {
			return Integer.parseInt(request.getPathInfo().substring(1));
		}
		return null;
	}
	
}
