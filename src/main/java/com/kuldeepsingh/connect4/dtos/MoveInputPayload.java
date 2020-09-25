package com.kuldeepsingh.connect4.dtos;

import com.kuldeepsingh.connect4.model.Player;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MoveInputPayload {
	private int column;
	private Long gameId;
	private Player player;
}
