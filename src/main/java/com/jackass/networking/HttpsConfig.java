/**
 * 
 */
package com.jackass.networking;

import javax.net.ssl.TrustManager;

/**
 * @author wz
 *
 * 
 */
public class HttpsConfig {
	private String sslContextProtocol;
	private String securityProvider;
	
	private TrustManager[] trustManagers;
	
	/**
	 * 
	 */
	public HttpsConfig() {
	}
	
	public HttpsConfig(String sslContextProtocol,String securityProvider,TrustManager[] trustManagers) {
		this.sslContextProtocol=sslContextProtocol;
		this.securityProvider=securityProvider;
		this.trustManagers=trustManagers;
	}

	/**
	 * @return the sslContextProtocol
	 */
	public String getSslContextProtocol() {
		return sslContextProtocol;
	}

	/**
	 * @param sslContextProtocol the sslContextProtocol to set
	 */
	public void setSslContextProtocol(String sslContextProtocol) {
		this.sslContextProtocol = sslContextProtocol;
	}

	/**
	 * @return the securityProvider
	 */
	public String getSecurityProvider() {
		return securityProvider;
	}

	/**
	 * @param securityProvider the securityProvider to set
	 */
	public void setSecurityProvider(String securityProvider) {
		this.securityProvider = securityProvider;
	}

	/**
	 * @return the trustManagers
	 */
	public TrustManager[] getTrustManagers() {
		return trustManagers;
	}

	/**
	 * @param trustManagers the trustManagers to set
	 */
	public void setTrustManagers(TrustManager[] trustManagers) {
		this.trustManagers = trustManagers;
	}
}
