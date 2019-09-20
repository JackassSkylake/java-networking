/**
 * 
 */
package com.jackass.networking.vendor;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;
import com.jackass.networking.MimeType;
import com.jackass.networking.PostClient;
import com.jackass.networking.postbody.RawStringBodyGenerator;

/**
 * @author jackass
 *
 */
public class JSONPostClient extends PostClient{
	/**
	 * 
	 */
	public JSONPostClient() {
		setMimeType(MimeType.JSON);
	}
	
	public JSONPostClient(String url) {
		super(url, MimeType.JSON);
	}
	
	public JSONPostClient(String url,String jsonData) throws UnsupportedEncodingException {
		super(url, MimeType.JSON);
		setPostData(new RawStringBodyGenerator(jsonData).generate());
	}
	
	public JSONPostClient(String url,JSONObject json) throws UnsupportedEncodingException {
		this(url, json.toJSONString());
	}
	
	public void setJsonData(String jsonStr) throws UnsupportedEncodingException {
		setPostData(new RawStringBodyGenerator(jsonStr).generate());
	}
	
	public void setJsonData(JSONObject json) throws UnsupportedEncodingException {
		setPostData(new RawStringBodyGenerator(json.toJSONString()).generate());
	}
}
