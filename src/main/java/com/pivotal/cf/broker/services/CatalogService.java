package com.pivotal.cf.broker.services;

import java.util.List;

import com.pivotal.cf.broker.model.ServiceDefinition;

public interface CatalogService {

	ServiceDefinition createServiceDefinition(
			ServiceDefinition serviceDefinition);

	boolean deleteServiceDefinition(String serviceDefinitionId);

	List<ServiceDefinition> listServiceDefinition();

	int countServiceDefinition();

}
