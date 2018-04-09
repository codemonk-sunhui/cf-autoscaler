package com.pivotal.cf.broker.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;

public class CFConnetor {

//	 --------paas-dev
//	 static String email = "admin";
//	 static String password = "ed320b9dbc71dca0cd8f";
//	 static String target = "https://api.pcfadmin.paas.paic.com.cn";
//	 static String orgName = "pingan";
//	 static String spaceName = "dev";

	// ---------paas-int
	static String  email = "admin";
	static String password = "ec075b4bb0b2478280eb";
	static String target = "https://api.pcfdev.pingantest.com";
	static String orgName = "system";
	static String spaceName = "apps-manager";

	private static CloudFoundryClient client;
	
	public static CloudFoundryClient getClient() throws MalformedURLException{
		if(client==null){
			setUp();
		}
		
		return client;
	}

	private static void setUp() throws MalformedURLException{
		init(email, password, target, orgName, spaceName);
	}

	@PostConstruct
	private static void init(String email, String password, String target, String orgName,
			String spaceName) throws MalformedURLException {
		CloudCredentials credentials = new CloudCredentials(email, password);
		URL cloudControllerUrl = null;
		cloudControllerUrl = URI.create(target).toURL();
		client = new CloudFoundryClient(credentials, cloudControllerUrl,
				orgName, spaceName, true);
		client.login();
	}
}
