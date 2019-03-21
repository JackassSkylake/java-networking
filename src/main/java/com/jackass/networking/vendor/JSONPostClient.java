/**
 * 
 */
package com.jackass.networking.vendor;

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
		setContentType(MimeType.JSON);
	}
	
	public JSONPostClient(String url) {
		super(url, MimeType.JSON);
	}
	
	public JSONPostClient(String url,String jsonData) {
		super(url, MimeType.JSON);
		postBody=new RawStringBodyGenerator(jsonData).generate();
	}
	
	public void setJsonData(String jsonData) {
		postBody=new RawStringBodyGenerator(jsonData).generate();
	}
}
