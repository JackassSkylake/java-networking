/**
 * 
 */
package com.jackass.networking.afterconn;

import java.io.InputStream;
import java.net.HttpURLConnection;

import com.jackass.networking.StreamTool;

/**
 * @author jackass
 * 简单的将相应流转为字节数组然后返回
 */
public class SimpleAfterConHandler implements AfterConnectCallback{
	@Override
	public byte[] afterConnect(HttpURLConnection conn) throws Exception {
		byte[] ret=null;
		int responseCode = conn.getResponseCode();
		InputStream inputStream = null;
		if(responseCode==HttpURLConnection.HTTP_OK || responseCode==HttpURLConnection.HTTP_CREATED || responseCode==HttpURLConnection.HTTP_ACCEPTED) {
			inputStream = conn.getInputStream();
		}else {
			inputStream = conn.getErrorStream();
		}
		ret=StreamTool.inputStreamToByteArray(inputStream);
		if(ret==null) {
			ret=new byte[0];
		}
		return ret;
	}

}
