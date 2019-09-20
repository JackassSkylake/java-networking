/**
 * 
 */
package com.jackass.networking.vendor;

import java.util.Map;
import java.util.Map.Entry;

import com.jackass.networking.MimeType;
import com.jackass.networking.PostClient;

/**
 * @author jackass
 *
 */
public class KVPairFormPostClient extends PostClient{
	private String bodyKvs="";
	
	public KVPairFormPostClient() {
		setMimeType(MimeType.XWWWFORM);
	}
	
	public KVPairFormPostClient(String url) {
		super(url, MimeType.XWWWFORM);
	}
	
	public void addParam(String key,String value) {
		bodyKvs=String.format("%s%s=%s&", bodyKvs,key,value);
	}
	
	public void addAllParams(Map<String, String> params) {
		for(Entry<String, String> entry:params.entrySet()) {
			addParam(entry.getKey(), entry.getValue());
		}
	}
}
