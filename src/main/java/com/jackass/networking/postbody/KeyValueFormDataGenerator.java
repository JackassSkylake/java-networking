/**
 * 
 */
package com.jackass.networking.postbody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.jackass.networking.UrlKeyValueUtil;

/**
 * @author jackass
 * 
 *         表单提交类似的请求体：格式为:key1=value1&key2=value2...
 *
 */
public class KeyValueFormDataGenerator implements PostBodyGenerator {
	private String encoding = "utf-8";
	private Map<String, Object> params = new HashMap<>();

	/**
	 * 
	 */
	public KeyValueFormDataGenerator() {
		// TODO Auto-generated constructor stub
	}

	public KeyValueFormDataGenerator(Map<String, Object> params) {
		this.params.putAll(params);
	}

	public void addParam(String key, String value) {
		params.put(key, value);
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public byte[] generate() {
		String postData = UrlKeyValueUtil.toUrlKeyVal(params);
		try {
			if (postData != null) {
				return postData.getBytes(encoding);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

}
