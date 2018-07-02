package com.desafiob2wapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.desafiob2wapi.model.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {
	
	 public List<Planet> findByName(String firstName);

}
