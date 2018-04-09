package com.pivotal.cf.broker.repositories;

import java.util.List;

import com.pivotal.cf.broker.model.ServiceDefinition;

public interface ServiceDefinitionRepository {

	public ServiceDefinition save(ServiceDefinition serviceDefinition) ;

	public List<ServiceDefinition> findAll() ;

	public boolean exists(String serviceDefinitionId) ;

	public ServiceDefinition findOne(String serviceDefinitionId) ;

	public void delete(String serviceDefinitionId) ;

	public int count() ;
}
