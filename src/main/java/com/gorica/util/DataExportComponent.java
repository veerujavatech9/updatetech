package com.gorica.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.gorica.dao.model.ScannedTimeLog;

@Component
public class DataExportComponent {

	public void doExport(String fileName, List<ScannedTimeLog> attendenaceList, HttpServletResponse response) {
		try {
			SimpleDateFormat formate = new SimpleDateFormat("dd.MM.yyyy");
			if (attendenaceList != null && !attendenaceList.isEmpty()) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Attendance Report");

				/*
				 * HSSFRow rowhead = sheet.createRow((short) 0);
				 * rowhead.createCell(0).setCellValue("JOB Number");
				 * rowhead.createCell(1).setCellValue("Activity No.");
				 * rowhead.createCell(2).setCellValue("Confimartion");
				 * rowhead.createCell(3).setCellValue("Yied");
				 * rowhead.createCell(4).setCellValue("Activity Hours");
				 * rowhead.createCell(5).setCellValue("Unit of Measure");
				 * rowhead.createCell(6).setCellValue("Activity Hours");
				 * rowhead.createCell(7).setCellValue("Unit of Measure");
				 * rowhead.createCell(8).setCellValue("Personnel No.");
				 * rowhead.createCell(9).setCellValue("Posting Date");
				 * rowhead.createCell(10).setCellValue("Confirmed IDLE Time");
				 * rowhead.createCell(11).setCellValue("Unit of IDLE Time");
				 * rowhead.createCell(12).setCellValue("Reason for IDLE Hrs.");
				 */

				Iterator<ScannedTimeLog> itr = attendenaceList.iterator();
				int index = 0;
				while (itr.hasNext()) {
					ScannedTimeLog timeLog = itr.next();
					HSSFRow row = sheet.createRow((short) index);
					if (timeLog.getJob() != null)
						row.createCell(0).setCellValue(timeLog.getJob().getJobNo());
					if (timeLog.getActivity() != null)
						row.createCell(1).setCellValue(timeLog.getActivity().getActivityNo());
					row.createCell(2).setCellValue("");
					row.createCell(3).setCellValue("");
					row.createCell(4).setCellValue("");
					row.createCell(5).setCellValue("");
					if (timeLog.getActivityHrs() != null)
						row.createCell(6).setCellValue(timeLog.getActivityHrs());
					else
						row.createCell(6).setCellValue("0.0");
					row.createCell(7).setCellValue("H");
					if (timeLog.getEmployee() != null)
						row.createCell(8).setCellValue(timeLog.getEmployee().getEmpNo());
					row.createCell(9).setCellValue(formate.format(timeLog.getCheckInTime()));
					if (timeLog.getIdealHrs() != null && timeLog.getIdealHrs() != "0") {
						row.createCell(10).setCellValue(timeLog.getIdealHrs());
						row.createCell(11).setCellValue("H");
					}
					if (timeLog.getIdealReason() != null)
						row.createCell(12).setCellValue(timeLog.getIdealReason().getReason());
					if (timeLog.getUser() != null)
						row.createCell(13).setCellValue(timeLog.getUser().getUserName());
					index++;
				}

				FileOutputStream fileOut = new FileOutputStream(fileName);
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();

				final File f = new File(fileName);
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename=" + fileName);
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "no-store");
				response.addHeader("Cache-Control", "max-age=0");
				FileInputStream fin = null;
				ServletOutputStream os = null;
				try {
					fin = new FileInputStream(f);
					final int size = 1024;
					response.setContentLength(fin.available());
					final byte[] buffer = new byte[size];
					os = response.getOutputStream();
					int length = 0;
					while ((length = fin.read(buffer)) != -1) {
						os.write(buffer, 0, length);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				} finally {
					try {
						if (fin != null)
							fin.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}

					try {
						if (os != null)
							os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
