package com.kuldeepsingh.connect4.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.kuldeepsingh.connect4.dtos.GameMoveResponse;
import com.kuldeepsingh.connect4.dtos.GameStartResponse;
import com.kuldeepsingh.connect4.dtos.MoveInputPayload;
import com.kuldeepsingh.connect4.exceptions.InvalidInputException;
import com.kuldeepsingh.connect4.exceptions.InvalidUserMoveException;
import com.kuldeepsingh.connect4.model.Board;
import com.kuldeepsingh.connect4.model.Game;
import com.kuldeepsingh.connect4.model.Player;
import com.kuldeepsingh.connect4.model.State;
import com.kuldeepsingh.connect4.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;

	@Override
	public GameStartResponse start() {

		Game game = new Game();
		Player yellowPlayer = new Player();
		yellowPlayer.setColor("Yellow");
		Player redPlayer = new Player();
		redPlayer.setColor("Red");
		game.setRedPlayer(redPlayer);
		game.setYellowPlayer(yellowPlayer);
		Board board = new Board();
		board.setBaseColor("white");
		board.setBoardName("New Game");
		board.setMetrix(new String[6][7]);
		game.setBoard(board);
		String token = UUID.randomUUID().toString();
		game.setToken(token);
		game = gameRepository.save(game);
		return new GameStartResponse("Ready", game.getId(), token);
	}

	@Override
	public GameMoveResponse move(MoveInputPayload inputPayload) {
		GameMoveResponse response = new GameMoveResponse("Invalid", null, false);
		try {
			Game game = gameRepository.findById(inputPayload.getGameId()).orElse(null);
			int columIndex = inputPayload.getColumn();
			if (game != null && !game.getState().equals(State.CONCLUDED)) {
				String lastMovedBy = game.getLastMoveBy();
				game.setState(State.RUNNING);
				if (!StringUtils.isEmpty(lastMovedBy)
						&& lastMovedBy.equalsIgnoreCase(inputPayload.getPlayer().getName())) {
					throw new InvalidUserMoveException("You are moving out of turn, lets move other player.");
				}
				Board board = game.getBoard();
				String[][] metrix = board.getMetrix();
				for (int i = metrix.length - 1; i >= 0; i--) {
					String[] row = metrix[i];
					if (row[columIndex] == null) {
						game.setLastMoveBy(inputPayload.getPlayer().getName());
						response.setMessage("Valid");
						metrix[i][inputPayload.getColumn()] = inputPayload.getPlayer().getColor();
						boolean result = result(metrix, columIndex, i, inputPayload.getPlayer());
						response.setWinningMove(result);
						if (result)
							game.setState(State.CONCLUDED);
						gameRepository.save(game);
						break;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidInputException("Invalid valiue of column is provided");
		}
		return response;
	}

	@Override
	public boolean result(String[][] metrix, int colIndex, int rowIndex, Player player) {
		int indexes[] = { 3, 2, 1, 0, 1, 2, 3 };
		int count = 0;
		for (int i = 0; i < indexes.length; i++) {
			int k = rowIndex - indexes[i];
			int l = colIndex - indexes[i];
			if (k > 0 && l > 0) {
				if (!StringUtils.isEmpty(metrix[k][l]) && metrix[k][l].equalsIgnoreCase(player.getColor())) {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}

		count = 0;
		for (int i = 0; i < indexes.length; i++) {
			int k = rowIndex;
			int l = colIndex - indexes[i];
			if (k >= 0 && k <= 5 && l >= 0) {
				if (!StringUtils.isEmpty(metrix[k][l]) && metrix[k][l].equalsIgnoreCase(player.getColor())) {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}

		count = 0;
		for (int i = 0; i < indexes.length; i++) {
			int k = rowIndex - indexes[i];
			int l = colIndex;
			if (k >= 0 && l >= 0 && l <= 6) {
				if (!StringUtils.isEmpty(metrix[k][l]) && metrix[k][l].equalsIgnoreCase(player.getColor())) {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}

		count = 0;
		for (int i = 0; i < indexes.length; i++) {
			int k = rowIndex + indexes[i];
			int l = colIndex - indexes[i];
			if (k >= 0 && k <= 5 && l >= 0) {
				if (!StringUtils.isEmpty(metrix[k][l]) && metrix[k][l].equalsIgnoreCase(player.getColor())) {
					count++;
					if (count == 4) {
						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public List<Game> getAll() {
		return gameRepository.findAll();
	}

	@Override
	public Game findById(Long id) {
		Game game = gameRepository.findById(id).orElse(null);
		return game;
	}

	@Override
	public Game findByToken(String token) {
		return gameRepository.findByToken(token);
	}
}
