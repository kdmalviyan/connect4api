package com.kuldeepsingh.connect4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuldeepsingh.connect4.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	Game findByToken(String token);

}
