package com.example.demo;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoundServiceDaoImpl implements RoundServiceDao{

	@Autowired
	RoundRepository roundRepository;
	
	@Autowired
	GameRepository gameRepository;
	
	@Override
	public void createRound(Round round) {
		roundRepository.save(round);
	}
	
	//Get all rounds for a particular game
	@Override
	public List<Round> getAllRounds(Integer gameId){
		Optional<Game> games = gameRepository.findById(gameId);
		Game game = games.get();
		List<Round> rounds = roundRepository.findByGame(game);
		return rounds;	
	}
}
