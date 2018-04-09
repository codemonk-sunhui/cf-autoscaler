package com.pivotal.cf.broker.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ServiceInstance {

	@JsonSerialize
	@JsonProperty("service_instance_id")
	private String id;

	@JsonSerialize
	@JsonProperty("service_id")
	private String serviceDefinitionId;

	@JsonSerialize
	@JsonProperty("plan_id")
	private String planId;

	@JsonSerialize
	@JsonProperty("organization_guid")
	private String organizationGuid;

	@JsonSerialize
	@JsonProperty("space_guid")
	private String spaceGuid;

	@JsonSerialize
	@JsonProperty("dashboard_url")
	private String dashboardUrl;

	@JsonSerialize
	@JsonProperty("res_id")
	private String resId;
	

	@JsonIgnore
	private Map<String, String> config = new HashMap<String, String>();

	@SuppressWarnings("unused")
	private ServiceInstance() {
	}

	public ServiceInstance(String id, String serviceDefinitionId,
			String planId, String organizationGuid, String spaceGuid,
			String dashboardUrl) {
		this(id, serviceDefinitionId, planId, organizationGuid, spaceGuid,
				dashboardUrl, null);
	}

	public ServiceInstance(String id, String serviceDefinitionId,
			String planId, String organizationGuid, String spaceGuid,
			String dashboardUrl, String resId) {
		setId(id);
		setServiceDefinitionId(serviceDefinitionId);
		setPlanId(planId);
		setOrganizationGuid(organizationGuid);
		setSpaceGuid(spaceGuid);
		setDashboardUrl(dashboardUrl);

		setResId(resId);
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public String getServiceDefinitionId() {
		return serviceDefinitionId;
	}

	private void setServiceDefinitionId(String serviceDefinitionId) {
		this.serviceDefinitionId = serviceDefinitionId;
	}

	public String getPlanId() {
		return planId;
	}

	private void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getOrganizationGuid() {
		return organizationGuid;
	}

	private void setOrganizationGuid(String organizationGuid) {
		this.organizationGuid = organizationGuid;
	}

	public String getSpaceGuid() {
		return spaceGuid;
	}

	private void setSpaceGuid(String spaceGuid) {
		this.spaceGuid = spaceGuid;
	}

	public String getDashboardUrl() {
		return dashboardUrl;
	}

	private void setDashboardUrl(String dashboardUrl) {
		this.dashboardUrl = dashboardUrl;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}
}
