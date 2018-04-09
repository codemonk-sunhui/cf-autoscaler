package com.pivotal.cf.broker.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pivotal.cf.broker.listener.InitListener;
import com.pivotal.cf.broker.model.Plan;
import com.pivotal.cf.broker.model.PlanMetadata;
import com.pivotal.cf.broker.model.PlanMetadataCost;
import com.pivotal.cf.broker.model.PlanMetadataRes;
import com.pivotal.cf.broker.model.ServiceDefinition;
import com.pivotal.cf.broker.services.CatalogService;
import com.pivotal.cf.broker.services.PlanService;

@RestController
@RequestMapping(value = "/v2/catalog/init")
public class InitController {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private PlanService planService;

	@Autowired
	private CatalogService catalogService;
	
	@ResponseBody
	@RequestMapping(produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<String> init() {

		if (this.catalogService.countServiceDefinition() > 0) {
			ResponseEntity<String> response = new ResponseEntity<String>(
					"{already init}", HttpStatus.FOUND);
			return response;
		}

		ServiceDefinition sd = this.readData("ServiceDescription.json",
				ServiceDefinition.class);
		sd = catalogService.createServiceDefinition(sd);

		// add plan
		Plan plan = this.readData("Plan.json", Plan.class);
		plan.setServiceDefinition(sd);
		sd.addPlan(plan);
		plan = planService.create(plan);

		// add cost
		PlanMetadataCost cost = this.readData("PlanMetadataCost.json",
				PlanMetadataCost.class);
		PlanMetadata metadata = plan.getMetadata();
		metadata.getCosts().add(cost);
		plan = planService.addCost(metadata);

		// add res
		PlanMetadataRes res = this.readData("PlanMetadataRes.json",
				PlanMetadataRes.class);
		metadata = plan.getMetadata();
		metadata.getPool().add(res);
		plan = planService.addRes(metadata);

		ResponseEntity<String> response = new ResponseEntity<String>(
				"{init success}", HttpStatus.CREATED);
		return response;

	}

	private <T> T readData(String fileName, Class<T> clazz) {
		InputStream in = null;
		try {
			in = InitListener.class.getClassLoader().getResourceAsStream(
					fileName);
			T t = mapper.readValue(in, clazz);
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}

	}

}
