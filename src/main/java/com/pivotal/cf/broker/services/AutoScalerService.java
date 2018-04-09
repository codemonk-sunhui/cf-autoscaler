package com.pivotal.cf.broker.services;

import org.springframework.stereotype.Component;

@Component
public interface AutoScalerService {
	
	void addApp(String appid);
	
	void deleteApp(String appid);

}
