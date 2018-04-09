package com.pivotal.cf.broker.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Plan {

	@JsonSerialize
	@JsonProperty("id")
	private String id;

	@JsonSerialize
	@JsonProperty("name")
	private String name;

	@JsonSerialize
	@JsonProperty("description")
	private String description;

	@JsonProperty("metadata")
	private PlanMetadata metadata;

	private ServiceDefinition serviceDefinition;

	@JsonIgnore
	private Date createTime = new Date();

	@JsonIgnore
	private Date updateTime = new Date();

	public Plan() {
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PlanMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(PlanMetadata metadata) {
		this.metadata = metadata;
	}

	public ServiceDefinition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(ServiceDefinition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
