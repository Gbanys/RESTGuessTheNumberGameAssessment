package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoundRepository extends CrudRepository<Round, Integer>{

	Optional<Round> findById(Integer roundId);
	
	List<Round> findByGame(Game game);
	
}
