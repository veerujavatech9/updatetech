package com.gorica.controller.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gorica.SessionData;
import com.gorica.dao.model.BreakTimings;
import com.gorica.dao.model.ScannedTimeLog;
import com.gorica.service.FinalActivityService;
import com.gorica.service.JobDetailsService;
import com.gorica.service.TimeLogDetailsService;

@Component
@RequestMapping("/finalActivity")
public class FinalActivitySchedular {

	private static final Logger logger = LoggerFactory.getLogger(FinalActivitySchedular.class);
	
	@Autowired
	private SessionData sessionobj;

	@Autowired
	private TimeLogDetailsService timeLogDetailsService;

	@Autowired
	private FinalActivityService finalActivityService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Autowired
	private JobDetailsService jobDetailsService;

	/******************
	 * Cron Job for Generating Attendance Report
	 **********************/
	@Scheduled(cron = "0 40 6 * * ?")
	//@Scheduled(cron = "0 45 5 * * ?")
	@GetMapping("/attendanceReportSchedular")
	public void generateAttendanceReport() {
		try {
			System.out.println("Generating Attendance Report");
			Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
			Date postDate = Date.from(yesterday);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			String postYstrDate = dateFormat.format(postDate);
			String postTodayDate = dateFormat.format(new Date());
			// System.out.println("strYstrDate=" + postYstrDate + ",strTodayDate=" +
			// postTodayDate);

			/*
			 * List<ScannedTimeLog> attendenaceList = timeLogDetailsService
			 * .getScannedTimeLogByPostingDate(simpleDateFormat.format(postDate));
			 */

			List<ScannedTimeLog> attendenaceList = timeLogDetailsService.getScannedTimeLogBetweenDates(postYstrDate,
					postTodayDate);
			System.out.println("attendenaceList=" + attendenaceList);

			List<BreakTimings> breakTimings = jobDetailsService.getActiveBreakTimings();
			for (ScannedTimeLog scannedTimeLog : attendenaceList) {
				String actvieHrs = "0";
				String idleHrsHrs = "0";
				if (scannedTimeLog.getCheckOutTime() != null && scannedTimeLog.getCheckInTime() != null) {
					Period p = new Period(scannedTimeLog.getCheckInTime().getTime(),
							scannedTimeLog.getCheckOutTime().getTime());
					actvieHrs = String.valueOf(p.getHours()) + "." + String.valueOf(p.getMinutes());
				} else if (scannedTimeLog.getCheckOutTime() == null && scannedTimeLog.getCheckInTime() != null) {

					long difference_In_Hours = TimeUnit.MILLISECONDS
							.toHours(new Date().getTime() - scannedTimeLog.getCheckInTime().getTime()) % 24;
					if (difference_In_Hours >= 16)
						actvieHrs = "9.0";
					else if (difference_In_Hours < 16 && scannedTimeLog.getCheckInTime().getHours() >= 14)
						actvieHrs = "9.0";
				}

				if (scannedTimeLog.getIdealOutTime() != null && scannedTimeLog.getIdealInTime() != null) {
					Period p = new Period(scannedTimeLog.getIdealInTime().getTime(),
							scannedTimeLog.getIdealOutTime().getTime());
					idleHrsHrs = String.valueOf(p.getHours()) + "." + String.valueOf(p.getMinutes());
				}

				else if (scannedTimeLog.getIdealOutTime() == null && scannedTimeLog.getIdealInTime() != null) {

					long difference_In_Hours = TimeUnit.MILLISECONDS
							.toHours(new Date().getTime() - scannedTimeLog.getIdealInTime().getTime()) % 24;
					// System.out.println("Idle Time Difference=" + difference_In_Hours);
					if (difference_In_Hours >= 16)
						idleHrsHrs = "9.0";
					else if (difference_In_Hours < 16 && scannedTimeLog.getIdealInTime().getHours() >= 14)
						idleHrsHrs = "9.0";
				}

				for (BreakTimings breakTime : breakTimings) {
					Date startTime = timeFormat.parse(breakTime.getStartTime());
					Date potDate = scannedTimeLog.getPostingDate();
					potDate.setHours(startTime.getHours());
					potDate.setMinutes(startTime.getMinutes());
					// System.out.println("startTime=" + startTime);
					Calendar clndrstrtTime = Calendar.getInstance();
					clndrstrtTime.setTime(potDate);
					if (scannedTimeLog.getCheckOutTime() != null && scannedTimeLog.getCheckInTime() != null) {
						Calendar checkInCldr = Calendar.getInstance();
						checkInCldr.setTime(scannedTimeLog.getCheckInTime());

						Calendar checkOutCldr = Calendar.getInstance();
						checkOutCldr.setTime(scannedTimeLog.getCheckOutTime());

						if (clndrstrtTime.getTime().after(checkInCldr.getTime())
								&& clndrstrtTime.getTime().before(checkOutCldr.getTime())) {
							actvieHrs = String.format("%.2f",
									((Double.valueOf(actvieHrs)) - Double.valueOf(breakTime.getBreakTime()) / 60));
						}

					} else if (scannedTimeLog.getCheckOutTime() == null && scannedTimeLog.getCheckInTime() != null) {
						actvieHrs = String.format("%.2f",
								((Double.valueOf(actvieHrs)) - Double.valueOf(breakTime.getBreakTime()) / 60));
					}
				}

				scannedTimeLog.setActivityHrs(actvieHrs);
				scannedTimeLog.setIdealHrs(idleHrsHrs);
				// System.out.println("scannedTimeLog=" + scannedTimeLog);
				timeLogDetailsService.updateTimeLog(scannedTimeLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
