package com.pivotal.cf.broker.services.impl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pivotal.cf.broker.listener.InitListener;
import com.pivotal.cf.broker.model.Plan;
import com.pivotal.cf.broker.model.PlanMetadata;
import com.pivotal.cf.broker.model.PlanMetadataCost;
import com.pivotal.cf.broker.model.PlanMetadataRes;
import com.pivotal.cf.broker.model.ServiceDefinition;
import com.pivotal.cf.broker.repositories.PlanRepository;
import com.pivotal.cf.broker.repositories.ServiceDefinitionRepository;
import com.pivotal.cf.broker.services.BaseService;
import com.pivotal.cf.broker.services.CatalogService;
import com.pivotal.cf.broker.services.PlanService;
import com.pivotal.cf.broker.utils.StringUtils;

@Service
public class CatalogServiceImpl extends BaseService implements CatalogService {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ServiceDefinitionRepository serviceDefinitionRepository;
	
	@Autowired
	private PlanService planService;
	
	@PostConstruct
	public void test(){
		String isInit = StringUtils.isInit();
		if(isInit!=null && isInit.equals("true")){
			init();
		}
	}
	
	@Override
	public ServiceDefinition createServiceDefinition(
			ServiceDefinition serviceDefinition) {
		return serviceDefinitionRepository.save(serviceDefinition);
	}

	@Override
	public List<ServiceDefinition> listServiceDefinition() {
		return this.makeCollection(serviceDefinitionRepository.findAll());
	}

	@Override
	public boolean deleteServiceDefinition(String serviceDefinitionId) {
		if (!serviceDefinitionRepository.exists(serviceDefinitionId)) {
			return false;
		}
		ServiceDefinition serviceDefinition = serviceDefinitionRepository
				.findOne(serviceDefinitionId);
		if (planRepository.countByServiceDefinition(serviceDefinition) > 0) {
			throw new IllegalStateException(
					"Can not remove service instance, the instance has plans associated to it");
		}
		serviceDefinitionRepository.delete(serviceDefinitionId);
		return true;
	}

	@Override
	public int countServiceDefinition() {
		return this.serviceDefinitionRepository.count();
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
	
	private void init(){
		System.out.println("init");
		if (this.countServiceDefinition() > 0) {
			return;
		}

		ServiceDefinition sd = this.readData("ServiceDescription.json",
				ServiceDefinition.class);
		sd = createServiceDefinition(sd);

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
	}


}
