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
public class KVPairFormDataGenerator implements PostBodyGenerator {
	private String encoding = "utf-8";
	private Map<String, String> params = new HashMap<>();

	/**
	 * 
	 */
	public KVPairFormDataGenerator() {}

	public KVPairFormDataGenerator(Map<String, String> params) {
		this.params=params;
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

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	@Override
	public byte[] generate() throws UnsupportedEncodingException{
		String postData = UrlKeyValueUtil.toUrlKeyVal(params);
		if (postData != null) {
			return postData.getBytes(encoding);
		}
		return new byte[0];
	}

}
