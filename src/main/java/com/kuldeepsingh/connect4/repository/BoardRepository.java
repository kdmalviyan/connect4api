package com.kuldeepsingh.connect4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuldeepsingh.connect4.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
