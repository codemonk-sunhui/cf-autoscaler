package com.pivotal.cf.broker.repositories;

import java.util.List;

import com.pivotal.cf.broker.model.ServiceInstance;

public interface ServiceInstanceRepository {

	public int countByPlanId(String planId) ;


	public ServiceInstance save(ServiceInstance instance) ;

	public boolean exists(String serviceInstanceId);


	public ServiceInstance findOne(String serviceInstanceId) ;


	public void delete(String serviceInstanceId) ;


	public List<ServiceInstance> findAll() ;
}
