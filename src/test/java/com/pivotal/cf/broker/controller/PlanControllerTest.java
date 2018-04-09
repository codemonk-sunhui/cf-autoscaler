package com.pivotal.cf.broker.controller;

import org.junit.Test;
import org.springframework.http.HttpEntity;

import com.pivotal.cf.broker.model.Plan;
import com.pivotal.cf.broker.model.PlanMetadataCost;
import com.pivotal.cf.broker.model.PlanMetadataRes;

public class PlanControllerTest extends BaseControllerTest {

	@Test
	public void testCreatePlan() {
		String url = BASE_PATH + "/v2/catalog/services/" + SID + "/plans";

		String fileName = "Plan.json";
		Plan req = this.readObject(fileName, Plan.class);

		this.post(url, new HttpEntity<Plan>(req), Plan.class);

	}

	@Test
	public void testDeletePlan() {
		String url = BASE_PATH + "/v2/catalog/services/" + SID + "/plans/"
				+ "8682aeef-daed-4cfc-8ff5-c2cf2ecc4683";

		this.delete(url, String.class);

	}

	@Test
	public void testAddCost() {
		String url = BASE_PATH + "/v2/catalog/services/" + SID + "/plans/"
				+ PLAN_ID + "/costs";

		String fileName = "PlanMetadataCost.json";
		PlanMetadataCost req = this
				.readObject(fileName, PlanMetadataCost.class);

		this.put(url, new HttpEntity<PlanMetadataCost>(req), Plan.class);

	}

	@Test
	public void testDeleteCost() {
		String url = BASE_PATH + "/v2/catalog/services/" + SID + "/plans/"
				+ PLAN_ID + "/costs/" + "73753a0e-c992-4702-ab71-5122f9570dc2";

		this.delete(url, Plan.class);

	}

	@Test
	public void testAddRes() {
		String url = BASE_PATH + "/v2/catalog/services/" + SID + "/plans/"
				+ PLAN_ID + "/pool";

		String fileName = "PlanMetadataRes.json";
		PlanMetadataRes req = this.readObject(fileName, PlanMetadataRes.class);

		this.put(url, new HttpEntity<PlanMetadataRes>(req), Plan.class);

	}

	@Test
	public void testDeleteRes() {
		String url = BASE_PATH + "/v2/catalog/services/" + SID + "/plans/"
				+ PLAN_ID + "/pool/" + "df997db4-7681-40c5-afe8-c577f550c5e1";

		this.delete(url, Plan.class);

	}

}