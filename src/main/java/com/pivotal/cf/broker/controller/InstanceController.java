package com.pivotal.cf.broker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pivotal.cf.broker.model.CreateServiceInstanceRequest;
import com.pivotal.cf.broker.model.CreateServiceInstanceResponse;
import com.pivotal.cf.broker.model.ServiceInstance;
import com.pivotal.cf.broker.services.ServiceManagement;

@RestController
@RequestMapping("/v2/service_instances")
public class InstanceController {

	@Autowired
	private ServiceManagement service;

	@ResponseBody
	@RequestMapping(produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<List<ServiceInstance>> listInstances() {
 		List<ServiceInstance> instances = service.listInstances();
		if (instances.isEmpty()) {
			instances = null;
		}
		ResponseEntity<List<ServiceInstance>> response = new ResponseEntity<List<ServiceInstance>>(
				instances, HttpStatus.OK);
		return response;
	}

	@ResponseBody
	@RequestMapping(consumes = "application/json", produces = "application/json", value = "/{instanceId}", method = RequestMethod.PUT)
	public ResponseEntity<CreateServiceInstanceResponse> provision(
			@RequestBody CreateServiceInstanceRequest serviceRequest,
			@PathVariable("instanceId") String serviceInstanceId) {
		serviceRequest.setServiceInstanceId(serviceInstanceId);
		ServiceInstance instance = service.createInstance(serviceRequest);
		ResponseEntity<CreateServiceInstanceResponse> response = new ResponseEntity<CreateServiceInstanceResponse>(
				new CreateServiceInstanceResponse(instance), HttpStatus.CREATED);
		return response;
	}

	@RequestMapping(produces = "application/json", value = "/{instanceId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deprovision(
			@PathVariable("instanceId") String serviceInstanceId) {
		boolean deleted = service.removeServiceInstance(serviceInstanceId);
		HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.GONE;
		ResponseEntity<String> response = new ResponseEntity<String>("{}",
				status);
		return response;
	}

}
