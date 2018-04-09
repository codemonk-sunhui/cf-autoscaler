package com.pivotal.cf.broker.repositories.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pivotal.cf.broker.model.Plan;
import com.pivotal.cf.broker.model.ServiceDefinition;
import com.pivotal.cf.broker.repositories.PlanRepository;

@Component
public class PlanRepositoryImpl implements PlanRepository{
	
	private static Map<String,Plan> map = new HashMap<String,Plan>();
	

	public int countByServiceDefinition(ServiceDefinition definition) {
		if(definition==null) return 0;
		
		int count = 0;
		Set<Entry<String, Plan>>  set = map.entrySet();
		
		for (Entry<String, Plan> entry : set) {
			Plan plan = entry.getValue();
			String id = plan.getServiceDefinition().getId();
			if(definition.getId().equals(id)) count++;
		}
		
		return count;
	}

	public Plan save(Plan plan) {
		map.put(plan.getId(), plan);
		return plan;
	}

	public Plan findOne(String planId) {
		return map.get(planId);
	}

	public void delete(Plan plan) {
		map.remove(plan.getId());
	}
}
