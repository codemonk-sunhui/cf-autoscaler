package com.pivotal.cf.broker.repositories;

import com.pivotal.cf.broker.model.PlanMetadata;
import com.pivotal.cf.broker.model.PlanMetadataRes;

public interface PlanMetadataResRepository {
	
	public int countByPlanMetadata(PlanMetadata planMetadata);

	public void save(PlanMetadataRes res);

	public void delete(PlanMetadataRes res);
	
	public void delete(String id);

	public PlanMetadataRes findOne(String id);
}
