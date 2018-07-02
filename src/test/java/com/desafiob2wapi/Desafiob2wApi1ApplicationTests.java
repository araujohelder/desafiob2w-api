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
import com.desafiob2wapi.repository.PlanetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Desafiob2wApi1ApplicationTests {
	
	 @Autowired
	 PlanetRepository repository;
	
	 @Before
     public void setUp() {

        repository.deleteAll();
       
        Planet alderaan = new Planet();
        alderaan.setName("Alderaan");
        alderaan.setClimate("temperate");
        alderaan.setGround("ocean");
        alderaan = repository.save(alderaan);
        
        Planet dagobah = new Planet();
        dagobah.setName("Dagobah");
        dagobah.setClimate("temperate");
        dagobah.setGround("ocean");
        dagobah = repository.save(dagobah);
        
        Planet kamino = new Planet();
        kamino.setName("Kamino");
        kamino.setClimate("temperate");
        kamino.setGround("ocean");
        kamino = repository.save(kamino);
     }
	 
	
	@Test
    public void setsIdOnSave() {
	 	Planet saturno = new Planet();
        saturno.setName("Saturno");
        saturno.setClimate("temperate");
        saturno.setGround("ocean");
        saturno = repository.save(saturno);
        Planet sat = repository.save(saturno);
        assertThat(sat.getId()).isNotNull();
    }

    @Test
    public void findsByLastName() {
        List<Planet> result = repository.findByName("Kamino");
        assertThat(result).hasSize(1).extracting("name").contains("Kamino");
    }

    @Test
    public void findAll() {
        List<Planet> result = repository.findAll();
        assertThat(result).hasSize(3);
    }
    
    @Test
    public void removePlanet() {
    	List<Planet> result = repository.findByName("Kamino");
    	Planet planet = result.get(0);
    	repository.deleteById(planet.getId());
    	List<Planet> result2 = repository.findByName("Kamino");
    	assertThat(result2).hasSize(0);
    }
}
