package com.desafiob2wapi.model;


import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planet")
public class Planet {
	
	@Id
	private String id;
	@NotNull
	private String name;
	
	private String climate;
	
	private String ground;
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getGround() {
		return ground;
	}
	public void setGround(String ground) {
		this.ground = ground;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Planet[id=%s, name='%s', climate='%s']",
                id, this.name, this.climate);
    }
}
