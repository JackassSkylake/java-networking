/**
 * 
 */
package com.jackass.networking;

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

	@Override
	final protected boolean isPost() {
		return false;
	}

	/**
	 * 注意原来有的同名参数会被新的覆盖
	 * @param key
	 * @param value
	 */
	public void addParam(String key,Object value) {
		params.put(key, value);
	}
	
	public void addAllParams(Map<String, Object> params) {
		this.params.putAll(params);
	}
}
