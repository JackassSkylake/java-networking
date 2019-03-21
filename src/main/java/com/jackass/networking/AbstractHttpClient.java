/**
 * 
 */
package com.jackass.networking;

import java.util.HashMap;
import java.util.Map;

import com.jackass.networking.afterconn.AfterConnectCallback;
import com.jackass.networking.afterconn.SimpleHandleAndFetchAll;
import com.jackass.networking.afterconn.SimpleHandleAndHowFetchInfo;

/**
 * @author jackass
 *
 */
public abstract class AbstractHttpClient {
	protected String url=null;
	protected Map<String, String> headers=new HashMap<>();
	protected Map<String, String> cookies=new HashMap<>();
	protected Map<String, Object> params=new HashMap<>();
	protected byte[] postBody=null;
	
	public AbstractHttpClient() {}
	
	public AbstractHttpClient(String url) {
		this.url=url;
	}
	
	/**
	 * 注意原来有的同名参数会被新的覆盖
	 * @param key
	 * @param value
	 */
	public void setHeader(String key,String value) {
		headers.put(key, value);
	}
	
	public void setAllHeaders(Map<String, String> headers) {
		this.headers.putAll(headers);
	}
	
	/**
	 * 注意原来有的同名参数会被新的覆盖
	 * @param key
	 * @param value
	 */
	public void setCookie(String key,String value) {
		cookies.put(key, value);
	}
	
	protected abstract boolean isPost();
	
	public void setUrl(String url) {
		this.url=url;
	}
	
	public void setContentType(MimeType type) {
		setHeader("Content-Type", type.getValue());
	}
	
	public byte[] doRequest() throws Exception {
		return doRequest((AfterConnectCallback)null);
	}
	
	public byte[] doRequest(SimpleHandleAndHowFetchInfo howFetchInfo) throws Exception {
		return doRequest((AfterConnectCallback)howFetchInfo);
	}
	
	public byte[] doRequest(AfterConnectCallback callback) throws Exception {
		byte[] ret=null;
		if(!isPost()) {
			ret=HttpUtil.doGet(url, params, headers,callback);
		}else {
			ret=HttpUtil.doPost(url,postBody, headers,callback);
		}
		return ret;
	}
	
	public NetResponse doRequestRetVerbose() throws Exception {
		return doRequestRetVerbose((AfterConnectCallback)null);
	}
	
	public NetResponse doRequestRetVerbose(SimpleHandleAndHowFetchInfo simpleHandleAndHowFetchInfo) throws Exception {
		return doRequestRetVerbose((AfterConnectCallback)simpleHandleAndHowFetchInfo);
	}
	
	public NetResponse doRequestRetVerbose(AfterConnectCallback callback) throws Exception {
		NetResponse response=new NetResponse();
		if(callback==null)
			callback=new SimpleHandleAndFetchAll(response);
		if(!isPost()) {
			HttpUtil.doGet(url, params, headers,callback);
		}else {
			HttpUtil.doPost(url,postBody, headers,callback);
		}
		return response;
	}
}
