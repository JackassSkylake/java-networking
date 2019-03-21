/**
 * 
 */
package com.jackass.networking;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import com.jackass.networking.afterconn.AfterConnectCallback;
import com.jackass.networking.afterconn.SimpleAfterConHandler;

/**
 * @author jackass
 *
 */
public class HttpUtil {
	// post调用
	public static byte[] doPost(String addr) throws Exception {
		return doPost(addr, null, null, null);
	}

	public static byte[] doPost(String addr, AfterConnectCallback callback) throws Exception {
		return doPost(addr, null, null, callback);
	}

	public static byte[] doPost(String addr, byte[] postData) throws Exception {
		return doPost(addr, postData, null, null);
	}

	public static byte[] doPost(String addr, byte[] postData, AfterConnectCallback callback) throws Exception {
		return doPost(addr, postData, null, callback);
	}

	public static byte[] doPost(String addr, Map<String, String> headerParams) throws Exception {
		return doPost(addr, null, headerParams, null);
	}

	public static byte[] doPost(String addr, Map<String, String> headerParams, AfterConnectCallback callback)
			throws Exception {
		return doPost(addr, null, headerParams, callback);
	}

	public static byte[] doPost(String addr, byte[] postData, Map<String, String> headerParams) throws Exception {
		return doPost(addr, postData, headerParams, null);
	}

	public static byte[] doPost(String addr, byte[] postData, Map<String, String> headerParams,
			AfterConnectCallback callback) throws Exception {
		byte ret[] = null;

		URL url = new URL(addr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		setAssignHeaderParams(connection, headerParams);
		if (postData != null) {
			connection.setRequestProperty("Content-Length", String.valueOf(postData.length));
		}
		connection.connect();
		OutputStream outputStream = connection.getOutputStream();
		if(postData!=null)
			outputStream.write(postData);
		outputStream.flush();
		outputStream.close();
		if (callback != null) {
			ret = callback.afterConnect(connection);
		} else {
			ret = new SimpleAfterConHandler().afterConnect(connection);
		}
		return ret;
	}

	// get调用

	public static byte[] doGet(String addr) throws Exception {
		return doGet(addr, null, null, null);
	}

	public static byte[] doGet(String addr, AfterConnectCallback callback) throws Exception {
		return doGet(addr, null, null, callback);
	}

	public static byte[] doGet(String addr, Map<String, Object> params) throws Exception {
		return doGet(addr, params, null, null);
	}

	public static byte[] doGet(String addr, Map<String, Object> params, AfterConnectCallback callback)
			throws Exception {
		return doGet(addr, params, null, callback);
	}

	public static byte[] doGet(Map<String, String> headerParams, String addr) throws Exception {
		return doGet(addr, null, headerParams, null);
	}

	public static byte[] doGet(Map<String, String> headerParams, String addr, AfterConnectCallback callback)
			throws Exception {
		return doGet(addr, null, headerParams, callback);
	}

	public static byte[] doGet(String addr, Map<String, Object> params, Map<String, String> headerParams)
			throws Exception {
		return doGet(addr, params, headerParams, null);
	}

	public static byte[] doGet(String addr, Map<String, Object> params, Map<String, String> headerParams,
			AfterConnectCallback callback) throws Exception {
		byte[] ret = null;
		String reqUrl = addr;
		if (params != null) {
			if (addr.indexOf("?") != -1) {
				reqUrl = addr + "&" + UrlKeyValueUtil.toUrlKeyVal(params);
			} else {
				reqUrl = addr + "?" + UrlKeyValueUtil.toUrlKeyVal(params);
			}
		}
		URL url = new URL(reqUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		setAssignHeaderParams(connection, headerParams);
		connection.connect();
		if (callback != null) {
			ret = callback.afterConnect(connection);
		} else {
			ret = new SimpleAfterConHandler().afterConnect(connection);
		}
		return ret;
	}

	/**
	 * 设置请求头参数
	 * 
	 * @param connection
	 * @param params
	 */
	private static void setAssignHeaderParams(HttpURLConnection connection, Map<String, String> params) {
		if (connection == null || params == null) {
			return;
		}
		for (Entry<String, String> entry : params.entrySet()) {
			connection.setRequestProperty(entry.getKey(), entry.getValue());
		}
	}
}
