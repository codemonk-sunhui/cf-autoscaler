package com.pivotal.cf.broker.repositories.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pivotal.cf.broker.model.PlanMetadata;
import com.pivotal.cf.broker.model.PlanMetadataCost;
import com.pivotal.cf.broker.repositories.PlanMetadataCostRepository;

@Component
public class PlanMetadataCostRepositoryImpl implements PlanMetadataCostRepository{
	
	private static Map<String,PlanMetadataCost> PlanMetadataList = new HashMap<String,PlanMetadataCost>();

	public int countByPlanMetadata(PlanMetadata planMetadata) {
		if(planMetadata==null) return 0;
		
		int count = 0;
		Set<Entry<String, PlanMetadataCost>>  set = PlanMetadataList.entrySet();
		
		for (Entry<String, PlanMetadataCost> entry : set) {
			PlanMetadataCost cost = entry.getValue();
			String id = cost.getPlanMetadata().getId();
			if(planMetadata.getId().equals(id)) count++;
		}
		
		return count;
	}

	public int countByPlanMetadata(String planMetadataId) {
		if(planMetadataId==null) return 0;
		
		int count = 0;
		Set<Entry<String, PlanMetadataCost>>  set = PlanMetadataList.entrySet();
	
		for (Entry<String, PlanMetadataCost> entry : set) {
			PlanMetadataCost cost = entry.getValue();
			String id = cost.getPlanMetadata().getId();
			if(planMetadataId.equals(id)) count++;
		}
			
		return count;
	}

	public void save(PlanMetadataCost cost) {
		PlanMetadataList.put(cost.getId(), cost);
	}

	public PlanMetadataCost findOne(String id) {
		return PlanMetadataList.get(id);
	}

	public void delete(String id) {
		PlanMetadataList.remove(id);
	}
}
