package com.hs.eai.monitorscheduler.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hs.eai.monitorscheduler.service.ExportWorklogTrigger;

@Configuration
@ComponentScan(basePackages = "com.hs.eai.monitorscheduler")
@PropertySource({ "file:henryschein/EaiMonitorSchedulerWebClient.properties" })
//@PropertySource("file:C:/glassfish4/glassfish/domains/domain1/config/henryschein/EaiMonitorSchedulerWebClient.properties")
@EnableAsync
@EnableScheduling
public class AppConfig {

	// @Scheduled(fixedRate=1000)

	@Bean
	public ExportWorklogTrigger processExport() {
		return new ExportWorklogTrigger();
	}
	// @Scheduled(cron="*/5 * * * * ?")
	/*
	 * public void demoServiceMethod() { System.out.println(
	 * "Method executed at every 5 seconds. Current time is :: "+ new Date()); }
	 */

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

		return new PropertySourcesPlaceholderConfigurer();
	}
}
