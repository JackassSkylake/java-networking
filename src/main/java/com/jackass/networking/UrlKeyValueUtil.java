/**
 * 
 */
package com.jackass.networking;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jackass
 *
 */
public class UrlKeyValueUtil {
	private static Logger logger = LoggerFactory.getLogger(UrlKeyValueUtil.class);

	private UrlKeyValueUtil() {
	}

	public static String optimizeUrl(String url, Map<String, String> kvMap) {
		String reqUrl = url;
		if (kvMap != null && !kvMap.isEmpty()) {
			if (reqUrl.indexOf('?') != -1) {
				reqUrl = reqUrl + "&" + toUrlKeyVal(kvMap);
			} else {
				reqUrl = reqUrl + "?" + toUrlKeyVal(kvMap);
			}
		}
		return reqUrl;
	}

	public static String toUrlKeyVal(Map<String, String> params) {
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> entry : params.entrySet()) {
			try {
				String value = URLEncoder.encode(entry.getValue(), "utf-8");
				builder.append(entry.getKey());
				builder.append("=");
				builder.append(value);
				builder.append("&");
			} catch (UnsupportedEncodingException e) {
				logger.error("", e);
			}
		}
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}

	public static Map<String, String> toParamsMap(String keyValStr) {
		Map<String, String> params = new HashMap<>();
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
