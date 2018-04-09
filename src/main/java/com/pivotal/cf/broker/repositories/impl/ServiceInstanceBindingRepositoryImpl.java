package com.pivotal.cf.broker.repositories.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pivotal.cf.broker.model.ServiceInstanceBinding;
import com.pivotal.cf.broker.repositories.ServiceInstanceBindingRepository;

@Component
public class ServiceInstanceBindingRepositoryImpl  implements ServiceInstanceBindingRepository{
	
	private static Map<String,ServiceInstanceBinding> map = new HashMap<String,ServiceInstanceBinding>();
	

	public int countByServiceInstanceId(String serviceInstanceId) {
		if(serviceInstanceId==null) return 0;
		
		int count = 0;
		Set<Entry<String, ServiceInstanceBinding>>  set = map.entrySet();
		
		for (Entry<String, ServiceInstanceBinding> entry : set) {
			ServiceInstanceBinding sib = entry.getValue();
			String id = sib.getServiceInstanceId();
			if(serviceInstanceId.equals(id)) count++;
		}
		
		return count;
	}

	public boolean exists(String bindingId) {
		return map.containsKey(bindingId);
	}

	public ServiceInstanceBinding save(ServiceInstanceBinding binding) {
		map.put(binding.getId(), binding);
		return binding;
	}

	public ServiceInstanceBinding findOne(String serviceBindingId) {
		return map.get(serviceBindingId);
	}

	public void delete(ServiceInstanceBinding binding) {
		map.remove(binding.getId());
	}

	public List<ServiceInstanceBinding> findAll() {
		List<ServiceInstanceBinding> sdList = new ArrayList<ServiceInstanceBinding>();
		
		Set<Entry<String, ServiceInstanceBinding>>  set = map.entrySet();
		
		for (Entry<String, ServiceInstanceBinding> entry : set) {
			ServiceInstanceBinding sib = entry.getValue();
			sdList.add(sib);
		}
		
		return sdList;
	}
}
