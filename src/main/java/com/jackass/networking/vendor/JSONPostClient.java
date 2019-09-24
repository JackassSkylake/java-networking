/**
 * 
 */
package com.jackass.networking.vendor;

import com.alibaba.fastjson.JSONObject;
import com.jackass.networking.MimeType;
import com.jackass.networking.PostClient;
import com.jackass.networking.postbody.RawStringBodyGenerator;

/**
 * @author jackass
 *
 */
public class JSONPostClient extends PostClient{
	private RawStringBodyGenerator generator=new RawStringBodyGenerator("");
	/**
	 * 
	 */
	public JSONPostClient() {
		setMimeType(MimeType.JSON);
		setBodyGenerator(generator);
	}
	
	public JSONPostClient(String url) {
		super(url, MimeType.JSON);
		setBodyGenerator(generator);
	}
	
	public JSONPostClient(String url,String jsonData){
		this(url);
		generator.setDataStr(jsonData);
	}
	
	public JSONPostClient(String url,JSONObject json){
		this(url, json.toJSONString());
	}
	
	public void setJsonData(String jsonStr){
		generator.setDataStr(jsonStr);
	}
	
	public void setJsonData(JSONObject json){
		generator.setDataStr(json.toJSONString());
	}
}
