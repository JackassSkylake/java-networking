/**
 * 
 */
package com.jackass.networking;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wz
 *
 * 
 */
public class ConfigurableX509TrustManager implements X509TrustManager {

	public enum KeyStoreType {
		PKCS12, JKS
	}

	private List<X509TrustManager> tms;

	public ConfigurableX509TrustManager() throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException,
			CertificateException, IOException {
		this(null, null, null, null, null);
	}

	/**
	 * @param securityProvider
	 * @param keyStoreType
	 * @param keyStoreStream
	 * @param keyStorePwd
	 * @param trustManagerAlogrithm
	 * @throws NoSuchProviderException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws CertificateException
	 */
	public ConfigurableX509TrustManager(InputStream keyStoreStream, char[] keyStorePwd, KeyStoreType keyStoreType,
			String trustManagerAlogrithm, String securityProvider) throws KeyStoreException, NoSuchProviderException,
			NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore keyStore = null;
		TrustManagerFactory tmf = null;

		if (keyStoreType == null) {
			keyStoreType = KeyStoreType.PKCS12;
		}

		if (StringUtils.isBlank(trustManagerAlogrithm)) {
			trustManagerAlogrithm = TrustManagerFactory.getDefaultAlgorithm();
		}

		if (StringUtils.isNotBlank(securityProvider)) {
			keyStore = KeyStore.getInstance(keyStoreType.name(), securityProvider);
			tmf = TrustManagerFactory.getInstance(trustManagerAlogrithm, securityProvider);
		} else {
			keyStore = KeyStore.getInstance(keyStoreType.name());
			tmf = TrustManagerFactory.getInstance(trustManagerAlogrithm);
		}

		if (keyStoreStream != null && keyStorePwd != null) {
			keyStore.load(keyStoreStream, keyStorePwd);
		}

		tmf.init(keyStore);

		tms = new ArrayList<>();
		TrustManager[] trustManagers = tmf.getTrustManagers();
		for (TrustManager tm : trustManagers) {
			if (tm instanceof X509TrustManager) {
				tms.add((X509TrustManager) tm);
			}
		}
	}

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		for (X509TrustManager tm : tms) {
			tm.checkClientTrusted(chain, authType);
		}
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		for (X509TrustManager tm : tms) {
			tm.checkServerTrusted(chain, authType);
		}
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		List<X509Certificate> certificates = new ArrayList<>();
		for (X509TrustManager tm : tms) {
			for (X509Certificate certificate : tm.getAcceptedIssuers()) {
				certificates.add(certificate);
			}
		}
		return certificates.toArray(new X509Certificate[certificates.size()]);
	}
}
