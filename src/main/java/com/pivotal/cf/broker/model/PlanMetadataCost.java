package com.pivotal.cf.broker.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class PlanMetadataCost {

	@JsonIgnore
	private String id = UUID.randomUUID().toString();

	@JsonProperty("unit")
	private String unit;

	private PlanMetadata planMetadata;

	@JsonSerialize
	@JsonProperty("amount")
	private Map<String, BigDecimal> amount = new HashMap<String, BigDecimal>();

	public PlanMetadataCost() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Map<String, BigDecimal> getAmount() {
		return amount;
	}

	public void setAmount(Map<String, BigDecimal> amount) {
		if (amount == null) {
			this.amount = new HashMap<String, BigDecimal>();
		} else {
			this.amount = amount;
		}
	}

	public PlanMetadata getPlanMetadata() {
		return planMetadata;
	}

	public void setPlanMetadata(PlanMetadata sid) {
		this.planMetadata = sid;
	}

}
