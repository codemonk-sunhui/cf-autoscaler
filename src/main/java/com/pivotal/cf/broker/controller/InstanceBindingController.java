package com.pivotal.cf.broker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pivotal.cf.broker.model.ServiceInstanceBinding;
import com.pivotal.cf.broker.model.ServiceInstanceBindingRequest;
import com.pivotal.cf.broker.model.ServiceInstanceBindingResponse;
import com.pivotal.cf.broker.services.ServiceManagement;

@RestController
@RequestMapping("/v2/service_instances/{instanceId}/service_bindings")
public class InstanceBindingController {

	@Autowired
	private ServiceManagement service;

	@ResponseBody
	@RequestMapping(value = "/{bindingId}", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<ServiceInstanceBindingResponse> bind(
			@RequestBody ServiceInstanceBindingRequest request,
			@PathVariable("instanceId") String serviceInstanceId,
			@PathVariable("bindingId") String bindingId) {
		request.setBindingId(bindingId);
		request.setInstanceId(serviceInstanceId);
		ServiceInstanceBinding binding = service.createInstanceBinding(request);
		return new ResponseEntity<ServiceInstanceBindingResponse>(
				new ServiceInstanceBindingResponse(binding), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{bindingId}", produces = "application/json", method = RequestMethod.DELETE)
	public ResponseEntity<String> unbind(
			@PathVariable("instanceId") String serviceInstanceId,
			@PathVariable("bindingId") String bindingId) {
		boolean deleted = service.removeBinding(bindingId);
		HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.GONE;
		ResponseEntity<String> response = new ResponseEntity<String>("{}",
				status);
		return response;
	}

}
