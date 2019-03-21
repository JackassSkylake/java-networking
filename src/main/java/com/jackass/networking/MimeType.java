/**
 * 
 */
package com.jackass.networking;

/**
 * @author jackass
 *
 */
public enum MimeType {
	OCTETSTREAM("application/octet-stream"),
	JPG("image/jpeg"),
	GIF("image/gif"),
	HTML("text/html"),
	PLAIN("text/plain"),
	JSON("application/json"),
	XWWWFORM("application/x-www-form-urlencoded"),
	MULTYPART_FORM_DATA("multipart/form-data");
	
	private String value;
	private MimeType(String contentType) {
		this.value=contentType;
	}
	public String getValue() {
		return value;
	}
}
