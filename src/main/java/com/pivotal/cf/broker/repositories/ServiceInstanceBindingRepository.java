package com.pivotal.cf.broker.repositories;

import java.util.List;

import com.pivotal.cf.broker.model.ServiceInstanceBinding;

public interface ServiceInstanceBindingRepository  {

	public int countByServiceInstanceId(String serviceInstanceId);

	public boolean exists(String bindingId);

	public ServiceInstanceBinding save(ServiceInstanceBinding binding) ;

	public ServiceInstanceBinding findOne(String serviceBindingId) ;

	public void delete(ServiceInstanceBinding binding) ;

	public List<ServiceInstanceBinding> findAll() ;
}
