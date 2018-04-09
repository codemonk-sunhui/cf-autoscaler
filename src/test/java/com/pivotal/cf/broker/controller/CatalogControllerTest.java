package com.pivotal.cf.broker.controller;

import org.junit.Test;
import org.springframework.http.HttpEntity;

import com.pivotal.cf.broker.model.Catalog;
import com.pivotal.cf.broker.model.ServiceDefinition;

public class CatalogControllerTest extends BaseControllerTest {
	@Test
	public void testList() {
		String url = BASE_PATH + "/v2/catalog";

		this.get(url, Catalog.class);

	}

	@Test
	public void testCreateServiceDefinition() {
		String url = BASE_PATH + "/v2/catalog/services";

		String fileName = "ServiceDescription.json";
		ServiceDefinition req = this.readObject(fileName,
				ServiceDefinition.class);

		this.post(url, new HttpEntity<ServiceDefinition>(req),
				ServiceDefinition.class);

	}

	@Test
	public void testDeleteServiceDefinition() {
		String url = BASE_PATH + "/v2/catalog/services/"
				+ "80b18379-d7ab-4950-942a-86cd59091ade";

		this.delete(url, String.class);

	}

}