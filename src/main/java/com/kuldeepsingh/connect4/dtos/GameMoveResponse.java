package com.kuldeepsingh.connect4.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class GameMoveResponse {
	private String message;
	private String winner;
	private boolean isWinningMove;
}
