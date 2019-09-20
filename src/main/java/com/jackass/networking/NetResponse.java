/**
 * 
 */
package com.jackass.networking;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

/**
 * @author jackass
 *
 */
public class NetResponse {
	// 返回码
	private int respCode;
	// 返回数据
	private byte[] respData;
	
	private String cacheControlHeader;
	
	private String connectionHeader;
	
	private String contentEncodingHeader;
	
	private String contentTypeHeader;
	
	private String serverHeader;

	private HttpURLConnection connection;

	public String getCacheControlHeader() {
		return cacheControlHeader;
	}

	public void setCacheControlHeader(String cacheControlHeader) {
		this.cacheControlHeader = cacheControlHeader;
	}

	public String getConnectionHeader() {
		return connectionHeader;
	}

	public void setConnectionHeader(String connectionHeader) {
		this.connectionHeader = connectionHeader;
	}

	public String getContentEncodingHeader() {
		return contentEncodingHeader;
	}

	public void setContentEncodingHeader(String contentEncodingHeader) {
		this.contentEncodingHeader = contentEncodingHeader;
	}

	public String getContentTypeHeader() {
		return contentTypeHeader;
	}

	public void setContentTypeHeader(String contentTypeHeader) {
		this.contentTypeHeader = contentTypeHeader;
	}

	public String getServerHeader() {
		return serverHeader;
	}

	public void setServerHeader(String serverHeader) {
		this.serverHeader = serverHeader;
	}

	public void setRespData(byte[] data) {
		this.respData = data;
	}

	public boolean isOk() {
		return respCode == HttpURLConnection.HTTP_OK;
	}

	public void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public HttpURLConnection getConnection() {
		return connection;
	}

	public int getRespCode() {
		return respCode;
	}

	public byte[] getRespData() {
		return respData;
	}

	public String getRespDataAsString(String encoding) throws UnsupportedEncodingException {
		return new String(respData, encoding);
	}

	public String getContentType() {
		return connection.getContentType();
	}

	public String getContentEncoding() {
		return connection.getContentEncoding();
	}
}
