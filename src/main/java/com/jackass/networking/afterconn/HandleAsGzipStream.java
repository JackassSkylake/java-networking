/**
 * 
 */
package com.jackass.networking.afterconn;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

import com.jackass.networking.AfterConnectCallback;
import com.jackass.networking.StreamTool;

/**
 * @author jackass
 * 将返回流作为压缩流处理并转化为字节数组然后返回
 */
public class HandleAsGzipStream implements AfterConnectCallback{
	@Override
	public byte[] afterConnect(HttpURLConnection conn) throws Exception {
		InputStream inputStream=conn.getInputStream();
		String contentType=conn.getHeaderField("Content-Encoding");
		if(contentType!=null && contentType.equals("gzip")) {
			inputStream=new GZIPInputStream(inputStream);
		}
		return StreamTool.inputStreamToByteArray(inputStream);
	}

}
