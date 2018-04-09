package com.pivotal.cf.broker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class PlanMetadata {

	@JsonIgnore
	private String id;

	@JsonProperty("displayName")
	private String displayName;

	@JsonIgnore
	private Plan plan;

	@JsonSerialize
	@JsonProperty("bullets")
	private List<String> bullets;

	@JsonSerialize
	@JsonProperty("costs")
	private List<PlanMetadataCost> costs = new ArrayList<PlanMetadataCost>();

	@JsonSerialize
	@JsonProperty("pool")
	private List<PlanMetadataRes> pool = new ArrayList<PlanMetadataRes>();

	protected Map<String, String> other = new HashMap<String, String>();

	@JsonAnyGetter
	public Map<String, String> any() {
		return other;
	}

	@JsonAnySetter
	public void set(String name, String value) {
		other.put(name, value);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getBullets() {
		return bullets;
	}

	public void setBullets(List<String> bullets) {
		this.bullets = bullets;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Map<String, String> getOther() {
		return other;
	}

	public void setOther(Map<String, String> other) {
		this.other = other;
	}

	public List<PlanMetadataCost> getCosts() {
		return costs;
	}

	public void setCosts(List<PlanMetadataCost> costs) {
		if (costs == null) {
			this.costs = new ArrayList<PlanMetadataCost>();
		} else {
			this.costs = costs;
		}
	}

	public List<PlanMetadataRes> getPool() {
		return pool;
	}

	public void setPool(List<PlanMetadataRes> pool) {
		if (pool == null) {
			this.pool = new ArrayList<PlanMetadataRes>();
		} else {
			this.pool = pool;
		}
	}

}
