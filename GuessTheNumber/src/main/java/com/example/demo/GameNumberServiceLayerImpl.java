package com.example.demo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameNumberServiceLayerImpl {
	
	@Autowired
	GameServiceDaoImpl gameServiceDao;
	
	@Autowired
	RoundServiceDaoImpl roundServiceDao;

	public void createGame() throws Exception {
		
		//Create a game which stores an answer with 4 random digits
		Game game = new Game();
		String answer = "";
		boolean validDigits = false;
		int[] digits = new int[4];
		
		while(!validDigits) {
			for(int i = 0; i < 4; i++) {
				Random random = new Random();
				int randomDigit = random.nextInt(10);
				answer = answer + randomDigit;
				digits[i] = randomDigit;
			}
			validDigits = true;
			
			//Check whether all digits are different
			for(int i = 0; i < digits.length; i++) {
				for(int j = 0; j < digits.length; j++) {
					if((digits[i] == digits[j]) && (i != j)) {
						validDigits = false;
						break;
					}
				}
			}
		}
		
		game.setAnswer(answer);
		game.setStatus(true);
		gameServiceDao.createGame(game);
	}
	public String createGuess(String guess, Integer gameId) {
		
		//Create a new round and insert the user guess into it
		//Insert current time to track the time that the user inserts the guess.
		Round round = new Round();
		LocalTime timestamp = LocalTime.now();
		round.setGuess(guess);
		round.setTimeOfGuess(timestamp);
				
		//Find a game which the user is playing now
		Game game = gameServiceDao.getGameForServiceLayer(gameId);
		String gameAnswer = game.getAnswer();
				
				//If the game is closed then return this message
	    if(game.getStatus() == false) {
				return "Sorry but this game is currently closed, please create a new game";
		}
			
		//Track the number of parial and correct guesses
		int partiallyCorrect = 0;
		int exactlyCorrect = 0;
		List<Integer> guessDigits = new ArrayList<Integer>();
		List<Integer> answerDigits = new ArrayList<Integer>();
		
		//Check whether each guess is partially or fully correct
		for(int i = 0; i < gameAnswer.length(); i++) {
			guessDigits.add(Integer.parseInt(guess.substring(i,i+1)));
			answerDigits.add(Integer.parseInt(gameAnswer.substring(i,i+1)));
		}
		
		for(int i = 0; i < guessDigits.size(); i++) {
			for(int j = 0; j < answerDigits.size(); j++) {
				if(guessDigits.get(i) == answerDigits.get(j) && i == j) {
					exactlyCorrect += 1;
				}
				else if(guessDigits.get(i) == answerDigits.get(j)) {
					partiallyCorrect += 1;
				}
			}
		}
		
		//Get the result and save the round 
		String result = "e:"+exactlyCorrect+"p:"+partiallyCorrect;
		round.setGuessResult(result);
		round.setGame(game);
		roundServiceDao.createRound(round);
				
		//If the user guessed all four digits correctly then 
		if(exactlyCorrect == 4) {
			game.setStatus(false);
			gameServiceDao.createGame(game);
			return "Congratulations, you guessed correctly!";
		}
		else {
			return "Sorry you guessed incorrectly please try again, your result is " + result; 
		}
	}

	//Get a list of all games
	public List<Game> getAllGames(){
		return gameServiceDao.getAllGames();
		
	}
	
	//Get a certain game by its Id
	public Game getGame(Integer gameId) {
		return gameServiceDao.getGameForUser(gameId);
	}
	
	//Get a list of all rounds based on Id
	public List<Round> getAllRounds(Integer gameId){
		return roundServiceDao.getAllRounds(gameId);
	}
}
