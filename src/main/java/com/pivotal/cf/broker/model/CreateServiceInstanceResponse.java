package com.pivotal.cf.broker.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class CreateServiceInstanceResponse {

	private ServiceInstance instance;

	public CreateServiceInstanceResponse() {
	}

	public CreateServiceInstanceResponse(ServiceInstance instance) {
		this.instance = instance;
	}

	@JsonSerialize
	@JsonProperty("dashboard_url")
	public String getDashboardUrl() {
		return instance.getDashboardUrl();
	}

}
