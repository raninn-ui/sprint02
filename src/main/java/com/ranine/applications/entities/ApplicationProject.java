package com.ranine.applications.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nomApp", types = { Application.class })
public interface ApplicationProject {

	public String getNomApp();
}
