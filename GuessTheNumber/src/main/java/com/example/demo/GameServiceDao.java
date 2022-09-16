package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface GameServiceDao {

	void createGame(Game game);
	
	List<Game> getAllGames();
	
	Game getGameForServiceLayer(Integer gameId);
	Game getGameForUser(Integer gameId);
}
