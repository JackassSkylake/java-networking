/**
 * 
 */
package com.jackass.networking;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author jackass
 *
 */
public class UrlKeyValueUtil {
	public static String toUrlKeyVal(Map<String, Object> params) {
		StringBuffer buffer = new StringBuffer();
		for (Entry<String, Object> entry : params.entrySet()) {
			try {
				String value=URLEncoder.encode(String.valueOf(entry.getValue()), "utf-8");
				buffer.append(entry.getKey() + "=" + value + "&");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (buffer.length() > 0) {
			buffer.deleteCharAt(buffer.length() - 1);
		}
		return buffer.toString();
	}

	public static Map<String, Object> toParamsMap(String keyValStr) {
		Map<String, Object> params = new HashMap<>();
		if (keyValStr != null) {
			String[] keyValPairs = keyValStr.split("&");
			for (String pairStr : keyValPairs) {
				String[] pairs = pairStr.split("=");
				if (pairs.length == 2) {
					String key = pairs[0];
					String val = pairs[1];
					params.put(key, val);
				}
			}
		}
		return params;
	}
}
