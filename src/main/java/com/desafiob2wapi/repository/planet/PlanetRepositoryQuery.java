package com.desafiob2wapi.repository.planet;

import java.util.List;

import com.desafiob2wapi.model.Planet;
import com.desafiob2wapi.repository.planet.filter.PlanetFilter;


public interface PlanetRepositoryQuery {
	
	public List<Planet> search(PlanetFilter planetFilter);

}
