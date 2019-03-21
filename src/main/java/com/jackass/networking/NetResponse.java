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

	transient private HttpURLConnection connection;

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

	public String getRespDataAsString(String encoding) {
		String ret = null;
		try {
			ret = new String(respData, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public String getContentType() {
		return connection.getContentType();
	}

	public String getContentEncoding() {
		return connection.getContentEncoding();
	}

	@Override
	public String toString() {
		try {
			return "NetResponse [respCode=" + respCode + ", respData=" + new String(respData, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
