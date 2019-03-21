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

	@Override
	public byte[] generate() {
		if(dataStr!=null) {
			try {
				return dataStr.getBytes(encoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
