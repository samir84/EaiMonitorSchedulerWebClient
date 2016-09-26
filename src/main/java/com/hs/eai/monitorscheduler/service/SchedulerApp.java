package com.hs.eai.monitorscheduler.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hs.eai.monitorscheduler.spring.AppConfig;

public class SchedulerApp {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
	}
}
