package com.desafiob2wapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.desafiob2wapi.model.Planet;
import com.desafiob2wapi.repository.planet.filter.PlanetFilter;
import com.desafiob2wapi.service.PlanetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Desafiob2wApi1ApplicationTests {
	
	 @Autowired
	 PlanetService planetService;
	
	 @Before
     public void setUp() {

        planetService.deleteAll();
       
        Planet alderaan = new Planet();
        alderaan.setName("Alderaan");
        alderaan.setClimate("temperate");
        alderaan.setGround("ocean");
        alderaan = planetService.save(alderaan);
        
        Planet dagobah = new Planet();
        dagobah.setName("Dagobah");
        dagobah.setClimate("temperate");
        dagobah.setGround("ocean");
        dagobah = planetService.save(dagobah);
        
        Planet kamino = new Planet();
        kamino.setName("Kamino");
        kamino.setClimate("temperate");
        kamino.setGround("ocean");
        kamino = planetService.save(kamino);
     }
	 
	
	@Test
    public void setsIdOnSave() {
	 	Planet saturno = new Planet();
        saturno.setName("Saturno");
        saturno.setClimate("temperate");
        saturno.setGround("ocean");
        saturno = planetService.save(saturno);
        Planet sat = planetService.save(saturno);
        assertThat(sat.getId()).isNotNull();
    }

    @Test
    public void findsBytName() {
    	PlanetFilter planetFilter = new PlanetFilter();
    	planetFilter.setName("Kamino");
        List<Planet> result = planetService.search((planetFilter));
        assertThat(result).hasSize(1).extracting("name").contains("Kamino");
    }

    @Test
    public void findAll() {
        List<Planet> result = planetService.findAll();
        assertThat(result).hasSize(3);
    }
    
    @Test
    public void removePlanet() {
    	PlanetFilter planetFilter = new PlanetFilter();
    	planetFilter.setName("Kamino");
    	List<Planet> result = planetService.search(planetFilter);
    	Planet planet = result.get(0);
    	planetService.delete(planet.getId());
    	List<Planet> result2 = planetService.search(planetFilter);
    	assertThat(result2).hasSize(0);
    }
}
