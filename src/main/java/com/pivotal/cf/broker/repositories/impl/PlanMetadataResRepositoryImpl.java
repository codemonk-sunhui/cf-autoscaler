package com.pivotal.cf.broker.repositories.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pivotal.cf.broker.model.PlanMetadata;
import com.pivotal.cf.broker.model.PlanMetadataRes;
import com.pivotal.cf.broker.repositories.PlanMetadataResRepository;

@Component
public class PlanMetadataResRepositoryImpl implements PlanMetadataResRepository{
	
	private static Map<String,PlanMetadataRes> map = new HashMap<String,PlanMetadataRes>();

	public int countByPlanMetadata(PlanMetadata planMetadata){
		if(planMetadata==null) return 0;
		
		int count = 0;
		Set<Entry<String, PlanMetadataRes>>  set = map.entrySet();
		
		for (Entry<String, PlanMetadataRes> entry : set) {
			PlanMetadataRes res = entry.getValue();
			String id = res.getPlanMetadata().getId();
			if(planMetadata.getId().equals(id)) count++;
		}
		
		return count;
	}

	public void save(PlanMetadataRes res){
		map.put(res.getId(), res);
	}

	public void delete(PlanMetadataRes res){
		map.remove(res.getId());
	}
	
	public void delete(String id){
		map.remove(id);
	}

	public PlanMetadataRes findOne(String id){
		return map.get(id);
	}
}
