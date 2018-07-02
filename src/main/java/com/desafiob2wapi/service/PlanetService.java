package com.desafiob2wapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.desafiob2wapi.model.Planet;
import com.desafiob2wapi.repository.PlanetRepository;
import com.desafiob2wapi.repository.planet.filter.PlanetFilter;

@Service
public class PlanetService {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	public Planet findByid(String id) {
		Optional<Planet> planet = planetRepository.findById(id);
		if (planet.isPresent() ) {
			return planet.get();
		}
		throw new EmptyResultDataAccessException(1);
	}
	
	public List<Planet> findAll() {
		 return  planetRepository.findAll();
	}
	
	public List<Planet> search (PlanetFilter planetFilter) {
		List<Planet> planets = null;
		if (planetFilter.getName() != null) {
			planets = planetRepository.findByName(planetFilter.getName());
			return planets;
		}
		planets = planetRepository.findAll();
		return planets;
	} 
	
	public void delete(String id) {
		Planet planet = this.findByid(id);
		planetRepository.delete(planet);
	}

	public Planet save(Planet planet)  {
		return planetRepository.save(planet);
	}

	public Planet update(String id, Planet planet) {
		Planet planetReturn = this.findByid(id);
		BeanUtils.copyProperties(planet, planetReturn, "id");
		this.planetRepository.save(planetReturn);
		return planetReturn;
	}
}
