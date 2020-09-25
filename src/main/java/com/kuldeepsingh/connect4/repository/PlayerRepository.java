package com.kuldeepsingh.connect4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuldeepsingh.connect4.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
