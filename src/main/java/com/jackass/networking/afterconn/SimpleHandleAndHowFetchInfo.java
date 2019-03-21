/**
 * 
 */
package com.jackass.networking.afterconn;

import java.net.HttpURLConnection;

/**
 * @author jackass
 * 简单处理流并获取额外参数
 */
public abstract class SimpleHandleAndHowFetchInfo implements FetchMoreRespInfo{
	protected byte[] data=null;
	@Override
	public byte[] afterConnect(HttpURLConnection conn) throws Exception {
		data = new SimpleAfterConHandler().afterConnect(conn);
		fetchMoreInfo(conn);
		return data;
	}
}
