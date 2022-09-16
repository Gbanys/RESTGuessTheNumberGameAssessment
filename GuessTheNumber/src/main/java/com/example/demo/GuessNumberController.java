package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class GuessNumberController {
	
	@Autowired
	GameNumberServiceLayerImpl gameNumberServiceLayer;
	
	//Create a new game
	@PostMapping("/begin")
	@ResponseStatus(HttpStatus.CREATED)
	public void startGame() throws Exception {
		gameNumberServiceLayer.createGame();
	}
	
	//Create a new guess
	@PostMapping("/guess")
	public String guess(@RequestParam(value="guess") String guess,
			@RequestParam(value="gameId") Integer gameId) {
		
		return gameNumberServiceLayer.createGuess(guess, gameId);
	}
	
	//Get all games
	@GetMapping("/game")
	public List<Game> getAllGames() {
		return gameNumberServiceLayer.getAllGames();
	}
	
	//Get a particular game by id
	@GetMapping("/game/{gameId}")
	public Game getGame(@PathVariable Integer gameId) {
		return gameNumberServiceLayer.getGame(gameId);
	}
	
	//Get all the rounds belonging to a game
	@GetMapping("/rounds/{gameId}")
	public List<Round> getAllRounds(@PathVariable Integer gameId){
		return gameNumberServiceLayer.getAllRounds(gameId);
	}
}
