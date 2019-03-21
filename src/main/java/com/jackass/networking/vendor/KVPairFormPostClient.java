/**
 * 
 */
package com.jackass.networking.vendor;

import java.util.Map;

import com.jackass.networking.MimeType;
import com.jackass.networking.NetResponse;
import com.jackass.networking.PostClient;
import com.jackass.networking.afterconn.AfterConnectCallback;
import com.jackass.networking.postbody.KeyValueFormDataGenerator;

/**
 * @author jackass
 *
 */
public class KVPairFormPostClient extends PostClient{
	public KVPairFormPostClient() {
		setContentType(MimeType.XWWWFORM);
	}
	
	public KVPairFormPostClient(String url) {
		super(url, MimeType.XWWWFORM);
	}
	
	public void addParam(String key,Object value) {
		params.put(key, value);
	}
	
	public void addAllParams(Map<String, Object> params) {
		this.params.putAll(params);
	}

	@Override
	public byte[] doRequest() throws Exception {
		generatePostBody(new KeyValueFormDataGenerator(params));
		return super.doRequest();
	}

	@Override
	public NetResponse doRequestRetVerbose() throws Exception {
		generatePostBody(new KeyValueFormDataGenerator(params));
		return super.doRequestRetVerbose();
	}
}
