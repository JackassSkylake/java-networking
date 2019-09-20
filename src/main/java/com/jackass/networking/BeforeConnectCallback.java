/**
 * 
 */
package com.jackass.networking;

import java.net.HttpURLConnection;

/**
 * @author jackass
 *
 */
public interface BeforeConnectCallback {
	public void beforeConnect(HttpURLConnection conn);
}
