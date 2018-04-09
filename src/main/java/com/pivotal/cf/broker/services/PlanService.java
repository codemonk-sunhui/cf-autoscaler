package com.pivotal.cf.broker.services;

import com.pivotal.cf.broker.model.Plan;
import com.pivotal.cf.broker.model.PlanMetadata;

public interface PlanService {
	Plan create(Plan plan);

	boolean delete(String planId);

	Plan addCost(PlanMetadata metadata);

	Plan deleteCost(PlanMetadata metadata);

	Plan addRes(PlanMetadata metadata);

	Plan deleteRes(PlanMetadata metadata);

}
