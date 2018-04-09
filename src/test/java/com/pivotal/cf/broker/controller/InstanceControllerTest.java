package com.pivotal.cf.broker.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpEntity;

import com.pivotal.cf.broker.model.CreateServiceInstanceRequest;
import com.pivotal.cf.broker.model.CreateServiceInstanceResponse;

public class InstanceControllerTest extends BaseControllerTest {

	@Test
	public void testList() {
		String url = BASE_PATH + "/v2/service_instances";

		this.get(url, List.class);

	}

	@Test
	public void testProvision() {
		String url = BASE_PATH + "/v2/service_instances/" + "1";

		CreateServiceInstanceRequest req = new CreateServiceInstanceRequest();
		req.setServiceDefinitionId(SID);
		req.setPlanId(PLAN_ID);

		this.put(url, new HttpEntity<CreateServiceInstanceRequest>(req),
				CreateServiceInstanceResponse.class);

	}

	@Test
	public void testDeprovision() {
		String url = BASE_PATH + "/v2/service_instances/" + "2";

		this.delete(url, String.class);

	}

}