package com.pivotal.cf.broker.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class StringUtils {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static Random rnd = new Random();

	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			if (i == 0)
				sb.append(AB.charAt(10 + rnd.nextInt(AB.length() - 10)));
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	public static String getId() {
		UUID uuid = UUID.randomUUID();
		String a = uuid.toString();
		a = a.toUpperCase();
		a = a.replaceAll("-", "");
		return a;
	}
	
	public static <T> List<T> valueList(Map<String,T> map) {
		List<T> tList = new ArrayList<T>();
		
		Set<Entry<String, T>>  set = map.entrySet();
		
		for (Entry<String, T> entry : set) {
			T t = entry.getValue();
			tList.add(t);
		}
		
		return tList;
	}
	
	public static String isInit() {
		Properties pro = new Properties();
		try {
			pro.load((StringUtils.class.getResourceAsStream("/config/catalog.properties")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pro.getProperty("catalog.init");
	}
}
