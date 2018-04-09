package com.pivotal.cf.broker.controller;

import java.io.InputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseControllerTest {
	protected static final String BASE_PATH = "http://127.0.0.1:8080";
	/**
	 * 需修改为正确的 service_definition_id
	 */
	protected static final String SID = "1ce25388-e5cd-4b08-a820-a1c243d926be";
	/**
	 * 需修改为正确的 plan_id
	 */
	protected static final String PLAN_ID = "3deba1f6-55c5-4ed6-8432-4b40b78c9711";

	private ObjectMapper mapper = new ObjectMapper();

	protected <T> T readObject(String fileName, Class<T> clazz) {
		InputStream in = null;
		try {
			in = this.getClass().getClassLoader().getResourceAsStream(fileName);
			return mapper.readValue(in, clazz);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}

		throw new RuntimeException("fail to read json data file,fileName:");

	}

	/**
	 * 读取 json 数据文件
	 * 
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	protected JSONObject readJsonData(String fileName, Class<?> clazz) {

		return new JSONObject(this.readObject(fileName, clazz));

	}

	protected <T> ResponseEntity<T> get(String url, Class<T> clazz) {
		return this.execute(url, HttpMethod.GET, null, clazz);

	}

	protected <T> ResponseEntity<T> post(String url, HttpEntity<?> entity,
			Class<T> clazz) {
		return this.execute(url, HttpMethod.POST, entity, clazz);

	}

	protected <T> ResponseEntity<T> put(String url, HttpEntity<?> entity,
			Class<T> clazz) {
		return this.execute(url, HttpMethod.PUT, entity, clazz);

	}

	protected <T> ResponseEntity<T> delete(String url, Class<T> clazz) {
		return this.execute(url, HttpMethod.DELETE, null, clazz);

	}

	/**
	 * 执行请求
	 * 
	 * @param request
	 * @return
	 */
	private <T> ResponseEntity<T> execute(String url, HttpMethod method,
			HttpEntity<?> entity, Class<T> clazz) {

		ResponseEntity<T> res = null;
		try {
			RestTemplate template = new RestTemplate();
			res = template.exchange(url, method, entity, clazz);

			HttpStatus status = res.getStatusCode();
			this.log("request status:" + status);

			T t = res.getBody();
			if (null != t) {
				if (t instanceof List) {
					this.log(new JSONArray((List<?>) res.getBody()));
				} else {
					this.log(new JSONObject(res.getBody()));
				}

			}

		} catch (HttpClientErrorException e) {
			HttpStatus status = e.getStatusCode();
			this.log("request status:" + status);

			this.log("msg:" + e.getResponseBodyAsString());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return res;

	}

	protected void log(Object msg) {
		System.out.println(msg);
	}

}