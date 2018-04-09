package com.pivotal.cf.broker.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pivotal.cf.broker.model.CreateServiceInstanceRequest;
import com.pivotal.cf.broker.model.Plan;
import com.pivotal.cf.broker.model.PlanMetadataRes;
import com.pivotal.cf.broker.model.ServiceDefinition;
import com.pivotal.cf.broker.model.ServiceInstance;
import com.pivotal.cf.broker.model.ServiceInstanceBinding;
import com.pivotal.cf.broker.model.ServiceInstanceBindingRequest;
import com.pivotal.cf.broker.repositories.PlanMetadataResRepository;
import com.pivotal.cf.broker.repositories.PlanRepository;
import com.pivotal.cf.broker.repositories.ServiceDefinitionRepository;
import com.pivotal.cf.broker.repositories.ServiceInstanceBindingRepository;
import com.pivotal.cf.broker.repositories.ServiceInstanceRepository;
import com.pivotal.cf.broker.services.AutoScalerService;
import com.pivotal.cf.broker.services.BaseService;
import com.pivotal.cf.broker.services.ServiceManagement;

@Service
public class ServiceManagementImpl extends BaseService implements
		ServiceManagement {
	
	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ServiceDefinitionRepository serviceRepository;

	@Autowired
	private ServiceInstanceRepository serviceInstanceRepository;

	@Autowired
	private ServiceInstanceBindingRepository bindingRepository;

	@Autowired
	private PlanMetadataResRepository planMetadataResRepository;
	
	@Autowired
	private AutoScalerService autoService;


	@Override
	public ServiceInstance createInstance(
			CreateServiceInstanceRequest serviceRequest) {
		ServiceDefinition serviceDefinition = serviceRepository
				.findOne(serviceRequest.getServiceDefinitionId());
		if (serviceDefinition == null) {
			throw new IllegalArgumentException("Service definition not found: "
					+ serviceRequest.getServiceDefinitionId());
		}
		Plan plan = planRepository.findOne(serviceRequest.getPlanId());
		if (plan == null) {
			throw new IllegalArgumentException("Invalid plan identifier");
		}
		if (serviceInstanceRepository.exists(serviceRequest
				.getServiceInstanceId())) {
			throw new IllegalStateException(
					"There's already an instance of this service");
		}
//		if (plan.getMetadata().getPool().isEmpty()) {
//			throw new IllegalArgumentException(
//					"No resource left for this plan, contact PaaS admin");
//		}
		PlanMetadataRes res = new PlanMetadataRes();
		String dashboardUrl = "http://pa-autoscaler.pcfdev.pingantest.com/rules/config_index.html";
		ServiceInstance instance = new ServiceInstance(
				serviceRequest.getServiceInstanceId(),
				serviceDefinition.getId(), plan.getId(),
				serviceRequest.getOrganizationGuid(),
				serviceRequest.getSpaceGuid(), dashboardUrl, res.getId());

//		planMetadataResRepository.delete(res.getId());
		instance = serviceInstanceRepository.save(instance);
		return instance;
	}

	@Override
	public boolean removeServiceInstance(String serviceInstanceId) {
		if (!serviceInstanceRepository.exists(serviceInstanceId)) {
			return false;
		}
		if (bindingRepository.countByServiceInstanceId(serviceInstanceId) > 0) {
			throw new IllegalStateException(
					"Can not delete service instance, there are still apps bound to it");
		}
		ServiceInstance instance = serviceInstanceRepository
				.findOne(serviceInstanceId);
		Plan plan = planRepository.findOne(instance.getPlanId());
		PlanMetadataRes res = new PlanMetadataRes();
		res.setId(instance.getResId());
		
		res.setPlanMetadata(plan.getMetadata());
		plan.getMetadata().getPool().add(res);
		planMetadataResRepository.save(res);
		serviceInstanceRepository.delete(serviceInstanceId);
		return true;
	}

	@Override
	public List<ServiceInstance> listInstances() {
		return makeCollection(serviceInstanceRepository.findAll());
	}

	@Override
	public ServiceInstanceBinding createInstanceBinding(
			ServiceInstanceBindingRequest bindingRequest) {
		if (bindingRepository.exists(bindingRequest.getBindingId())) {
			throw new IllegalStateException("Binding Already exists");
		}
		ServiceInstance instance = serviceInstanceRepository
				.findOne(bindingRequest.getInstanceId());
		if (instance == null) {
			throw new IllegalArgumentException("instance not exists");
		}

		Map<String, String> credentials = new HashMap<String, String>();

		ServiceInstanceBinding binding = new ServiceInstanceBinding();
		binding.setId(bindingRequest.getBindingId());
		binding.setServiceInstanceId(bindingRequest.getInstanceId());
		binding.setAppGuid(bindingRequest.getAppGuid());
		binding.setCredentials(credentials);
		binding = bindingRepository.save(binding);
		
		//添加auto scaler
		String appid = bindingRequest.getAppGuid();
		autoService.addApp(appid);
		
		return binding;
	}

	@Override
	public boolean removeBinding(String serviceBindingId) {
		ServiceInstanceBinding binding = bindingRepository
				.findOne(serviceBindingId);
		if (binding == null) {
			return false;
		}

		bindingRepository.delete(binding);
		
		String appid = binding.getAppGuid();
		autoService.deleteApp(appid);
		return true;
	}

	@Override
	public List<ServiceInstanceBinding> listBindings() {
		return makeCollection(bindingRepository.findAll());
	}

}
