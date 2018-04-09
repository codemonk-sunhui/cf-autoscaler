package com.pivotal.cf.broker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ServiceDefinition {

	@JsonSerialize
	@JsonProperty("id")
	private String id;

	@JsonSerialize
	@JsonProperty("name")
	private String name;

	@JsonSerialize
	@JsonProperty("description")
	private String description;

	@JsonSerialize
	@JsonProperty("bindable")
	private boolean bindable;

	@JsonSerialize
	@JsonProperty("plans")
	private List<Plan> plans = new ArrayList<Plan>();

	@JsonSerialize
	@JsonProperty("tags")
	private List<String> tags = new ArrayList<String>();

	@JsonSerialize
	@JsonProperty("metadata")
	private Map<String, String> metadata = new HashMap<String, String>();

	@JsonSerialize
	@JsonProperty("requires")
	private List<String> requires = new ArrayList<String>();

	@JsonIgnore
	private Date createTime = new Date();

	@JsonIgnore
	private Date updateTime = new Date();

	public ServiceDefinition() {
	}

	public ServiceDefinition(String id) {
		this.id = id;
	}

	public ServiceDefinition(String id, String name, String description,
			boolean bindable, List<Plan> plans) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.bindable = bindable;
		this.setPlans(plans);
	}

	public ServiceDefinition(String id, String name, String description,
			boolean bindable, List<Plan> plans, List<String> tags,
			Map<String, String> metadata, List<String> requires) {
		this(id, name, description, bindable, plans);
		setTags(tags);
		setMetadata(metadata);
		setRequires(requires);
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

	public boolean isBindable() {
		return bindable;
	}

	public void setBindable(boolean bindable) {
		this.bindable = bindable;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		if (plans == null) {
			this.plans = new ArrayList<Plan>();
		} else {
			this.plans = plans;
		}
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		if (tags == null) {
			this.tags = new ArrayList<String>();
		} else {
			this.tags = tags;
		}
	}

	public List<String> getRequires() {
		return requires;
	}

	public void setRequires(List<String> requires) {
		if (requires == null) {
			this.requires = new ArrayList<String>();
		} else {
			this.requires = requires;
		}
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		if (metadata == null) {
			this.metadata = new HashMap<String, String>();
		} else {
			this.metadata = metadata;
		}
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
	
	public void addPlan(Plan plan) {
		if(this.plans == null) {
			plans = new ArrayList<Plan>();
		}
		
		plans.add(plan);
	}
}
