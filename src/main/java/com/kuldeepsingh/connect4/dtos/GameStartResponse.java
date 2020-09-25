package com.kuldeepsingh.connect4.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class GameStartResponse {
	private String message;
	private Long gameId;
	private String token;
}
