package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

//Create a DAO interface
@Service
public interface RoundServiceDao {

	void createRound(Round round);
	
	List<Round> getAllRounds(Integer gameId);
}
