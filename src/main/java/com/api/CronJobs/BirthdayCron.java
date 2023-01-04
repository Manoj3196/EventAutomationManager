package com.api.CronJobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.api.service.BirthdayService;

@Component
public class BirthdayCron {

	@Autowired
	BirthdayService birthdayService;

	// Execute every second
	@Scheduled(cron = "*/1 * * * * *")
	public void cronJobSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println(strDate);

		birthdayService.sendBirthdayEmail();

	}

}