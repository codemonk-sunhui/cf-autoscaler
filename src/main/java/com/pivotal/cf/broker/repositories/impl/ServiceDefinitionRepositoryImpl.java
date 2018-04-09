package com.pivotal.cf.broker.repositories.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pivotal.cf.broker.model.ServiceDefinition;
import com.pivotal.cf.broker.repositories.ServiceDefinitionRepository;

@Component
public class ServiceDefinitionRepositoryImpl implements ServiceDefinitionRepository {
	
	private static Map<String,ServiceDefinition> map = new HashMap<String,ServiceDefinition>();
	

	public ServiceDefinition save(ServiceDefinition serviceDefinition) {
		map.put(serviceDefinition.getId(), serviceDefinition);
		return serviceDefinition;
	}

	public List<ServiceDefinition> findAll() {
		List<ServiceDefinition> sdList = new ArrayList<ServiceDefinition>();
		
		Set<Entry<String, ServiceDefinition>>  set = map.entrySet();
		
		for (Entry<String, ServiceDefinition> entry : set) {
			ServiceDefinition sd = entry.getValue();
			sdList.add(sd);
		}
		
		return sdList;
	}

	public boolean exists(String serviceDefinitionId) {
		return map.containsKey(serviceDefinitionId);
	}

	public ServiceDefinition findOne(String serviceDefinitionId) {
		return map.get(serviceDefinitionId);
		
	}

	public void delete(String serviceDefinitionId) {
		map.remove(serviceDefinitionId);
	}

	public int count() {
		return map.size();
	}
}
