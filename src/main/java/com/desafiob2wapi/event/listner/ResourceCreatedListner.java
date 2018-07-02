package com.desafiob2wapi.event.listner;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafiob2wapi.event.ResourceCreatedEvent;


@Component
public class ResourceCreatedListner implements ApplicationListener<ResourceCreatedEvent>{

	@Override
	public void onApplicationEvent(ResourceCreatedEvent resourceCreated) {
		HttpServletResponse response = resourceCreated.getResponse();
		String id = resourceCreated.getId();
		addHeaderLocation(response, id);
	}

	private void addHeaderLocation(HttpServletResponse response, String id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.addHeader("Location", uri.toString());
	}

}
