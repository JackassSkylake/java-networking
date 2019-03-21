/**
 * 
 */
package com.jackass.networking.afterconn;

import java.net.HttpURLConnection;

/**
 * @author jackass
 *
 */
public interface AfterConnectCallback {
	public byte[] afterConnect(HttpURLConnection conn) throws Exception;
}
