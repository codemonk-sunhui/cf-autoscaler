package com.pivotal.cf.broker.repositories;


import com.pivotal.cf.broker.model.PlanMetadata;
import com.pivotal.cf.broker.model.PlanMetadataCost;


public interface PlanMetadataCostRepository {
	

	public int countByPlanMetadata(PlanMetadata planMetadata) ;

	public int countByPlanMetadata(String planMetadataId) ;

	public void save(PlanMetadataCost cost) ;

	public PlanMetadataCost findOne(String id) ;

	public void delete(String id) ;
}
