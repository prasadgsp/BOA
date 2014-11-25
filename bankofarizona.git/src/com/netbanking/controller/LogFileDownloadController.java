package com.netbanking.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netbanking.database.trandetails;
import com.netbanking.database.util.TransDBHandler;
import com.netbanking.model.AuditTranscationsModel;
import com.netbanking.util.CustomLog;

@Controller
@RequestMapping("/")
public class LogFileDownloadController {
	private static final int BUFFER_SIZE = 4096;
	CustomLog log = new CustomLog();

	@PreAuthorize("hasAnyRole('MANAGER')")
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String path=System.getProperty("catalina.home");
		String filePath = path+"/logs/myapp.log";

		Logger logger = log.getLogger(this);
		logger.warn("Reqesting log fle download");

		ServletContext context = request.getServletContext();
		String fullPath = filePath;
		File file = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(file);

		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		response.setContentType(mimeType);
		response.setContentLength((int) file.length());

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				file.getName());
		response.setHeader(headerKey, headerValue);

		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();

	}

	@PreAuthorize("hasAnyRole('MANAGER')")
	@RequestMapping(value = "/downloadAudit", method = RequestMethod.POST)
	public void downloadAudit(
			@ModelAttribute("AuditTranscationsModel") AuditTranscationsModel auditTranscationsModel,
			BindingResult result, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		Logger logger = log.getLogger(this);
		logger.warn("Reqesting Audit log fle download");

		String audirfilepath=System.getProperty("catalina.home");
		String reqePath = audirfilepath+"/logs/audit.csv";
		FileWriter writer = new FileWriter(reqePath);

		List<trandetails> list = TransDBHandler
				.getTransFromDate(auditTranscationsModel.getDate());

		writer.append("transcationId");
		writer.append(',');
		writer.append("From_Acctount");
		writer.append(',');
		writer.append("TO_Acctount");
		writer.append(',');
		writer.append("amount");
		writer.append(',');
		writer.append("TranscationType");
		writer.append(',');
		writer.append("TranscationStatus");
		writer.append(',');
		writer.append("authorisedUser");
		writer.append(',');
		writer.append("timestamp");
		writer.append('\n');

		for (int i = 0; i < list.size(); i++) {
			writer.append("" + list.get(i).getTransId());
			writer.append(',');
			writer.append("" + list.get(i).getFromAcct());
			writer.append(',');
			writer.append("" + list.get(i).getToAcct());
			writer.append(',');
			writer.append("" + list.get(i).getAmount());
			writer.append(',');
			writer.append("" + list.get(i).getTransType());
			writer.append(',');
			writer.append("" + list.get(i).getTransStatus());
			writer.append(',');
			writer.append("" + list.get(i).getAuthUser());
			writer.append(',');
			writer.append("" + list.get(i).getTimestamp());
			writer.append('\n');
		}
		writer.flush();
		writer.close();

		String filePath = reqePath;
		ServletContext context = request.getServletContext();
		String fullPath = filePath;
		File file = new File(fullPath);
		
	
	
		FileInputStream inputStream = new FileInputStream(file);

		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		response.setContentType(mimeType);
		response.setContentLength((int) file.length());

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				file.getName());
		response.setHeader(headerKey, headerValue);

		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();

	}
}