/**
 * 
 */
package com.jackass.test;

import com.alibaba.fastjson.JSONObject;
import com.jackass.networking.GetClient;
import com.jackass.networking.MimeType;
import com.jackass.networking.NetResponse;
import com.jackass.networking.PostClient;
import com.jackass.networking.postbody.RawStringBodyGenerator;
import com.jackass.networking.vendor.JSONPostClient;
import com.jackass.networking.vendor.KVPairFormPostClient;

/**
 * @author jackass
 *
 */
public class MainApp {
	public static void main(String[] args) throws Exception {
		/**GetClient client=new GetClient("http://localhost:9000/http_server/test/get");
		client.addParam("username", "msn");
		client.addParam("age", "13");
		client.doRequest();
		NetResponse response = client.getNetResponse();
		System.out.println(response.getRespDataAsString("utf-8"));*/
		
		/**KVPairFormPostClient postClient=new KVPairFormPostClient("http://localhost:9000/http_server/test/formPost");
		postClient.addParam("username", "msn");
		postClient.addParam("age", "11");
		postClient.doRequest();
		NetResponse response2 = postClient.getNetResponse();
		System.out.println(response2.getRespDataAsString("utf-8"));*/
		
		/**JSONPostClient jsonPostClient=new JSONPostClient("http://localhost:9000/http_server/test/jsonPost");
		JSONObject json=new JSONObject();
		json.put("username", "wz");
		json.put("age", 10);
		jsonPostClient.setJsonData(json);
		jsonPostClient.doRequest();
	
		NetResponse response3 = jsonPostClient.getNetResponse();
		System.out.println(response3.getRespDataAsString("utf-8"));*/
		
		PostClient postClient=new PostClient("https://api.mch.weixin.qq.com/pay/refundquery",MimeType.XML);
		//postClient.setUseHttps(true);
		
		String data="<xml>\n" + 
				"   <appid>wx94753d0a76b5c611</appid>\n" + 
				"   <mch_id>1502510371</mch_id>\n" + 
				"   <nonce_str>0b9f35f484df17a732e537c37708d1d0</nonce_str>\n" + 
				"   <out_trade_no>20191218182657904181</out_trade_no>\n" + 
				"   <sign>66FFB727015F450D167EF38CCC549521</sign>\n" + 
				"</xml>";
		
		postClient.setBodyGenerator(new RawStringBodyGenerator(data));
		postClient.doRequest();
		String result = postClient.getNetResponse().getRespDataAsString("utf-8");
		System.out.println(result);
	}
}
