package com.pivotal.cf.broker.controller;

import org.junit.Test;
import org.springframework.http.HttpEntity;

import com.pivotal.cf.broker.model.ServiceInstanceBindingRequest;
import com.pivotal.cf.broker.model.ServiceInstanceBindingResponse;

public class InstanceBindingControllerTest extends BaseControllerTest {

	@Test
	public void testBind() {
		String url = BASE_PATH + "/v2/service_instances/" + "2"
				+ "/service_bindings/" + "2";

		ServiceInstanceBindingRequest req = new ServiceInstanceBindingRequest();
		req.setServiceDefinitionId(SID);
		req.setPlanId(PLAN_ID);
		//test-pq
		req.setAppGuid("ace621a9-0519-48d0-84f3-03adabdfe799");

		this.put(url, new HttpEntity<ServiceInstanceBindingRequest>(req),
				ServiceInstanceBindingResponse.class);

	}

	@Test
	public void testUnbind() {
		String url = BASE_PATH + "/v2/service_instances/" + "2"
				+ "/service_bindings/" + "2";

		this.delete(url, String.class);

	}

}