/**
 * 
 */
package com.jackass.networking;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author jackass
 *
 */
public class GetClient extends AbstractHttpClient{
	public GetClient() {}
	
	public GetClient(String url) {
		super(url);
	}
	/**
	 * 注意原来有的同名参数会被新的覆盖
	 * @param key
	 * @param value
	 */
	public void addParam(String key,String value) {
		addUrlParam(key, value);
	}
	
	public void addAllParams(Map<String, String> params) {
		addAllUrlParam(params);
	}
	
	@Override
	public final byte[] doRequest() throws Exception {
		String reqUrl = UrlKeyValueUtil.optimizeUrl(addr, urlParams);
		URL url = new URL(reqUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setRequestMethod("GET");
		setAssignHeaderParams(connection);

		beforeConn(connection);

		connection.connect();

		return afterConn(connection);
	}

	@Override
	public final RequestMethod getRequestMethod() {
		return RequestMethod.POST;
	}
}
