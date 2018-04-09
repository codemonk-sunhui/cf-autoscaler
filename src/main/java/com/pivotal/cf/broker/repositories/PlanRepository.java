package com.pivotal.cf.broker.repositories;

import com.pivotal.cf.broker.model.Plan;
import com.pivotal.cf.broker.model.ServiceDefinition;


public interface PlanRepository {

	public int countByServiceDefinition(ServiceDefinition definition) ;

	public Plan save(Plan plan) ;

	public Plan findOne(String planId) ;

	public void delete(Plan plan);
}
