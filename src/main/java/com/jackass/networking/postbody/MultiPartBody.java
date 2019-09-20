/**
 * 
 */
package com.jackass.networking.postbody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jackass
 *
 */
public class MultiPartBody implements PostBodyGenerator {
	private Logger logger=LoggerFactory.getLogger(MultiPartBody.class);
	
	private String boundary = "--676dhuwgygy5678ddesad";
	private static final String BREAK_LINE = "\r\n";
	private ByteArrayOutputStream body = new ByteArrayOutputStream();

	@Override
	public byte[] generate() {
		return body.toByteArray();
	}

	public void addFile(File file, String fieldName) throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append(boundary);
		builder.append(BREAK_LINE);
		builder.append("Content-Disposition:form-data;name=\"" + fieldName + "\";filename=\"" + file.getName() + "\"");
		builder.append(BREAK_LINE);
		builder.append("Content-Type:application/octet-stream");
		builder.append(BREAK_LINE);
		builder.append(BREAK_LINE);
		body.write(builder.toString().getBytes());

		int len;
		byte[] data = new byte[1024];
		
		try(InputStream inputStream = new FileInputStream(file);) {
			while ((len = inputStream.read(data)) != -1) {
				body.write(data, 0, len);
			}
			body.write(BREAK_LINE.getBytes());
		} catch (Exception e) {
			logger.error("",e);
		}
	}

	public void addFormPattern(Map<String, Object> map) throws IOException {
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			StringBuilder builder = new StringBuilder();
			builder.append(boundary);
			builder.append(BREAK_LINE);
			builder.append("Content-Disposition:form-data;name=\"" + key + "\"");
			builder.append(BREAK_LINE);
			builder.append(BREAK_LINE);
			builder.append(val.toString());
			builder.append(BREAK_LINE);
			body.write(builder.toString().getBytes());
		}
	}
}
