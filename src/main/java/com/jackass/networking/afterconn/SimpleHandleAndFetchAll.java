/**
 * 
 */
package com.jackass.networking.afterconn;

import java.net.HttpURLConnection;

import com.jackass.networking.NetResponse;

/**
 * @author jackass
 *
 */
public class SimpleHandleAndFetchAll extends SimpleHandleAndHowFetchInfo{
	private NetResponse response;
	public SimpleHandleAndFetchAll(NetResponse response) {
		this.response=response;
	}
	
	@Override
	public void fetchMoreInfo(HttpURLConnection connection) throws Exception {
		int responseCode = connection.getResponseCode();
		response.setRespCode(responseCode);
		response.setConnection(connection);
		response.setRespData(data);
	}
}
