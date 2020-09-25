package com.kuldeepsingh.connect4.securit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kuldeepsingh.connect4.model.Game;
import com.kuldeepsingh.connect4.service.GameService;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
	@Autowired
	private GameService gameService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		if (restOfTheUrl.equalsIgnoreCase("/connect4/game/start")) {
			return true;
		}
		String token = request.getHeader("x-token");
		if (StringUtils.isEmpty(token)) {
			response.setStatus(403);
			return false;
		}
		Game game = gameService.findByToken(token);
		if (game == null) {
			response.setStatus(404);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
	}
}
