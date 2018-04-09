package com.pivotal.cf.broker.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pivotal.cf.broker.model.ServiceInstance;
import com.pivotal.cf.broker.repositories.ServiceInstanceRepository;
import com.pivotal.cf.broker.utils.StringUtils;

@Component
public class ServiceInstanceRepositoryImpl implements ServiceInstanceRepository{
	
	private static Map<String,ServiceInstance> map = new HashMap<String,ServiceInstance>();
	

	public int countByPlanId(String planId) {
		if(planId==null) return 0;
		
		int count = 0;
		Set<Entry<String, ServiceInstance>>  set = map.entrySet();
		
		for (Entry<String, ServiceInstance> entry : set) {
			ServiceInstance si = entry.getValue();
			String id = si.getPlanId();
			if(planId.equals(id)) count++;
		}
		
		return count;
	}


	public ServiceInstance save(ServiceInstance instance) {
		map.put(instance.getId(), instance);
		return instance;
	}

	public boolean exists(String serviceInstanceId) {
		return map.containsKey(serviceInstanceId);
	}


	public ServiceInstance findOne(String serviceInstanceId) {
		return map.get(serviceInstanceId);
	}


	public void delete(String serviceInstanceId) {
		map.remove(serviceInstanceId);
	}


	public List<ServiceInstance> findAll() {
		return StringUtils.valueList(map);
	}
}
