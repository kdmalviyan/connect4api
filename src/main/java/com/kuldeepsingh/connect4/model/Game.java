package com.kuldeepsingh.connect4.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "Game_Table")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String lastMoveBy;

	@OneToOne(cascade = CascadeType.ALL)
	private Player yellowPlayer;

	@OneToOne(cascade = CascadeType.ALL)
	private Player redPlayer;

	@OneToOne(cascade = CascadeType.ALL)
	private Board board;

	private State state = State.STARTED;

	private String token;
}
