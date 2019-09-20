/**
 * 
 */
package com.jackass.networking;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jackass.networking.afterconn.SimpleAfterConHandler;

/**
 * @author jackass
 *
 */
public abstract class AbstractHttpClient{

	private static final String COOKIE = "Cookie";
	
	private final SimpleAfterConHandler simpleAfterConHandler=new SimpleAfterConHandler();

	protected Map<String, String> headerParams = new HashMap<>();
	private AfterConnectCallback afterConnectCallback;
	private BeforeConnectCallback beforeConnectCallback;
	protected Map<String, String> urlParams = new HashMap<>();
	
	private Map<String, String> cookieMap = new HashMap<>();
	
	private NetResponse netResponse;

	protected int respCode;
	
	protected byte[] respData; 

	protected String addr;

	/**
	 * 
	 */
	public AbstractHttpClient() {
	}

	public AbstractHttpClient(String addr) {
		this.addr = addr;
	}

	public int getRespCode() {
		return respCode;
	}

	protected void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	protected byte[] getRespData() {
		return respData;
	}

	protected void setRespData(byte[] respData) {
		this.respData = respData;
	}

	/**
	 * 设置mime-type
	 * @param mimeType
	 */
	public void setMimeType(MimeType mimeType) {
		headerParams.put("Content-Type", mimeType.getValue());
	}

	/**
	 * 添加cookie键值对
	 * @param key
	 * @param value
	 */
	public void addCookie(String key, String value) {
		cookieMap.put(key, value);
		updateCookieField();
	}

	/**
	 * 添加cookie键值对
	 * @param cookies
	 */
	public void addAllCookie(Map<String, String> cookies) {
		cookieMap.putAll(cookies);
		updateCookieField();
	}
	
	private void updateCookieField() {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> entry : cookieMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			builder.append(key);
			builder.append("=");
			builder.append(value);
			builder.append(";");
		}
		String cookie = builder.toString();
		headerParams.put(COOKIE, cookie);
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Map<String, String> getHeaderParams() {
		return headerParams;
	}

	public void setHeaderParams(Map<String, String> headerParams) {
		this.headerParams = headerParams;
	}

	public AfterConnectCallback getAfterConnectCallback() {
		return afterConnectCallback;
	}

	/**
	 * 连接前回调
	 * @param afterConnectCallback
	 */
	public void setAfterConnectCallback(AfterConnectCallback afterConnectCallback) {
		this.afterConnectCallback = afterConnectCallback;
	}

	
	public BeforeConnectCallback getBeforeConnectCallback() {
		return beforeConnectCallback;
	}

	/**
	 * 连接后回调
	 * @return
	 */
	public void setBeforeConnectCallback(BeforeConnectCallback beforeConnectCallback) {
		this.beforeConnectCallback = beforeConnectCallback;
	}

	public Map<String, String> getUrlParams() {
		return urlParams;
	}

	public void setUrlParams(Map<String, String> urlParams) {
		this.urlParams = urlParams;
	}

	/**
	 * 添加url键值对
	 * @param key
	 * @param value
	 */
	public void addUrlParam(String key, String value) {
		urlParams.put(key, value);
	}

	/**
	 * 添加url键值对
	 * @param paramMap
	 */
	public void addAllUrlParam(Map<String, String> paramMap) {
		urlParams.putAll(paramMap);
	}

	/**
	 * 设置请求头参数
	 * @param key
	 * @param value
	 */
	public void addHeaderParam(String key, String value) {
		headerParams.put(key, value);
	}

	/**
	 * 设置请求头参数
	 * @param paramMap
	 */
	public void addAllHeaderParams(Map<String, String> paramMap) {
		headerParams.putAll(paramMap);
	}
	
	public abstract byte[] doRequest() throws Exception ;
	
	protected final NetResponse generateVerboseResponse(HttpURLConnection conn,byte[] data) throws IOException {
		NetResponse response=new NetResponse();
		
		response.setRespCode(conn.getResponseCode());
		response.setCacheControlHeader(conn.getHeaderField("Cache-Control"));
		response.setConnection(conn);
		response.setConnectionHeader(conn.getHeaderField("Connection"));
		response.setContentEncodingHeader(conn.getHeaderField("Content-Encoding"));
		response.setContentTypeHeader(conn.getHeaderField("Content-Type"));
		response.setServerHeader(conn.getHeaderField("Server"));
		response.setRespData(data);
		
		return response;
	}
	
	public abstract RequestMethod getRequestMethod();

	protected void beforeConn(HttpURLConnection conn) {
		if (beforeConnectCallback != null) {
			beforeConnectCallback.beforeConnect(conn);
		}
	}

	protected byte[] afterConn(HttpURLConnection conn) throws Exception {
		respCode = conn.getResponseCode();

		byte[] ret;
		if (afterConnectCallback != null) {
			ret = afterConnectCallback.afterConnect(conn);
		} else {
			ret = simpleAfterConHandler.afterConnect(conn);
		}

		respData=ret;

		netResponse = generateVerboseResponse(conn, respData);
		
		return ret;
	}

	

	/**
	 * set http header before connect
	 * 
	 * @param connection
	 * @param params
	 */
	protected void setAssignHeaderParams(HttpURLConnection connection) {
		if (connection == null || headerParams == null) {
			return;
		}
		for (Entry<String, String> entry : headerParams.entrySet()) {
			connection.setRequestProperty(entry.getKey(), entry.getValue());
		}
	}

	public NetResponse getNetResponse() {
		return netResponse;
	}

	public void setNetResponse(NetResponse netResponse) {
		this.netResponse = netResponse;
	}
}


