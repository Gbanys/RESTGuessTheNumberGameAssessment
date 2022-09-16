package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceDaoImpl implements GameServiceDao{

	@Autowired
	GameRepository gameRepository;
	
	@Override
	public void createGame(Game game) {
		gameRepository.save(game);
	}
	
	@Override
	public List<Game> getAllGames() {
		
		/*Get all games, but if a game is currently in progress then
		 * make all the answers of the game invisible
		 */
		
		List<Game> games = gameRepository.findAll();
		List<Game> updatedGames = new ArrayList<Game>();
		
		for(Game game : games) {
			if(game.getStatus() == true) {
				game.setAnswer("Invisible");
			}
			updatedGames.add(game);
		}
		return updatedGames;
	}
	
	
	//Get a certain game by id
	@Override
	public Game getGameForServiceLayer(Integer gameId) {
		Optional<Game> games = gameRepository.findById(gameId);
		Game game = games.get();
		return game;
	}
	
	/*Get a certain game by id however if the game is in progress then
	do not display its answer
	*/
	@Override
	public Game getGameForUser(Integer gameId) {
		Optional<Game> games = gameRepository.findById(gameId);
		Game game = games.get();
		boolean isInProgress = game.getStatus();
		
		if(isInProgress) {
			game.setAnswer("Invisible");
		}
		return game;
	}

}
