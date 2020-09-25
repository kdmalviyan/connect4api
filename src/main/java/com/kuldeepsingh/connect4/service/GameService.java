package com.kuldeepsingh.connect4.service;

import java.util.List;

import com.kuldeepsingh.connect4.dtos.GameMoveResponse;
import com.kuldeepsingh.connect4.dtos.GameStartResponse;
import com.kuldeepsingh.connect4.dtos.MoveInputPayload;
import com.kuldeepsingh.connect4.model.Game;
import com.kuldeepsingh.connect4.model.Player;

public interface GameService {
	GameStartResponse start();

	GameMoveResponse move(MoveInputPayload inputPayload);

	boolean result(String[][] metrix, int colIndex, int rowIndex,  Player player);

	List<Game> getAll();

	Game findById(Long id);

	Game findByToken(String token);
}
