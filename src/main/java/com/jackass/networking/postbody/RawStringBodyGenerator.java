/**
 * 
 */
package com.jackass.networking.postbody;

import java.io.UnsupportedEncodingException;

/**
 * @author jackass
 *
 */
public class RawStringBodyGenerator implements PostBodyGenerator{
	private String encoding="utf-8";
	private String dataStr;
	
	public RawStringBodyGenerator(String data) {
		this.dataStr=data;
	}
	
	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	

	/**
	 * @return the dataStr
	 */
	public String getDataStr() {
		return dataStr;
	}

	/**
	 * @param dataStr the dataStr to set
	 */
	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}

	@Override
	public byte[] generate() throws UnsupportedEncodingException {
		if(dataStr!=null) {
			return dataStr.getBytes(encoding);
		}
		return new byte[0];
	}

}
