package com.desafiob2wapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {
	
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
	 public String toString() {
	        return "Film{" +
	                "Titulo='" + this.title + '\'' +
	                '}';
	    }
}
