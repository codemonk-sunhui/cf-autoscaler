package com.pivotal.cf.broker.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class PlanMetadataRes {

	@JsonSerialize
	@JsonProperty("id")
	private String id = UUID.randomUUID().toString();

	private PlanMetadata planMetadata;

	public PlanMetadataRes() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PlanMetadata getPlanMetadata() {
		return planMetadata;
	}

	public void setPlanMetadata(PlanMetadata sid) {
		this.planMetadata = sid;
	}

}
