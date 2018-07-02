package com.desafiob2wapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.desafiob2wapi.event.ResourceCreatedEvent;
import com.desafiob2wapi.model.Film;
import com.desafiob2wapi.model.Planet;
import com.desafiob2wapi.repository.planet.filter.PlanetFilter;
import com.desafiob2wapi.service.PlanetService;

@RestController
@RequestMapping("/planeta")
public class PlanetResource {
	
	@Autowired
	private PlanetService planetService;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Planet> findById(@PathVariable String codigo)  {
		Planet planetReturned = planetService.findByid(codigo);
		return planetReturned != null ? ResponseEntity.ok(planetReturned) : ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Planet>> findByName(PlanetFilter planetFilter)  {
		List<Planet> planets = planetService.search(planetFilter);
		return planets != null ? ResponseEntity.ok(planets) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Planet> save(@Valid @RequestBody Planet planet, HttpServletResponse response) {
		Planet planetCreated =  planetService.save(planet);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, planetCreated.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(planetCreated);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Planet> remove(@PathVariable String id){
		planetService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Planet>atualizar(@PathVariable String id, @Validated @RequestBody Planet planet) {
		Planet planetSave = planetService.update(id, planet);
		return ResponseEntity.ok().body(planetSave);
	}
	
	@GetMapping("/films")
	public ResponseEntity<Film>searchFilms() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<Film> film = restTemplate.exchange("http://swapi.co/api/films/6/",HttpMethod.GET,entity,Film.class);
		System.out.println(film.toString());
		return ResponseEntity.noContent().build();
	}
}
