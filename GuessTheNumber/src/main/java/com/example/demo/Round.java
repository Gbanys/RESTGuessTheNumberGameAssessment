package com.example.demo;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Round {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roundId;
	private String guess;
	private LocalTime timeOfGuess;
	private String guessResult;
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
    @JoinColumn(name="gameId", nullable=false)
	private Game game;
	
}
