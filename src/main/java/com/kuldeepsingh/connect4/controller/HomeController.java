package com.kuldeepsingh.connect4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuldeepsingh.connect4.dtos.GameMoveResponse;
import com.kuldeepsingh.connect4.dtos.MoveInputPayload;
import com.kuldeepsingh.connect4.service.GameService;

@RestController
@RequestMapping("connect4")
public class HomeController {
	@Autowired
	private GameService gameService;

	@GetMapping("game/start")
	public ResponseEntity<?> start() {
		return ResponseEntity.ok(gameService.start());
	}

	@PostMapping("game/move")
	public ResponseEntity<?> move(@RequestBody MoveInputPayload inputPayload) {
		GameMoveResponse response = gameService.move(inputPayload);
		return ResponseEntity.ok(response);
	}

	@GetMapping("game")
	public ResponseEntity<?> get() {
		return ResponseEntity.ok(gameService.getAll());
	}

	@GetMapping("game/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		return ResponseEntity.ok(gameService.findById(id));
	}
}
