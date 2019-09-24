/**
 * 
 */
package com.jackass.networking.vendor;

import java.util.LinkedHashMap;
import java.util.Map;

import com.jackass.networking.MimeType;
import com.jackass.networking.PostClient;
import com.jackass.networking.postbody.KVPairFormDataGenerator;

/**
 * @author jackass
 *
 */
public class KVPairFormPostClient extends PostClient{
	private Map<String, String> params=new LinkedHashMap<>();
	
	public KVPairFormPostClient() {
		setMimeType(MimeType.XWWWFORM);
		setBodyGenerator(new KVPairFormDataGenerator(params));
	}
	
	public KVPairFormPostClient(String url) {
		super(url, MimeType.XWWWFORM);
		setBodyGenerator(new KVPairFormDataGenerator(params));
	}
	
	public void addParam(String key,String value) {
		params.put(key, value);
	}
	
	public void addAllParams(Map<String, String> params) {
		this.params.putAll(params);
	}
}
