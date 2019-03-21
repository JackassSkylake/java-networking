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

/**
 * @author jackass
 *
 */
public class MultiPartBody implements PostBodyGenerator {
	private String boundary = "--676dhuwgygy5678ddesad";
	private static final String breakLine = "\r\n";
	private ByteArrayOutputStream body = new ByteArrayOutputStream();

	@Override
	public byte[] generate() {
		return body.toByteArray();
	}

	public void addFile(File file, String fieldName) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append(boundary);
		buffer.append(breakLine);
		buffer.append("Content-Disposition:form-data;name=\"" + fieldName + "\";filename=\"" + file.getName() + "\"");
		buffer.append(breakLine);
		buffer.append("Content-Type:application/octet-stream");
		buffer.append(breakLine);
		buffer.append(breakLine);
		body.write(buffer.toString().getBytes());

		int len;
		byte[] data = new byte[1024];
		InputStream inputStream = new FileInputStream(file);
		try {
			while ((len = inputStream.read(data)) != -1) {
				body.write(data, 0, len);
			}
			body.write(breakLine.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public void addFormPattern(Map<String, Object> map) throws IOException {
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			StringBuffer buffer = new StringBuffer();
			buffer.append(boundary);
			buffer.append(breakLine);
			buffer.append("Content-Disposition:form-data;name=\"" + key + "\"");
			buffer.append(breakLine);
			buffer.append(breakLine);
			buffer.append(val.toString());
			buffer.append(breakLine);
			body.write(buffer.toString().getBytes());
		}
	}
}
