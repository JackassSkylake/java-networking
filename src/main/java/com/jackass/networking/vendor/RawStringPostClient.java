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
public class RawStringPostClient extends PostClient{
	private RawStringBodyGenerator generator=new RawStringBodyGenerator("");
	/**
	 * 
	 */
	public RawStringPostClient(MimeType mimeType) {
		setMimeType(mimeType);
		setBodyGenerator(generator);
	}
	
	public RawStringPostClient(String url,MimeType mimeType) {
		super(url, mimeType);
		setBodyGenerator(generator);
	}
	
	public RawStringPostClient(String url,MimeType mimeType,String jsonData){
		this(url,mimeType);
		generator.setDataStr(jsonData);
	}
	
	public void setRawData(String data){
		generator.setDataStr(data);
	}
}
