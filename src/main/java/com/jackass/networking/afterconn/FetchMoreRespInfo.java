/**
 * 
 */
package com.jackass.networking.afterconn;

import java.net.HttpURLConnection;

/**
 * @author jackass
 *
 */
public interface FetchMoreRespInfo extends AfterConnectCallback{
	public void fetchMoreInfo(HttpURLConnection connection) throws Exception;
}
