package com.example.demo;

import java.util.List;

public interface GameNumberServiceLayer {

	void createGame(Game game);
	String createGuess(String guess, Integer gameId);
	List<Game> getAllGames();
	Game getGame();
	List<Round> getAllRounds();
	
}
