/**
 * 
 */
package com.jackass.networking;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author jackass
 *
 */
public class PostClient extends AbstractHttpClient{
	private byte[] postData;
	
	
	public PostClient() {}
	public PostClient(String url,MimeType mimeType) {
		super(url);
		super.setMimeType(mimeType);
	}
	
	public byte[] getPostData() {
		return postData;
	}
	public void setPostData(byte[] postData) {
		this.postData = postData;
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
		if (postData != null) {
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
