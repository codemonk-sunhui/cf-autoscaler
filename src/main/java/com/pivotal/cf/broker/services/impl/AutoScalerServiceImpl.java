package com.pivotal.cf.broker.services.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.ApplicationStats;
import org.cloudfoundry.client.lib.domain.CloudApplication;
import org.cloudfoundry.client.lib.domain.InstanceStats;
import org.springframework.stereotype.Service;

import com.pivotal.cf.broker.services.AutoScalerService;
import com.pivotal.cf.broker.utils.CFConnetor;

@Service
public class AutoScalerServiceImpl implements AutoScalerService{
	
	public static final int MAX_INSTANCE = 5;

	public static final int Min_INSTANCE = 2;
	
	private static List<String> bindedAppList = new ArrayList<String>();
	
	private boolean isRun = false;

	@Override
	public void addApp(String appid) {
		bindedAppList.add(appid);
		startService();
	}

	@Override
	public void deleteApp(String appid) {
		bindedAppList.remove(appid);
		if(bindedAppList.size() == 0) {
			stopService();
			System.out.println("stop thread for there is no app bindings");
		}
	}

	public void startService() {
		if(!isRun) {
			isRun = true;
			run();
		}
	}
	
	private void stopService() {
		isRun = false;
	}
	
	private void run(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(isRun){
					int i = 0;
					System.out.println("start " + i);
					try {
						sleep(2000);
						System.out.println("扫描一次,监控了" + bindedAppList.size() + "个应用,appid is " + bindedAppList );
						for (String appid : bindedAppList) {
							autoScale(appid);
							i++;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
	
	private void sleep(long time){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void autoScale(String appid) throws IOException {
		List<InstanceStats> statsList = getAppMetrics(appid);
		int bigger = 0;
		int smaller = 0;
		for (InstanceStats instanceStats : statsList) {
			double cpu = instanceStats.getUsage().getCpu();
			if(cpu>0.6){
				bigger ++;
			}else if(cpu<0.2) {
				smaller ++;
			}
		}
		if(bigger >= smaller ) {
			addAppInstance(appid);
		}else {
			minusAppInstance(appid);
		}
		
	}
	
	private void addAppInstance(String appid) throws IOException {
		CloudFoundryClient client = CFConnetor.getClient();
		
		int instanceCount = getAppInstanceCount(appid);
		if(instanceCount>MAX_INSTANCE){
			return;
		}
		client.updateApplicationInstances(getAppName(appid), instanceCount+1);
		System.out.println("应用“"+getAppName(appid) + "”扩容一下");
	}
	
	private void minusAppInstance(String appid) throws IOException {
		CloudFoundryClient client = CFConnetor.getClient();
		
		int instanceCount = getAppInstanceCount(appid);
		if(instanceCount<Min_INSTANCE){
			return;
		}
		client.updateApplicationInstances(getAppName(appid),instanceCount-1);
		System.out.println("应用“"+getAppName(appid) + "”缩容一下");
	}
	
	private String getAppName(String appid) throws IOException{
		CloudFoundryClient client = CFConnetor.getClient();
		CloudApplication app = client.getApplication(UUID.fromString(appid));
		return app.getName();
	}
	
	private int getAppInstanceCount(String appid) throws MalformedURLException{
		CloudFoundryClient client = CFConnetor.getClient();
		CloudApplication app = client.getApplication(UUID.fromString(appid));
		return app.getInstances();
	}
	
	private List<InstanceStats> getAppMetrics(String appid) throws IOException {
		CloudFoundryClient client = CFConnetor.getClient();
		String name = getAppName(appid);
		System.out.println("get infomation about " + name);
		ApplicationStats stats = client.getApplicationStats(name);
		return stats.getRecords();
	}

}
