package com.hs.eai.monitorscheduler.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hs.eai.monitorscheduler.model.GenericResponse;


@Component
public class ExportWorklogTrigger {

	private static final Logger logger = LoggerFactory.getLogger(ExportWorklogTrigger.class);
	
	private static final String EXPORT_WORKLOG_STATUS_ACTIVE = "exportWorklogStatusActive";
	private static final String REST_URI_EXPORT_WORKLOG= "restUriexportWorklog";
	
	
	@Autowired
	Environment env;
	
	@Scheduled(cron = "${exportWorklogStatusCron}")
	@Async
	public void process() {

		System.out.println("Scheduled exportWorklog started!..");
		String active = env.getRequiredProperty(EXPORT_WORKLOG_STATUS_ACTIVE);
		
		if(active!= null && active.trim().equalsIgnoreCase("true")){
			
			try{
				LocalDate today = LocalDate.now();

			    // Go backward to get Monday
			    LocalDate monday = today;
			    while (monday.getDayOfWeek() != DayOfWeek.MONDAY)
			    {
			      monday = monday.minusDays(1);
			    }

			    // Go forward to get Sunday
			    LocalDate sunday = today;
			    while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY)
			    {
			      sunday = sunday.plusDays(1);
			    }

			    System.out.println("Today: " + today);
			    System.out.println("Monday of the Week: " + monday);
			    System.out.println("Sunday of the Week: " + sunday);
				RestTemplate restTemplate = new RestTemplate();
				String restUriexportWorklogs = env.getRequiredProperty(REST_URI_EXPORT_WORKLOG);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("fromDate", monday);
				params.put("toDate", sunday);
				ResponseEntity<GenericResponse> exportWorklogsResponse = restTemplate.exchange(restUriexportWorklogs,
						HttpMethod.GET, null, new ParameterizedTypeReference<GenericResponse>() {
						}, params);
				
				//GenericResponse response = exportWorklogsResponse.getBody();
				
				//System.out.println("exportWorklogsResponse:"+response.toString());
				//send notification of scheduled export!
				//implement later
			}catch(Exception ex){
				logger.error("Error occur when trying to schedul export worklog, reason: {} ",ex.getMessage());
				ex.printStackTrace();
				
			}
			
			System.out.println("End Scheduled exportWorklog started!..");
		}
	}
}
