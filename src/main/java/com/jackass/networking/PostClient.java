/**
 * 
 */
package com.jackass.networking;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.jackass.networking.postbody.PostBodyGenerator;

/**
 * @author jackass
 *
 */
public class PostClient extends AbstractHttpClient{
	
	private PostBodyGenerator bodyGenerator;
	
	public PostClient() {}
	public PostClient(String url,MimeType mimeType) {
		super(url);
		super.setMimeType(mimeType);
	}
	
	/**
	 * @return the bodyGenerator
	 */
	public PostBodyGenerator getBodyGenerator() {
		return bodyGenerator;
	}
	/**
	 * @param bodyGenerator the bodyGenerator to set
	 */
	public void setBodyGenerator(PostBodyGenerator bodyGenerator) {
		this.bodyGenerator = bodyGenerator;
	}
	
	@Override
	public final byte[] doRequest() throws Exception {
		String reqUrl = UrlKeyValueUtil.optimizeUrl(addr, urlParams);
		URL url = new URL(reqUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		setAssignHeaderParams(connection);
		
		byte[] postData=null;
		
		if (bodyGenerator != null) {
			postData=bodyGenerator.generate();
			connection.setRequestProperty("Content-Length", String.valueOf(postData.length));
		}

		beforeConn(connection);

		connection.connect();

		try (OutputStream outputStream = connection.getOutputStream();) {
			if (postData != null)
				outputStream.write(postData);
			outputStream.flush();
		}

		return afterConn(connection);
	}
	
	@Override
	public final RequestMethod getRequestMethod() {
		return RequestMethod.POST;
	}
}
