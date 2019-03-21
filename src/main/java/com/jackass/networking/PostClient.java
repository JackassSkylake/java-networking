/**
 * 
 */
package com.jackass.networking;

import java.io.UnsupportedEncodingException;

import com.jackass.networking.postbody.PostBodyGenerator;
import com.jackass.networking.postbody.RawStringBodyGenerator;

/**
 * @author jackass
 *
 */
public class PostClient extends AbstractHttpClient{
	public PostClient() {}
	public PostClient(String url,MimeType mimeType) {
		this.url=url;
		super.setContentType(mimeType);
	}
	
	@Override
	final protected boolean isPost() {
		return true;
	}
	
	public void generatePostBody(PostBodyGenerator generator) {
		postBody=generator.generate();
	}
	
	public void generatePostBody(String data,String encoding) throws UnsupportedEncodingException {
		RawStringBodyGenerator generator = new RawStringBodyGenerator(data);
		generator.setEncoding("utf-8");
		postBody=generator.generate();
	}
}
